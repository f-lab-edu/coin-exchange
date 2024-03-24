package com.coin.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.coin.dto.AccountDTO;

@Mapper
public interface AccountMapper {
	
	int addAccount(int userNumber);
	
	List<Map<String, Object>> withoutAccounts();
	
	int getBalance(int userNumber);
	
	int updateBalance(AccountDTO accountDto);
	
}
