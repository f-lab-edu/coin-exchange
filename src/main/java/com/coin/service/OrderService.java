package com.coin.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.coin.service.OrderService;
import com.coin.ResultVO;
import com.coin.dao.AccountRunnable;
import com.coin.dao.OrderRunnable;
import com.coin.dto.AccountDTO;
import com.coin.dto.HoldCoinDTO;
import com.coin.dto.OrderDTO;
import com.coin.dto.ParameterDTO;
import com.coin.dto.TradeHistoryDTO;
import com.coin.mapper.AccountMapper;
import com.coin.mapper.CoinMapper;
import com.coin.mapper.OrderMapper;
import com.coin.mapper.TradeHistoryMapper;

import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.reducing;

@EnableTransactionManagement
@Service("OrderService")
@Slf4j
public class OrderService {

	private OrderMapper orderMapper;
	private AccountMapper accountMapper;
	private CoinMapper coinMapper;
	private TradeHistoryMapper tradeHistoryMapper;
	

	public OrderService(OrderMapper orderMapper, AccountMapper accountMapper, CoinMapper coinMapper, TradeHistoryMapper tradeHistoryMapper) {
		this.orderMapper = orderMapper;
		this.accountMapper = accountMapper;
		this.coinMapper = coinMapper;
		this.tradeHistoryMapper = tradeHistoryMapper;
	}

	// 주문 -> 계좌 금액 확인(accountService.getBalance(userNumber)) 없으면 종료 있으면-> 주문테이블 확인
	// (매도면 구분이 매수인 종목만 확인)(orderService.getOrder(userNumber, coinCode, buySellCode))
	// 있으면 거래 진행-> 보유코인 테이블에 입력, 거래이력 테이블에 입력, 계좌 테이블(잔액) 수정(내거와 상대방거), 주문테이블(해당 코인) 삭제
	// 없으면-> 주문테이블에 입력, 계좌 테이블(잔액) 수정
	@Transactional()
	public int addOrder(OrderDTO orderDto) {
		//계좌 금액 확인
		if(!validateAmount(orderDto)) {
			return 0;
		}
		
		List<OrderDTO> orders = orderMapper.findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(orderDto);
//								orderMapper.findByOrderDTO
		
		// 수량이 존재하지 않으면 주문 테이블에 입력 // 배치로 오후 4시에 주문테이블을 비운다(개발 예정)
		if (orders.size() == 0) {
			// 주문 테이블에 입력과 잔액 차감
			insertOrder(orderDto);
			return 1;
		}

		// 수량이 존재하면 거래 진행-> 1보유코인 테이블에 등록(입력), 2주문테이블(해당 코인) 삭제, 3계좌 테이블(잔액) 수정(내거와 상대방거), 4거래이력 테이블에 입력,
		ResultVO rv = getQuantity(orderDto, orders);
		
		ParameterDTO param = ParameterDTO.builder()
										.orderDto(orderDto)
										.breakIndex(rv.getBreakIndex())
										.quantity(rv.getGetQuantity())
										.orders(orders)
										.accountDto(accountBuilder(orderDto))
										.build();
		//1보유코인 테이블에 등록(입력)
		insertHoldCoin(param);
		//insertHoldCoin(orderDto, idx, getQuantity, orders);
		
		//2주문테이블(해당 코인) 삭제
		updateOrder(param);
		
		//3상대방과 나의 계좌에서 잔액을 변경
		updateBalance(param);
		
		/*--------------------Transaction 2*/
		//4거래이력 테이블에 입력
//		try {
			insertTradeHistory(orderDto);
//		} catch (Exception e) {
//			log.error("거래 로그 입력 에러", e.getMessage());
//		}
		return 1;
	}
	
	public boolean validateAmount(OrderDTO orderDto) {
		// 계좌의 잔액 조회
		int balance = accountMapper.getBalance(orderDto.getUserNumber());
		// 입력한 수량과 금액의 합
		BigDecimal orderBalance = orderDto.calculateTotalTransactionAmount();

		// 잔액이 입력한 수량과 금액의 합보다 작으면 종료
		if (balance < orderBalance.intValue()) {
			return false;
		}
		return true;
	}
	
	public void insertOrder(OrderDTO orderDto) {
		orderMapper.insertOrder(orderDto);
		
		//매수인 경우에만 차감
		if(orderDto.getBuySellCode().equals("01")) {
			// 계좌에 잔액 차감
			//updateBalance(accountBuilder(orderDto));
			accountMapper.updateBalance(accountBuilder(orderDto));
		}
	}
	
	/*public void updateBalance(AccountDTO accountDto) {
		accountMapper.updateBalance(accountDto);
	}*/
	
	public ResultVO getQuantity(OrderDTO orderDto, List<OrderDTO> orders) {

		ResultVO rv = new ResultVO();
		
		if(orders.get(0).getTranQuantity() >= orderDto.getTranQuantity()) {
			rv.setGetQuantity(orders.get(0).getTranQuantity());
			rv.setBreakIndex(0);
			return rv;
		}
		
		
		// 수량 총 합을 계산하기위한 변수
		AtomicInteger quantity = new AtomicInteger(0);
		
		int breakIndex = IntStream.range(0, orders.size())
				.takeWhile(i -> quantity.addAndGet(orders.get(i).getTranQuantity()) < orderDto.getTranQuantity()) //false면 그만둔다
				.reduce((a, b) -> b)
				.orElse(orders.size() - 1);
		
//		for(int i=0;i<getOrder.size();i++){
//			getQuantity += getOrder.get(i).getTranQuantity();
//			//가져온 수량의 합 >= 주문한 수량
//			if(getQuantity >= orderDto.getTranQuantity()) {
//				idx = i;
//				break;
//			}
//			idx = i;
//		}
//		
		rv.setGetQuantity(quantity.get());
		//주문이 존재하지 않다면 0 / 수량의 총합이 부족하면 breakIndex / 수량의 총합을 충족하면 breakIndex +1(takeWhile에서 false인 경우에 빠져나와서 +1을 해준다)
		rv.setBreakIndex(orders.size() == 0 ? 0 : breakIndex == orders.size() -1 ? breakIndex : breakIndex+1);
		
		return rv;
	}
	
	public void insertHoldCoin(ParameterDTO param) {
		coinMapper.insertHoldCoin(HoldCoinDTO.builder()
				.userNumber(param.getOrderDto().getUserNumber())
				.coinCode(param.getOrderDto().getCoinCode())
				.coinQuantity(param.getBreakIndex() != 0 && param.getBreakIndex() == param.getOrders().size()-1 ? param.getOrderDto().getTranQuantity() : param.getQuantity()) //idx == orders.size()-1 => for문을 다 돌았는데 수량이 충족하지 못했다.
				.build());
	};
	
	public void updateOrder(ParameterDTO param) {
		
		// 주문 테이블에 수량이 1개(row)만 존재하는 경우
		if (param.getBreakIndex() == 0) {					// 주문 테이블에 존재하는 수량의 합 >= 주문 수량
			param.getOrders().get(0).setTranQuantity(param.getQuantity() >= param.getOrderDto().getTranQuantity() ? param.getOrderDto().getTranQuantity() : param.getQuantity());
			orderMapper.updateOrder(param.getOrders().get(0));
		}
		else {
			int[] secondQuantity = {0};
			
			// 주문 테이블에 수량이 많은 경우(주문자가 많은 경우)
			IntStream.rangeClosed(0, param.getBreakIndex())
					.forEach(i -> {
				        if (i == param.getBreakIndex() && param.getQuantity() >= param.getOrderDto().getTranQuantity()) {
							param.getOrders().get(i).setTranQuantity(param.getOrderDto().getTranQuantity() - secondQuantity[0]);
						}
						secondQuantity[0] += param.getOrders().get(i).getTranQuantity();
						
						orderMapper.updateOrder(param.getOrders().get(i));
					});
			
//			for (int i = 0; i <= param.getBreakIndex(); i++) {
//				// 마지막 주문 && 주문 테이블에 존재하는 수량의 합 >= 주문 수량
//				if (i == param.getBreakIndex() && param.getQuantity() >= param.getOrderDto().getTranQuantity()) {
//					param.getOrders().get(i).setTranQuantity(param.getOrderDto().getTranQuantity() - getSecondQuantity);
//				}
//				getSecondQuantity += param.getOrders().get(i).getTranQuantity();
//				
//				orderMapper.updateOrder(param.getOrders().get(i));
//			}
		}
		
		param.getAccountDto().setBalance(BigDecimal.valueOf(param.getOrderDto().getTranQuantity() * param.getOrderDto().getTranAmount().intValue()));
		
		// order 수량보다 주문 테이블에 적게 존재한다면 주문 테이블에 존재하는 수량 만큼은 제외하고 남은 수량은 주문 테이블에 등록
		if (param.getBreakIndex() != 0 && param.getBreakIndex() == param.getOrders().size()-1 && param.getOrderDto().getTranQuantity() > param.getQuantity()) {
			param.getOrderDto().setTranQuantity(param.getQuantity() >= param.getOrderDto().getTranQuantity() ? param.getQuantity() - param.getOrderDto().getTranQuantity() : param.getOrderDto().getTranQuantity() - param.getQuantity());

			orderMapper.insertOrder(param.getOrderDto());
		}
	}
	
	public void updateBalance(ParameterDTO param) {
		//										01 매수 02 매도									매수인 경우에는 잔액 차감 : 매도인 경우에는 자낵 증가
		param.getAccountDto().setBalance(param.getOrderDto().getBuySellCode().equals("01") ? param.getAccountDto().getBalance() : param.getAccountDto().getBalance().multiply(BigDecimal.valueOf(-1)));
		
		//내 계좌 잔액 변경
		accountMapper.updateBalance(param.getAccountDto());
		
		// 내가 매수를 했다면 매도한 유저의 계좌 잔액 변경
		IntStream.rangeClosed(0, param.getBreakIndex())
		.forEach(i -> {
			BigDecimal balance = BigDecimal.valueOf(param.getOrders().get(i).getTranQuantity() * param.getOrders().get(i).getTranAmount().intValue());
			
			param.getAccountDto().setUserNumber(param.getOrders().get(i).getUserNumber());
			param.getAccountDto().setBalance(param.getOrderDto().getBuySellCode().equals("01") ? balance.multiply(BigDecimal.valueOf(-1)) : balance);
			
			accountMapper.updateBalance(param.getAccountDto());
		});
		
//		for(int i=0; i<=param.getBreakIndex(); i++){
//			BigDecimal balance = BigDecimal.valueOf(param.getOrders().get(i).getTranQuantity() * param.getOrders().get(i).getTranAmount().intValue());
//			
//			param.getAccountDto().setUserNumber(param.getOrders().get(i).getUserNumber());
//			param.getAccountDto().setBalance(param.getOrderDto().getBuySellCode().equals("01") ? balance.multiply(BigDecimal.valueOf(-1)) : balance);
//			
//			accountMapper.updateBalance(param.getAccountDto());
//		}
		
	}
	
//	@Transactional(propagation = Propagation.NESTED, noRollbackFor = SQLException.class)
//	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = SQLException.class)
	public void insertTradeHistory(OrderDTO orderDto) {
		
		OrderRunnable orderRunnable = new OrderRunnable(tradeHistoryMapper, orderDto); 
		Thread thread = new Thread(orderRunnable);
		thread.start();
		
		thread.interrupt();
	}
	
	
	public AccountDTO accountBuilder(OrderDTO orderDto) {
		return AccountDTO.builder()
						.userNumber(orderDto.getUserNumber())
						.balance(orderDto.calculateTotalTransactionAmount())
						.build();
	}
}
