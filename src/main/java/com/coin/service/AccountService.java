package com.coin.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import com.coin.dao.AccountRunnable;
import com.coin.dto.AccountDTO;
import com.coin.dto.OrderDTO;
import com.coin.mapper.AccountMapper;

@Service("accountService")
public class AccountService {

  private AccountMapper accountMapper;

  public AccountService(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

  public void addAccount(AccountDTO accountDto) {

    // 새로운 사용자가 회원가입을 할때마다 비동기로 계좌를 생성하는데 매번 새로운 쓰레드를 생성해야하나?
    AccountRunnable accountRunnable =
        new AccountRunnable(accountMapper, accountDto.getUserNumber());
    Thread thread = new Thread(accountRunnable);
    thread.start();

    thread.interrupt();
  }

  public int getBalance(int userNumber) {
    return accountMapper.getBalance(userNumber);
  }

  public void updateBalance(AccountDTO accountDto, List<OrderDTO> orders) {
    
    // 내 계좌 잔액 변경
    accountMapper.updateBalance(accountDto);

    // 내가 매수를 했다면 매도한 유저의 계좌 잔액 변경
    IntStream.rangeClosed(0, accountDto.getBreakIndex())
        .filter(i -> orders.get(i).getBuySellCode().equals("02"))
        .forEach(i -> {
          BigDecimal balance = BigDecimal
              .valueOf(orders.get(i).getTranQuantity() * orders.get(i).getTranAmount().intValue());
          
          accountDto.setUserNumber(orders.get(i).getUserNumber());
          accountDto.setBalance(balance.multiply(BigDecimal.valueOf(-1)));

          accountMapper.updateBalance(accountDto);
        });

    // for(int i=0; i<=param.getBreakIndex(); i++){
    // BigDecimal balance = BigDecimal.valueOf(param.getOrders().get(i).getTranQuantity() *
    // param.getOrders().get(i).getTranAmount().intValue());
    //
    // param.getAccountDto().setUserNumber(param.getOrders().get(i).getUserNumber());
    // param.getAccountDto().setBalance(param.getOrderDto().getBuySellCode().equals("01") ?
    // balance.multiply(BigDecimal.valueOf(-1)) : balance);
    //
    // accountMapper.updateBalance(param.getAccountDto());
    // }

  }

  public void updateBalance(AccountDTO accountDto) {
    accountMapper.updateBalance(accountDto);
  }

  public AccountDTO accountBuilder(OrderDTO orderDto, int breakIdx) {
    return AccountDTO.builder().userNumber(orderDto.getUserNumber()).breakIndex(breakIdx)
        .balance(orderDto.getBuySellCode().equals("01") ? orderDto.calculateTotalTransactionAmount()
            : orderDto.calculateTotalTransactionAmount().multiply(BigDecimal.valueOf(-1)))
        .build();
  }
}

