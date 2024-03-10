package com.coin.serviceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.coin.service.UserDTO;
import com.coin.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private AccountDao accountDao;
	
	public UserServiceImpl(UserDao userDao, AccountDao accountDao) {
		this.userDao = userDao;
		this.accountDao = accountDao;
	}
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public int addUser(UserDTO userDto) {
		
		int userNumber = 0;
		
		try {
			userNumber = userDao.addUser(userDto);
			
			AccountRunnable accountRunnable = new AccountRunnable(accountDao, userNumber);
			Thread thread = new Thread(accountRunnable);
			thread.start();
			
			thread.interrupt();
		} catch (Exception e) {
			log.error("addUser exception error", e.getStackTrace()[0]);
		}

		return userNumber;
	}
	
}

