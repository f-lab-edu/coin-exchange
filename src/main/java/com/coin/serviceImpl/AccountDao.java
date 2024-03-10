package com.coin.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.coin.mapper.AccountMapper;
import com.coin.mapper.UserMapper;
import com.coin.service.AccountDTO;
import com.coin.service.OrderDTO;

@Repository("accountDao")
public class AccountDao {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AccountMapper accountMapper;
	
	public AccountDao(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
	public boolean addAccount(int userNumber)  {
		try {
			Thread.sleep(5000); //5초 대기
			return accountMapper.addAccount(userNumber) == 1? true: false;
		} catch (InterruptedException e) {
			log.error("Thread sleep error");
			return false;
		}
	}
	
	public int getBalance(int userNumber) {
		return accountMapper.getBalance(userNumber);
	}
	
	public int decreaseBalance(AccountDTO accountDto)  {
		return accountMapper.decreaseBalance(accountDto);
	}
}

