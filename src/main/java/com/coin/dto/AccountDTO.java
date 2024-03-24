package com.coin.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import com.coin.AES256Cipher;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = false)
public class AccountDTO {
	private String accountId;	    	//계좌번호
	private int userNumber;				//회원번호
	private BigDecimal balance;			//잔액
	private Timestamp updateTime;		//계좌정보 수정시간
	
	private int breakIndex;
	
	@Builder
	public AccountDTO(int userNumber, BigDecimal balance, int breakIndex) {
		this.userNumber = userNumber;
		this.balance = balance;
		this.breakIndex = breakIndex;
	}
}
