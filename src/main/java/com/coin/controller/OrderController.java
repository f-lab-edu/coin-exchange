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
import com.coin.dto.OrderDTO;
import com.coin.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;
	private ResultVO result;
	
	public OrderController(OrderService orderService, ResultVO result) {
        this.orderService = orderService;
        this.result = result;
    }
	
	@GetMapping
	@PostMapping
	public ResultVO addOrder(HttpServletRequest request) {
	        
		//Bean validation
		int addResult = orderService.addOrder(orderBuilder(request));
		
		result.setMsg(addResult != 0 ? "success" : "잔액이 부족합니다");
		result.setResult(addResult);
		
		return result;
	}
	
	//거래 상세정보
	@GetMapping("/{id}")
	//retrieve
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
		String coinCode = "";
		//01 매수 02 매도
		String buySellCode = "";
		int tranQuantity = 0;
		int tranAmount = 0;
		
		//필수로 넣을 값들은 이런식으로 방어가 굳이 필요하지 않을까?
		if(request.getParameter("userNumber") != null) {
			userNumber = Integer.parseInt(request.getParameter("userNumber"));
		}
		if(request.getParameter("coinCode") != null) {
			coinCode = request.getParameter("coinCode");
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
		coinCode = "101";
		buySellCode = "01";
		tranQuantity = 4;
		tranAmount = 100;
		
		return OrderDTO.builder()
				.userNumber(userNumber)
				.coinCode(coinCode)
				.buySellCode(buySellCode)
				.tranQuantity(tranQuantity)
				.tranAmount(BigDecimal.valueOf(tranAmount))
				.build();
	}
}
