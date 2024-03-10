package com.coin.account.controller;

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

import com.coin.account.service.AccountDTO;
import com.coin.account.service.AccountService;
import com.coin.user.service.UserDTO;
import com.coin.user.service.UserService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
	
	@PostMapping
	public void addAccount(int userNumber) {
		accountService.addAccount(new AccountDTO(userNumber));
	}

	@GetMapping("/{id}")
	public void selectAccount() {
	}
	
	@PutMapping("/{id}")
	public void updateAccount() {
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount() {
	}
}
