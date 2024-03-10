package com.coin.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountBuilder {
	private String accountId;	    	//계좌번호
	private int userNumber;				//회원번호
	private BigDecimal balance;			//잔액
	private Timestamp updateTime;		//계좌정보 수정시간
	
	public AccountBuilder accountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public AccountBuilder userNumber(int userNumber) {
		this.userNumber = userNumber;
		return this;
	}

	public AccountBuilder balance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}
	
	public AccountBuilder updateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
    public AccountDTO build() {
        return new AccountDTO(userNumber, balance);
    }
}
