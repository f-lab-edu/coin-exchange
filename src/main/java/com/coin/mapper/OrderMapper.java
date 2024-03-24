package com.coin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.coin.dto.OrderDTO;

@Mapper
public interface OrderMapper {
	
	int insertOrder(OrderDTO orderDto);
	
	List<OrderDTO> findByUserNumberAndTranAmountAndCoinCodeAndBuySellCode(OrderDTO orderDto);
	
	int deleteOrder(OrderDTO orderDto);
	
	int updateOrder(OrderDTO orderDto);
}
