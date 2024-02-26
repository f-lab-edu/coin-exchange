package com.coin.user.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.AES256Cipher;
import com.coin.user.service.UserDto;
import com.coin.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	public static AES256Cipher aes256;
	
	Random random = new Random();
	
//	@PostMapping("/test")
	@RequestMapping("/user/addUser")
	public void addUser() {
		try {
			String userId = "ksj" + random.nextInt();
			String password = "ksj";
			String encodingPassword = aes256.aesEncode(password);
			String address = "서울시";
			String name = "김승주";
			String handPhone = "01012341234";
			
			UserDto user = new UserDto(userId, encodingPassword, address, name, handPhone);
			
			userService.addUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/updateUser")
	public void updateUser() {
	}
	
	@RequestMapping("/user/deleteUser")
	public void deleteUser() {
	}
}
