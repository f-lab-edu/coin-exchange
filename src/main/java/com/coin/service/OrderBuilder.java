package com.coin.service;

import java.math.BigDecimal;

public class OrderBuilder {
	private int userNumber;				//회원 번호
	private int coinCode;				//코인 코드
	private String buySellCode;			//매수 매도 구분 코드
	private int tranQuantity;			//거래 수량
	private BigDecimal tranAmount;		//거래 금액
	
	public OrderBuilder userNumber(int userNumber) {
		this.userNumber = userNumber;
		return this;
	}

	public OrderBuilder coinCode(int coinCode) {
		this.coinCode = coinCode;
		return this;
	}

	public OrderBuilder buySellCode(String buySellCode) {
		this.buySellCode = buySellCode;
		return this;
	}
	
	public OrderBuilder tranQuantity(int tranQuantity) {
		this.tranQuantity = tranQuantity;
		return this;
	}

    public OrderBuilder tranAmount(BigDecimal tranAmount) {
    	this.tranAmount = tranAmount;
		return this;
    }

    public OrderDTO build() {
        return new OrderDTO(userNumber, coinCode, buySellCode, tranQuantity, tranAmount);
    }
}
