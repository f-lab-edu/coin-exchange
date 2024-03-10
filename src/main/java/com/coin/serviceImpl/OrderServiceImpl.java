package com.coin.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coin.service.AccountBuilder;
import com.coin.service.AccountDTO;
import com.coin.service.HoldCoinBuilder;
import com.coin.service.HoldCoinDTO;
import com.coin.service.OrderDTO;
import com.coin.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao;
	private AccountDao accountDao;
	private CoinDao coinDao;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public OrderServiceImpl(OrderDao orderDao, AccountDao accountDao, CoinDao coinDao) {
		this.orderDao = orderDao;
		this.accountDao = accountDao;
		this.coinDao = coinDao;
	}
	
	//주문 -> 계좌 금액 확인(accountService.getBalance(userNumber)) 없으면 종료 있으면-> 주문테이블 확인 (매도면 구분이 매수인 종목만 확인)(orderService.getOrder(userNumber, coinCode, buySellCode)) 
	//있으면 거래 진행-> 보유코인 테이블에 입력, 거래이력 테이블에 입력, 계좌 테이블(잔액) 수정(내거와 상대방거), 주문테이블(해당 코인) 삭제
	//없으면-> 주문테이블에 입력, 계좌 테이블(잔액) 수정
	//내가 매도 주문을 했는데 이미 매도 주문을 한 건수가 존재할수도있다
	
	public int addOrder(OrderDTO orderDto) {
		//계좌의 잔액 조회
		int getBalance = accountDao.getBalance(orderDto.getUserNumber());
		//입력한 수량과 금액의 합
		int orderBalance = orderDto.getTranQuantity()*orderDto.getTranAmount().intValue();
		//잔액이 입력한 수량과 금액의 합보다 크거나 같은 경우에만 진행
		if(getBalance >= orderBalance) {
			
			List<OrderDTO> getOrder = orderDao.getOrder(orderDto); // 해당 row값을 가져오도록 변경하고 수량과 상대방 정보(회원번호)가 필요하다
			
			//수량이 존재하지 않으면 주문 테이블에 입력 // 배치로 오후 4시에 주문테이블을 비운다(개발 예정)
			if(getOrder == null) {
				//로그에 입력과 주문 테이블에 입력 동시 진행
				orderDao.addOrder(orderDto);
				
				AccountDTO accountDto = new AccountBuilder()
						.userNumber(orderDto.getUserNumber())
						.balance(BigDecimal.valueOf(orderBalance))
						.build();
				
				accountDao.decreaseBalance(accountDto); // 수령이 존재하든 존재하지 않던 중복일거임
			} 

			//있으면 거래 진행-> 1보유코인 테이블에 등록(입력), 2주문테이블(해당 코인) 삭제, 3계좌 테이블(잔액) 수정(내거와 상대방거), 4거래이력 테이블에 입력, 
			// 수량 존재
			else {
				int idx = 0;
				//수량 총 합을 계산하기위한 변수 
				int getQuantity = 0;
				//수량이 맞지 않은 경우 마지막에서 2번째 총 합을 계산하기위한 변수 
				int getSecondQuantity = 0;
				
				boolean isFulfilled = false;
				// 매도면 매도 수량의 합이 내가 주문한 수량보다 많으면 종료 
				for(int i=0;i<getOrder.size();i++){
					getQuantity += getOrder.get(i).getTranQuantity();
					getSecondQuantity = getQuantity;
					//가져온 수량의 합 >= 주문한 수량
					if(getQuantity >= orderDto.getTranQuantity()) {
						idx = i;
						isFulfilled = true;
						break;
					}
					idx = i;
				}
				/*
				 * 보유코인 테이블에 등록(입력) 시작
				 * */
				//기본 - 충족한 경우
				HoldCoinDTO holdCoinDto  = new HoldCoinBuilder()
						.userNumber(orderDto.getUserNumber())
						.coinCode(orderDto.getCoinCode())
						.coinQuantuty(orderDto.getTranQuantity())
						.build();
				
				//수량이 불충분한 경우
				if(!isFulfilled) {
					holdCoinDto.setCoinQuantity(getQuantity);
				}
				
				coinDao.addHoldCoin(holdCoinDto);
				/*
				 * 보유코인 테이블에 등록(입력) 종료
				 * */
				
				/*
				 * 주문테이블(해당 코인) 삭제 시작
				 * */
				for(int i=0;i<=idx;i++){
					//마지막 순서 && 내가 주문한 수량과 가져온 총 합의 수량이 같지 않으면
					if(i == idx && orderDto.getTranQuantity() != getQuantity) {
						//마지막 수량 = 부족한 수량 = 내가 주문한 수량-마지막을 제외한 수량들의 합 = 마지막 사람의 주문에서 빼야하는 수량
						orderDto.setTranQuantity(orderDto.getTranQuantity()-getSecondQuantity);
						getOrder.set(i, orderDto);
						orderDao.updateOrder(getOrder.get(i));
					} else {
						orderDao.deleteOrder(getOrder.get(i));
					}
				}

				/*
				 * 주문테이블(해당 코인) 삭제 종료
				 * */
				
				/*
				 * 상대방과 나의 계좌에서 잔액을 변경 시작
				 * */
				/*
				 * 상대방과 나의 계좌에서 잔액을 변경 종료
				 * */
				/*
				 * 거래이력 테이블에 입력 시작
				 * */
				/*
				 * 거래이력 테이블에 입력 종료
				 * */
			}
			
			//내가 매도 주문을 했는데 이미 매도 주문을 한 건수가 존재할수도있다
			
			//coinDao.updateHoldCoin(orderDto.getUserNumber());
		}
		return getBalance;
	}
	
}

