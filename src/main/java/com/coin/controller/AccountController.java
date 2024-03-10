package com.coin.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coin.service.AccountDTO;
import com.coin.service.AccountService;
import com.coin.service.UserDTO;
import com.coin.service.UserService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
	//계좌 상세정보
	@GetMapping("/{id}")
	public void selectAccount(@PathVariable int userNumber) {
	}
	//계좌 잔액 변경
	@PutMapping("/{id}")
	public void updateAccount() {
	}
	//계좌정보 삭제
	@DeleteMapping("/{id}")
	public void deleteAccount() {
	}
}
