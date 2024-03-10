package com.coin.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountRunnable implements Runnable {
	
	private AccountDao accountDao;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int maxRetry = 5;
	private int userNumber;
	
	public AccountRunnable(AccountDao accountDao, int userNumber) {
		this.accountDao = accountDao;
		this.userNumber = userNumber;
	}
	
	@Override
	public void run() {
		
		boolean retry = false;
		try {
			retry = accountDao.addAccount(userNumber);
		} catch (Exception e) {
			log.error("addAccount exception error", e.getStackTrace()[0]);
		}
		if(!retry) {
			for (int i = 0; i < maxRetry; i++) {
	        	retry = accountDao.addAccount(userNumber);
				if(retry) {
					break;
				}
			}
		}		
		Thread.currentThread().interrupt();
	}
}
