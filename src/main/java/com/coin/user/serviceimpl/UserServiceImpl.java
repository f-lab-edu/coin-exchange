package com.coin.user.serviceimpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.coin.account.serviceimpl.AccountRunnable;
import com.coin.user.service.UserDTO;
import com.coin.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public int addUser(UserDTO userDto) {
		
		int userNumber = 0;
		
		try {
			userNumber = userDao.addUser(userDto);
		} catch (Exception e) {
			log.error("exception error", e.getStackTrace()[0]);
		}
		
		return userNumber;
	}
	
}

