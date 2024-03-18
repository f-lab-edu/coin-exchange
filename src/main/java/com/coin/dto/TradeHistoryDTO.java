package com.coin.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = false)
public class TradeHistoryDTO {

	private int tradeSeq;				//거래 일련번호
	private int userNumber;				//회원 번호
	private String coinCode;				//코인 코드
	private BigDecimal tranAmount;				//코인 가격
	private String buySellCode;				//코인 수량
	private Timestamp tranTime;			//거래 시간
	
	@Builder
	public TradeHistoryDTO(int userNumber, String coinCode, BigDecimal tranAmount, String buySellCode) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.tranAmount = tranAmount;
		this.buySellCode = buySellCode;
	}

}
