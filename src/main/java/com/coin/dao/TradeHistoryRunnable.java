package com.coin.dao;

import com.coin.dto.TradeHistoryDTO;
import com.coin.mapper.TradeHistoryMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TradeHistoryRunnable implements Runnable {

  private TradeHistoryMapper tradeHistoryMapper;
  private TradeHistoryDTO tradeHistoryDto;

  public TradeHistoryRunnable(TradeHistoryMapper tradeHistoryMapper,
      TradeHistoryDTO tradeHistoryDto) {
    this.tradeHistoryMapper = tradeHistoryMapper;
    this.tradeHistoryDto = tradeHistoryDto;
  }

  @Override
  public void run() {
    tradeHistoryMapper.insertTradeHistory(tradeHistoryDto);
    
    Thread.currentThread().interrupt();
  }
}
