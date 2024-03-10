package com.coin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRunnable implements Runnable {

	@Autowired
	private UserDao userDao;
	
	private String userId;
	private String password;
	private String address;
	private String name;
	private String handPhone;
	
	public UserRunnable(String userId, String password, String address, String name, String handPhone) {
		this.userId = userId;
		this.password = password;
		this.address = address;
		this.name = name;
		this.handPhone = handPhone;
	}
	
	@Override
	public void run() {
		try {
			//userDao.addUser(userId, password, address, name, handPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
