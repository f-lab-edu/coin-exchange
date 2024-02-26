package com.coin.user.service;

public interface UserService {
	int addUser(String userId, String password, String address, String name, String handPhone) throws Exception;
}
