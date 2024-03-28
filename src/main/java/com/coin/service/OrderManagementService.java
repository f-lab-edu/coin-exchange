package com.coin.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.coin.service.OrderManagementService;
import com.coin.ResultVO;
import com.coin.CustomErrorCode;
import com.coin.dto.OrderDTO;
import com.coin.exception.CustomException;
import com.coin.response.ApiResponse;

@Service("OrderManagementService")
public class OrderManagementService {

  private OrderService orderService;
  private AccountService accountService;
  private HoldCoinService holdCoinService;
  private TradeHistoryService tradeHistoryService;

  public OrderManagementService(OrderService orderService, AccountService accountService,
      HoldCoinService holdCoinService, TradeHistoryService tradeHistoryService) {
    this.orderService = orderService;
    this.accountService = accountService;
    this.holdCoinService = holdCoinService;
    this.tradeHistoryService = tradeHistoryService;
  }

  // 주문 -> 계좌 금액 확인(accountService.getBalance(userNumber)) 없으면 종료 있으면-> 주문테이블 확인
  // (매도면 구분이 매수인 종목만 확인)(orderService.getOrder(userNumber, coinCode,
  // buySellCode))
  // 있으면 거래 진행-> 보유코인 테이블에 입력, 거래이력 테이블에 입력, 계좌 테이블(잔액) 수정(내거와 상대방거), 주문테이블(해당 코인)
  // 삭제
  // 없으면-> 주문테이블에 입력, 계좌 테이블(잔액) 수정
  @Transactional//(isolation = Isolation.SERIALIZABLE)
  public ApiResponse<String> addOrder(OrderDTO orderDto) {
    // 계좌 금액 확인 StringUtils
    if (StringUtils.equals(orderDto.getBuySellCode(),"01") && !validateAmount(orderDto)) {
      throw new CustomException("잔액이 부족합니다");
    }
    
    List<OrderDTO> orders =
        orderService.findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(orderDto);

    // 수량이 존재하지 않으면 주문 테이블에 입력 // 배치로 오후 4시에 주문테이블을 비운다(개발 예정) 비울때 매수 주문을 한 유저의 경우에는 계좌에 금액을 돌려준다
    if (orders.size() == 0) {
      // 주문 테이블에 입력과 잔액 차감
      orderService.insert(orderDto);
      // 매수인 경우에만 차감
      if (StringUtils.equals(orderDto.getBuySellCode(),"01")) {
        // 계좌에 잔액 차감
        accountService.updateBalance(accountService.accountBuilder(orderDto, 0));
      }
//      return new ResponseEntity<>("주문을 입력하였습니다", HttpStatus.OK);
      return ApiResponse.ok("주문을 입력하였습니다");
    }

    // 수량이 존재하면 거래 진행-> 1보유코인 테이블에 등록(입력), 2주문테이블(해당 코인) 삭제, 3계좌 테이블(잔액) 수정(내거와
    // 상대방거), 4거래이력 테이블에 입력,
    ResultVO rv = getQuantity(orderDto, orders);

    // 1보유코인 테이블에 등록(입력)
    holdCoinService.insert(holdCoinService.holdCoinBuilder(orderDto, rv.getBreakIndex(),
        rv.getCalculateQuantity(), orders)); //holdCoinDto

    // 2주문테이블(해당 코인) 삭제
    orderService.update(orderDto, rv.getBreakIndex(), rv.getCalculateQuantity(), orders);// orderDto 

    // 3상대방과 나의 계좌에서 잔액을 변경
    accountService.updateBalance(accountService.accountBuilder(orderDto, rv.getBreakIndex()), orders);// accountDto

    /*--------------------Transaction 2*/
    // 4거래이력 테이블에 입력
    tradeHistoryService.insert(tradeHistoryService.tradeHistoryBuilder(orderDto)); //tradeHistoryDto

//    return new ResponseEntity<>("거래가 정상적으로 종료되었습니다", HttpStatus.OK);
    return ApiResponse.ok("거래가 정상적으로 종료되었습니다");
  }

  public boolean validateAmount(OrderDTO orderDto) {
    // 계좌의 잔액 조회
    int balance = accountService.getBalance(orderDto.getUserNumber());
    // 입력한 수량과 금액의 합
    BigDecimal orderBalance = orderDto.calculateTotalTransactionAmount();

    // 잔액이 입력한 수량과 금액의 합보다 작으면 종료
    if (balance < orderBalance.intValue()) {
      return false;
    }
    return true;
  }

  public ResultVO getQuantity(OrderDTO orderDto, List<OrderDTO> orders) {

    ResultVO rv = new ResultVO();

    if (orders.get(0).getTranQuantity() >= orderDto.getTranQuantity()) {
      rv.setCalculateQuantity(orders.get(0).getTranQuantity());
      rv.setBreakIndex(0);
      return rv;
    }

    // 수량 총 합을 계산하기위한 변수
    AtomicInteger quantity = new AtomicInteger(0);

    int breakIndex = IntStream.range(0, orders.size())
        .takeWhile(
            i -> quantity.addAndGet(orders.get(i).getTranQuantity()) < orderDto.getTranQuantity()) // false면
        // 그만둔다
        .reduce((idx1, idx2) -> idx2).orElse(orders.size() - 1);

    rv.setCalculateQuantity(quantity.get());
    // 주문이 존재하지 않다면 0 / 수량의 총합이 부족하면 breakIndex / 수량의 총합을 충족하면 breakIndex+1(takeWhile에서 false인 경우에
    // 빠져나와서 +1을 해준다)
    rv.setBreakIndex(
        orders.size() == 0 ? 0 : breakIndex == orders.size() - 1 ? breakIndex : breakIndex + 1);

    return rv;
  }
}
