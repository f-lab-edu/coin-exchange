package com.coin.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.coin.mapper.OrderMapper;
import com.coin.dto.OrderDTO;

@Repository("orderDao")
public class OrderDao {
	
	private OrderMapper orderMapper;
	
	public OrderDao(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	public int insertOrder(OrderDTO orderDto)  {
		return orderMapper.insertOrder(orderDto);
	}
	
	public List<OrderDTO> findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(OrderDTO orderDto)  {
		List<OrderDTO> getOrder = orderMapper.findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(orderDto);
		return getOrder.size() == 0 ? null : getOrder;
	}
	
	public int deleteOrder(OrderDTO orderDto)  {
		return orderMapper.deleteOrder(orderDto);
	}
	
	public int updateOrder(OrderDTO orderDto)  {
		return orderMapper.updateOrder(orderDto);
	}
	
}

