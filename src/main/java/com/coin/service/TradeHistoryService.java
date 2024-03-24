package com.coin.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.coin.dao.TradeHistoryRunnable;
import com.coin.dto.OrderDTO;
import com.coin.dto.TradeHistoryDTO;
import com.coin.mapper.TradeHistoryMapper;

@Service("TradeHistoryService")
public class TradeHistoryService {

  private TradeHistoryMapper tradeHistoryMapper;

  public TradeHistoryService(TradeHistoryMapper tradeHistoryMapper) {
    this.tradeHistoryMapper = tradeHistoryMapper;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void insert(TradeHistoryDTO tradeHistoryDto) {

    TradeHistoryRunnable tradeHistoryRunnable =
        new TradeHistoryRunnable(tradeHistoryMapper, tradeHistoryDto);
    Thread thread = new Thread(tradeHistoryRunnable);
    thread.start();
    
    try {
      thread.sleep(500);
      if(thread.isAlive())
        thread.interrupt();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public TradeHistoryDTO tradeHistoryBuilder(OrderDTO orderDto) {
    return TradeHistoryDTO.builder().userNumber(orderDto.getUserNumber())
        .coinCode(orderDto.getCoinCode()).tranAmount(orderDto.getTranAmount())
        .buySellCode(orderDto.getBuySellCode()).build();
  }
}
