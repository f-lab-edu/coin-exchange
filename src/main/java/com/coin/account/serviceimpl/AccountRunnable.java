package com.coin.account.serviceimpl;

public class AccountRunnable implements Runnable {
	
	private AccountDao accountDao;
	private final int maxRetry = 5;
	private int userNumber;
	
	public AccountRunnable(AccountDao accountDao, int userNumber) {
		this.accountDao = accountDao;
		this.userNumber = userNumber;
	}
	
	@Override
	public void run() {
		
		boolean retry = false;
		retry = accountDao.addAccount(userNumber);
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
