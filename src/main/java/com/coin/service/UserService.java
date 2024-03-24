package com.coin.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import com.coin.service.UserService;
import com.coin.dao.AccountRunnable;
import com.coin.dto.UserDTO;
import com.coin.mapper.AccountMapper;
import com.coin.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserService {
	
	private UserMapper userMapper;
	private AccountMapper accountMapper;
	
	public UserService(UserMapper userMapper, AccountMapper accountMapper) {
		this.userMapper = userMapper;
		this.accountMapper = accountMapper;
	}
	
	public int addUser(UserDTO userDto) {
		
		int userNumber = 0;
		
		try {
			userNumber = userMapper.addUser(userDto);
			
			AccountRunnable accountRunnable = new AccountRunnable(accountMapper, userNumber);
			Thread thread = new Thread(accountRunnable);
			thread.start();
			
			thread.interrupt();
		} catch (Exception e) {
			log.error("addUser exception error", e.getStackTrace()[0]);
		}

		return userNumber;
	}
	
}

