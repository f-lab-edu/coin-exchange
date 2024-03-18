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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private CoinMapper coinMapper;
	
	public CoinDao(CoinMapper coinMapper) {
		this.coinMapper = coinMapper;
	}
	
	public int addHoldCoin(HoldCoinDTO holdCoinDto)  {
		return coinMapper.addHoldCoin(holdCoinDto);
	}
}

