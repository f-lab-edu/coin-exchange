package com.coin.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import com.coin.dao.AccountRunnable;
import com.coin.dto.AccountDTO;
import com.coin.mapper.AccountMapper;

@Service("accountService")
public class AccountService {
	
	private AccountMapper accountMapper;
	
	public AccountService(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	
	public void addAccount(AccountDTO accountDto) {
		
		//새로운 사용자가 회원가입을 할때마다 비동기로 계좌를 생성하는데 매번 새로운 쓰레드를 생성해야하나?
		AccountRunnable accountRunnable = new AccountRunnable(accountMapper, accountDto.getUserNumber());
		Thread thread = new Thread(accountRunnable);
		thread.start();
		
		thread.interrupt();
	}
	
	public int getBalance(int userNumber) {
		return accountMapper.getBalance(userNumber);
	}
}

