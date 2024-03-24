package com.coin.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = false)
public class OrderDTO {
	
	private int orderSeq;				//주문 일련번호
	private int userNumber;				//회원 번호
	private String coinCode;			//코인 코드
	private String buySellCode;			//매수 매도 구분 코드
	private int tranQuantity;			//거래 수량
	private BigDecimal tranAmount;		//거래 금액
	private int tranEndYn;				//거래 종료 여부
	private Timestamp tranTime;			//거래 시간
	
	@Builder
	public OrderDTO(int userNumber, String coinCode, String buySellCode, int tranQuantity, BigDecimal tranAmount) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.buySellCode = buySellCode;
		this.tranQuantity = tranQuantity;
		this.tranAmount = tranAmount;
	}
	
	public BigDecimal calculateTotalTransactionAmount() {
		return BigDecimal.valueOf(this.tranQuantity*this.tranAmount.intValue());
	}
	
}
