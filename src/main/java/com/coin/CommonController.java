package com.coin;

import java.util.HashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.coin.account.controller.AccountController;
import com.coin.user.controller.UserController;

@RestController
public class CommonController {

	private UserController userController;
	private AccountController accountController;
	
	public CommonController(UserController userController, AccountController accountController) {
		this.userController = userController;
		this.accountController = accountController;
    }
	
	@GetMapping("/user")
	@PostMapping("/user")
	@ResponseBody
	public HashMap<String, String> addUser() {
		
		HashMap<String, String> addResult = userController.addUser();
		
		if(addResult.get("msg").equals("success"))
			accountController.addAccount(Integer.parseInt(addResult.get("result")));
		//계좌 생성 여부와 관계없이 회원가입의 성공 여부 값은 return
		return addResult;
		
	}
	
	@PutMapping("/user/{id}")
	public void updateUser() {
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser() {
	}
}
