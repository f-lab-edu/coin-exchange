package com.coin.user.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coin.AES256Cipher;
import com.coin.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	public static AES256Cipher aes256 = new AES256Cipher();
	Random random = new Random();
	
	@RequestMapping("/test")
	public void test() {
		try {
			String userId = "ksj" + random.nextInt();
			String password = "ksj";
			String encodingPassword = aes256.aesEncode(password);
			String address = "서울시";
			String name = "김승주";
			String handPhone = "01012341234";
			
			userService.addUser(userId, encodingPassword, address, name, handPhone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
