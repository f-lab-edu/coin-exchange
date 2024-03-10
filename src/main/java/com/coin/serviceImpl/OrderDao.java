package com.coin.serviceImpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.coin.mapper.OrderMapper;
import com.coin.service.OrderDTO;

@Repository("orderDao")
public class OrderDao {
	
	private OrderMapper orderMapper;
	
	public OrderDao(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	public int addOrder(OrderDTO orderDto)  {
		orderMapper.addTran(orderDto);
		return orderMapper.addOrder(orderDto);
	}
	
	public List<OrderDTO> getOrder(OrderDTO orderDto)  {
		List<OrderDTO> getOrder = orderMapper.getOrder(orderDto);
		return getOrder.size() == 0 ? null : getOrder;
	}
	
	public int deleteOrder(OrderDTO orderDto)  {
		return orderMapper.deleteOrder(orderDto);
	}
	
	public int updateOrder(OrderDTO orderDto)  {
		return orderMapper.updateOrder(orderDto);
	}
	
}

