package com.coin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.coin.mapper.AccountMapper;
import com.coin.mapper.CoinMapper;
import com.coin.mapper.UserMapper;
import com.coin.dto.HoldCoinDTO;
import com.coin.dto.OrderDTO;

@Repository("coinDao")
public class CoinDao {
	
	private CoinMapper coinMapper;
	
	public CoinDao(CoinMapper coinMapper) {
		this.coinMapper = coinMapper;
	}
	
	public int insertHoldCoin(HoldCoinDTO holdCoinDto)  {
		return coinMapper.insertHoldCoin(holdCoinDto);
	}
}

