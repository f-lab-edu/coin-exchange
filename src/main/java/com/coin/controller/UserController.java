package com.coin.controller;

import java.util.Random;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coin.AES256Cipher;
import com.coin.ResultVO;
import com.coin.service.UserBuilder;
import com.coin.service.UserDTO;
import com.coin.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private ResultVO result;
	private Random random = new Random();
	
	public UserController(UserService userService, ResultVO result) {
		this.userService = userService;
		this.result = result;
    }
	
	@GetMapping
	@PostMapping
	public ResultVO addUser(HttpServletRequest request) {
		//Bean validation
		UserDTO userDTO = userBuilder(request);
		
		int addResult = userService.addUser(userDTO);
		
		result.setMsg(addResult != 0 ? "success" : "fail");
		result.setResult(addResult);

		return result;
	}
	
	@PutMapping("/{id}")
	public void updateUser() {
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser() {
	}
	
	public UserDTO userBuilder(HttpServletRequest request) {
		String userId = "";
		String password = "";
		String address = "";
		String name = "";
		String handPhone = "";
		
		if(request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		if(request.getParameter("address") != null) {
			address = request.getParameter("address");
		}
		if(request.getParameter("name") != null) {
			name = request.getParameter("name");
		}
		if(request.getParameter("handPhone") != null) {
			handPhone = request.getParameter("handPhone");
		}
		userId = "ksj" + random.nextInt();
		password = AES256Cipher.aesEncode("ksj");
		address = "서울시";
		name = "김승주";
		handPhone = "01012345678";
		
		UserDTO userDTO = new UserBuilder()
				.userId(userId)
				.password(password)
				.address(address)
				.name(name)
				.handPhone(handPhone)
				.build();
		
		return userDTO;
	}
}
