package com.coin.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.coin.user.service.UserDTO;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO tb_user (USER_NUMBER, USER_ID, PASSWORD, ADDRESS, NAME, HANDPHONE) VALUES (USER_SEQUENCE.NEXTVAL, #{userId}, #{password}, #{address},	#{name}, #{handPhone})") 
	int addUser(UserDTO userDto);

	@Select("SELECT USER_SEQUENCE.CURRVAL FROM DUAL")
	int getUserNumber();
}
