package com.coin.account.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import com.coin.AES256Cipher;

public class AccountDTO {
	private String accountId;	    	//계좌번호
	private int userNumber;				//회원번호
	private BigDecimal balance;			//잔액
	private Timestamp updateTime;		//계좌정보 수정시간
	
	public AccountDTO(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "AccountVO [accountId=" + accountId + ", userNumber=" + userNumber + ", balance=" + balance
				+ ", updateTime=" + updateTime + "]";
	}
	
}
