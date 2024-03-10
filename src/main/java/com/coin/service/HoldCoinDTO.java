package com.coin.service;

import java.sql.Timestamp;

public class HoldCoinDTO {
	
	private int holdCoinSeq;			//보유코인 일련번호
	private int userNumber;				//회원 번호
	private int coinCode;				//코인 코드
	private int coinQuantity;			//코인 개수
	private Timestamp tranTime;			//거래 시간
	
	public HoldCoinDTO(int userNumber, int coinCode, int coinQuantity) {
		this.userNumber = userNumber;
		this.coinCode = coinCode;
		this.coinQuantity = coinQuantity;
	}

	public int getHoldCoinSeq() {
		return holdCoinSeq;
	}

	public void setHoldCoinSeq(int holdCoinSeq) {
		this.holdCoinSeq = holdCoinSeq;
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

	public int getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(int coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public Timestamp getTranTime() {
		return tranTime;
	}

	public void setTranTime(Timestamp tranTime) {
		this.tranTime = tranTime;
	}

	@Override
	public String toString() {
		return "HoldCoinDTO [holdCoinSeq=" + holdCoinSeq + ", userNumber=" + userNumber + ", coinCode=" + coinCode
				+ ", coinQuantity=" + coinQuantity + ", tranTime=" + tranTime + "]";
	}

}
