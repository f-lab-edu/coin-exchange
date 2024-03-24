package com.coin.dao;

import org.springframework.stereotype.Repository;
import com.coin.mapper.AccountMapper;
import com.coin.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("accountDao")
public class AccountDao {
	
	private AccountMapper accountMapper;
	
	public AccountDao(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
	public boolean addAccount(int userNumber)  {
		try {
			Thread.sleep(5000); //5초 대기
			//insertAccount
			return accountMapper.addAccount(userNumber) == 1? true: false;
		} catch (InterruptedException e) {
			log.error("Thread sleep error");
			return false;
		}
	}
	
	public int getBalance(int userNumber) {
		//findByUserNumber
		return accountMapper.getBalance(userNumber);
	}
	
	public int updateBalance(AccountDTO accountDto)  {
		return accountMapper.updateBalance(accountDto);
	}
	
}

