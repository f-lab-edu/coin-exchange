package com.coin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coin.dto.HoldCoinDTO;

@Mapper
public interface CoinMapper {
	
	int insertHoldCoin(HoldCoinDTO holdCoinDto);
}
