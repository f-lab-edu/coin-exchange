package com.coin.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
	private String coinCode;			//코인 코드
	private int coinQuantity;			//코인 개수
	private Timestamp tranTime;			//거래 시간
	
	private int breakIndex;
	private int calculateQuantity;
	private int orderSize;
	
	@Builder
	public HoldCoinDTO(int userNumber, String coinCode, int tranQuantity, int breakIndex, int calculateQuantity, int orderSize) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.coinQuantity = breakIndex != 0 && breakIndex == orderSize-1 ? tranQuantity : calculateQuantity;
	}
	

}
