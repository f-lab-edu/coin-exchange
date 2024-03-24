package com.coin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coin.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	int addUser(UserDTO userDto);

	int getUserNumber();
}
