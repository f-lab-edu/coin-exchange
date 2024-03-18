package com.coin.dao;

import java.util.stream.IntStream;

import com.coin.mapper.AccountMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountRunnable implements Runnable {
	
	private AccountMapper accountMapper;
	private final int maxRetry = 5;
	private int userNumber;
	
	public AccountRunnable(AccountMapper accountMapper, int userNumber) {
		this.accountMapper = accountMapper;
		this.userNumber = userNumber;
	}
	
	@Override
	public void run() {
		
		boolean retry = false;
		try {
			retry = accountMapper.addAccount(userNumber) == 1 ? true : false;
		} catch (Exception e) {
			log.error("addAccount exception error", e.getStackTrace()[0]);
		}
		if(!retry) {
			IntStream.range(0, maxRetry)
					.anyMatch(i -> accountMapper.addAccount(userNumber) == 1);
			
//			for (int i = 0; i < maxRetry; i++) {
//	        	retry = accountMapper.addAccount(userNumber) == 1 ? true : false;
//				if(retry) {
//					break;
//				}
//			}
		}		
		Thread.currentThread().interrupt();
	}
}
