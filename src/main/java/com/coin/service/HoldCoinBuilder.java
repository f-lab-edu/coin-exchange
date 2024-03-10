package com.coin.service;

import java.sql.Timestamp;

public class HoldCoinBuilder {
	
	private int holdCoinSeq;			//보유코인 일련번호
	private int userNumber;				//회원 번호
	private int coinCode;				//코인 코드
	private int coinQuantuty;			//코인 개수
	private Timestamp tranTime;			//거래 시간
	
	public HoldCoinBuilder holdCoinSeq(int holdCoinSeq) {
		this.holdCoinSeq = holdCoinSeq;
		return this;
	}

	public HoldCoinBuilder userNumber(int userNumber) {
		this.userNumber = userNumber;
		return this;
	}

	public HoldCoinBuilder coinCode(int coinCode) {
		this.coinCode = coinCode;
		return this;
	}
	
	public HoldCoinBuilder coinQuantuty(int coinQuantuty) {
		this.coinQuantuty = coinQuantuty;
		return this;
	}
	
	public HoldCoinBuilder updateTime(Timestamp tranTime) {
		this.tranTime = tranTime;
		return this;
	}
	
    public HoldCoinDTO build() {
        return new HoldCoinDTO(userNumber, coinCode, coinQuantuty);
    }
}
