package com.coin.dto;

import java.math.BigDecimal;

import jakarta.servlet.http.HttpServletRequest;

public class BuilderDirector {
	
	private HttpServletRequest request;
	
	public BuilderDirector(HttpServletRequest request) {
		this.request = request;
	}
	
	public OrderDTO orderBuilder() {
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
		tranQuantity = 2;
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
