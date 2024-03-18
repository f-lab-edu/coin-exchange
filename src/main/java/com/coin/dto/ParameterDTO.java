package com.coin.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterDTO {
	
	private OrderDTO orderDto;
	private int breakIndex;
	private int quantity;
	private List<OrderDTO> orders;
	private AccountDTO accountDto;
	
	@Builder
	public ParameterDTO(OrderDTO orderDto, int breakIndex, int quantity, List<OrderDTO> orders, AccountDTO accountDto) {
		this.orderDto = orderDto;
		this.breakIndex = breakIndex;
		this.quantity = quantity;
		this.orders = orders;
		this.accountDto = accountDto;
	}
	
}
