package com.coin.service;

public interface AccountService {
	void addAccount(AccountDTO accountDto);
	
	int getBalance(int userNumber);
}
