package com.coin.service;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import com.coin.dto.OrderDTO;
import com.coin.mapper.OrderMapper;

@Service("OrderService")
public class OrderService {

private OrderMapper orderMapper;
	
	public OrderService(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	public List<OrderDTO> findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(OrderDTO orderDto){
		return orderMapper.findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(orderDto);
	}
	
	
	public void insert(OrderDTO orderDto) {
		orderMapper.insertOrder(orderDto);
	}
	
	public void update(OrderDTO orderDto, int breakIndex, int quantity, List<OrderDTO> orders) {
		
		// 주문 테이블에 수량이 1개(row)만 존재하는 경우
		if (breakIndex == 0) {					// 주문 테이블에 존재하는 수량의 합 >= 주문 수량
			orders.get(0).setTranQuantity(quantity >= orderDto.getTranQuantity() ? orderDto.getTranQuantity() : quantity);
			orderMapper.updateOrder(orders.get(0));
		}
		else {
			int[] secondQuantity = {0};
			
			// 주문 테이블에 수량이 많은 경우(주문자가 많은 경우)
			IntStream.rangeClosed(0, breakIndex)
					.forEach(i -> {
				        if (i == breakIndex && quantity >= orderDto.getTranQuantity()) {
							orders.get(i).setTranQuantity(orderDto.getTranQuantity() - secondQuantity[0]);
						}
						secondQuantity[0] += orders.get(i).getTranQuantity();
						
						orderMapper.updateOrder(orders.get(i));
					});
			
		}
		
        // order 수량보다 주문 테이블에 적게 존재한다면 주문 테이블에 존재하는 수량 만큼은 제외하고 남은 수량은 주문 테이블에 등록
		if (breakIndex == orders.size()-1 && orderDto.getTranQuantity() > quantity) {
			orderDto.setTranQuantity(quantity >= orderDto.getTranQuantity() ? quantity - orderDto.getTranQuantity() : orderDto.getTranQuantity() - quantity);

			orderMapper.insertOrder(orderDto);
		}
	}
}
