package com.coin.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.coin.service.AccountDTO;

@Mapper
public interface AccountMapper {
	
	int addAccount(int userNumber);
	
	List<Map<String, Object>> withoutAccounts();
	
	int getBalance(int userNumber);
	
	int decreaseBalance(AccountDTO accountDto);
}
