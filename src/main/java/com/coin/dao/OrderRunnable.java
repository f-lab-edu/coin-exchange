package com.coin.dao;

import java.util.stream.IntStream;

import com.coin.dto.OrderDTO;
import com.coin.dto.TradeHistoryDTO;
import com.coin.mapper.AccountMapper;
import com.coin.mapper.TradeHistoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRunnable implements Runnable {
	
	private TradeHistoryMapper tradeHistoryMapper;
	private OrderDTO orderDto;
	
	public OrderRunnable(TradeHistoryMapper tradeHistoryMapper, OrderDTO orderDto) {
		this.tradeHistoryMapper = tradeHistoryMapper;
		this.orderDto = orderDto;
	}
	
	@Override
	public void run() {
		log.error("---------------5>>>>>>"+Thread.currentThread().getName());

		tradeHistoryMapper.insertTradeHistory(TradeHistoryDTO.builder()
				.userNumber(orderDto.getUserNumber())
				.coinCode(orderDto.getCoinCode())
				.tranAmount(orderDto.getTranAmount())
				.buySellCode(orderDto.getBuySellCode())
				.build());
		
		Thread.currentThread().interrupt();
	}
}
