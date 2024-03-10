package com.coin.serviceImpl;

import org.springframework.stereotype.Repository;

import com.coin.mapper.UserMapper;
import com.coin.service.UserDTO;

@Repository("userDao")
public class UserDao {
	
	private UserMapper userMapper;
	
	public UserDao(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public int addUser(UserDTO userDto) throws Exception{
		int rtn = userMapper.addUser(userDto);
		if(rtn == 1)
			return userMapper.getUserNumber();
		else
			throw new Exception("user insert Error");
	}
	
}

