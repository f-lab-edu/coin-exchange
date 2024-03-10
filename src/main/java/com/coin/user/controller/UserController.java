package com.coin.user.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coin.account.service.AccountService;
import com.coin.user.service.UserDTO;
import com.coin.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private AccountService accountService;
	
	public UserController(UserService userService, AccountService accountService) {
		this.userService = userService;
		this.accountService = accountService;
    }
	
	@GetMapping
	@PostMapping
	@ResponseBody
	public HashMap<String, String> addUser() {
		
		int addResult = userService.addUser(new UserDTO());
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("msg", addResult != 0 ? "success" : "fail");
		map.put("result", addResult+"");

		return map;
	}
	
	@PutMapping("/{id}")
	public void updateUser() {
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser() {
	}
}
