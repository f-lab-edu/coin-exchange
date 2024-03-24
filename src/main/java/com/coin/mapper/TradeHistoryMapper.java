package com.coin.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.coin.dto.AccountDTO;
import com.coin.dto.TradeHistoryDTO;

@Mapper
public interface TradeHistoryMapper {
	
	int insertTradeHistory(TradeHistoryDTO tradeHistoryDto);
	
}
