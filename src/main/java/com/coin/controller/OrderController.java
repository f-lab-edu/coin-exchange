package com.coin.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coin.ResultVO;
import com.coin.service.AccountService;
import com.coin.service.OrderBuilder;
import com.coin.service.OrderDTO;
import com.coin.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;
	private ResultVO result;
	
	public OrderController(OrderService orderService, AccountService accountService, ResultVO result) {
        this.orderService = orderService;
        this.result = result;
    }
	
	@GetMapping
	@PostMapping
	public ResultVO addOrder(HttpServletRequest request) {
		//Bean validation
		OrderDTO orderDto = orderBuilder(request);
		
		int addResult = orderService.addOrder(orderDto);
		
		result.setMsg(addResult != 0 ? "success" : "잔액이 부족합니다");
		result.setResult(addResult);
		
		return result;
	}
	
	//거래 상세정보
	@GetMapping("/{id}")
	public void selectOrder(@PathVariable int userNumber) {
	}
	//거래 내용 변경
	@PutMapping("/{id}")
	public void updateOrder() {
	}
	//거래정보 삭제
	@DeleteMapping("/{id}")
	public void deleteOrder() {
	}
	
	public OrderDTO orderBuilder(HttpServletRequest request) {
		int userNumber = 0;
		int coinCode = 0;
		String buySellCode = "";
		int tranQuantity = 0;
		int tranAmount = 0;
		
		if(request.getParameter("userNumber") != null) {
			userNumber = Integer.parseInt(request.getParameter("userNumber"));
		}
		if(request.getParameter("coinCode") != null) {
			coinCode = Integer.parseInt(request.getParameter("coinCode"));
		}
		if(request.getParameter("buySellCode") != null) {
			buySellCode = request.getParameter("buySellCode");
		}
		if(request.getParameter("tranQuantity") != null) {
			tranQuantity = Integer.parseInt(request.getParameter("tranQuantity"));
		}
		if(request.getParameter("tranAmount") != null) {
			tranAmount = Integer.parseInt(request.getParameter("tranAmount"));
		}
		
		userNumber = 231;
		coinCode = 101;
		buySellCode = "01";
		tranQuantity = 2;
		tranAmount = 100;
		
		OrderDTO orderDto = new OrderBuilder()
							.userNumber(userNumber)
							.coinCode(coinCode)
							.buySellCode(buySellCode)
							.tranQuantity(tranQuantity)
							.tranAmount(BigDecimal.valueOf(tranAmount))
							.build();
		
		return orderDto;
	}
}
