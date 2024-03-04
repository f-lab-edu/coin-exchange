package com.coin.account.serviceimpl;

import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import com.coin.account.service.AccountDTO;
import com.coin.account.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public void addAccount(AccountDTO accountDto) {
		
		//새로운 사용자가 회원가입을 할때마다 비동기로 계좌를 생성하는데 매번 새로운 쓰레드를 생성해야하나?
		AccountRunnable accountRunnable = new AccountRunnable(accountDao, accountDto.getUserNumber());
		Thread thread = new Thread(accountRunnable);
		thread.start();
		
		thread.interrupt();
	}
	
}

