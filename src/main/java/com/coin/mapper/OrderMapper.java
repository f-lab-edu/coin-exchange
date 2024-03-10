package com.coin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.coin.service.OrderDTO;

@Mapper
public interface OrderMapper {
	
	int addTran(OrderDTO orderDto);

	int addOrder(OrderDTO orderDto);
	
	List<OrderDTO> getOrder(OrderDTO orderDto);
	
	int deleteOrder(OrderDTO orderDto);
	
	int updateOrder(OrderDTO orderDto);
	
}
