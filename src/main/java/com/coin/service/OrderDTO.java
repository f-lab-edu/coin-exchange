package com.coin.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDTO {
	
	private int orderSeq;				//주문 일련번호
	private int userNumber;				//회원 번호
	private int coinCode;				//코인 코드
	private String buySellCode;			//매수 매도 구분 코드
	private int tranQuantity;			//거래 수량
	private BigDecimal tranAmount;		//거래 금액
	private int tranEndYn;				//거래 종료 여부
	private Timestamp tranTime;			//거래 시간
	
	public OrderDTO(int userNumber, int coinCode, String buySellCode, int tranQuantity, BigDecimal tranAmount) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.buySellCode = buySellCode;
		this.tranQuantity = tranQuantity;
		this.tranAmount = tranAmount;
	}
	
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(int coinCode) {
		this.coinCode = coinCode;
	}
	public String getBuySellCode() {
		return buySellCode;
	}
	public void setBuySellCode(String buySellCode) {
		this.buySellCode = buySellCode;
	}
	public int getTranQuantity() {
		return tranQuantity;
	}
	public void setTranQuantity(int tranQuantity) {
		this.tranQuantity = tranQuantity;
	}
	public BigDecimal getTranAmount() {
		return tranAmount;
	}
	public void setTranAmount(BigDecimal tranAmount) {
		this.tranAmount = tranAmount;
	}
	public int getTranEndYn() {
		return tranEndYn;
	}
	public void setTranEndYn(int tranEndYn) {
		this.tranEndYn = tranEndYn;
	}
	public Timestamp getTranTime() {
		return tranTime;
	}
	public void setTranTime(Timestamp tranTime) {
		this.tranTime = tranTime;
	}
	@Override
	public String toString() {
		return "OrderDTO [orderSeq=" + orderSeq + ", userNumber=" + userNumber + ", coinCode=" + coinCode
				+ ", buySellCode=" + buySellCode + ", tranQuantity=" + tranQuantity + ", tranAmount=" + tranAmount
				+ ", tranEndYn=" + tranEndYn + ", tranTime=" + tranTime + "]";
	}
	
}
