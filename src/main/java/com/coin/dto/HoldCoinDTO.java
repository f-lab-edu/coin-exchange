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
public class HoldCoinDTO {
	
	private int holdCoinSeq;			//보유코인 일련번호
	private int userNumber;				//회원 번호
	private String coinCode;				//코인 코드
	private int coinQuantity;			//코인 개수
	private Timestamp tranTime;			//거래 시간
	
	@Builder
	public HoldCoinDTO(int userNumber, String coinCode, int coinQuantity) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.coinQuantity = coinQuantity;
	}

}
