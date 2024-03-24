package com.coin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.coin.dto.HoldCoinDTO;
import com.coin.dto.OrderDTO;
import com.coin.dto.TradeHistoryDTO;
import com.coin.mapper.CoinMapper;

@Service("HoldCoinService")
public class HoldCoinService {

	private CoinMapper coinMapper;

	public HoldCoinService(CoinMapper coinMapper) {
		this.coinMapper = coinMapper;
	}

	public void insert(HoldCoinDTO holdCoinDto) {
		coinMapper.insertHoldCoin(holdCoinDto);
	};

	public HoldCoinDTO holdCoinBuilder(OrderDTO orderDto, int breakIndex, int quantity, List<OrderDTO> orders) {
		return HoldCoinDTO.builder().userNumber(orderDto.getUserNumber()).coinCode(orderDto.getCoinCode())
				.tranQuantity(orderDto.getTranQuantity()).breakIndex(breakIndex)
				.calculateQuantity(quantity).orderSize(orders.size()).build();
	}
}
