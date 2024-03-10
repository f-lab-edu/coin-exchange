package com.coin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coin.service.HoldCoinDTO;

@Mapper
public interface CoinMapper {
	
	int addHoldCoin(HoldCoinDTO holdCoinDto);
}
