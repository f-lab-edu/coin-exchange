package com.coin.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.coin.account.service.AccountDTO;

@Mapper
public interface AccountMapper {
	@Insert("INSERT INTO account (ACCOUNT, USER_NUMBER) VALUES ((select	case SUBSTR(REPLACE(account,'-',''), -4) when '9999' then SUBSTR(account, 0,5) || LPAD(SUBSTR(account,6,2)+1,2,0) || '-0001' else SUBSTR(account, 0, 8) || LPAD(SUBSTR(account,9,4)+1,4,0) end from account where REPLACE(account,'-','') = (select max(REPLACE(account,'-','')) from account)), #{userNumber})")
	int addAccount(AccountDTO accountDto);
	
	@Select("SELECT U.USER_NUMBER FROM TB_USER U LEFT JOIN ACCOUNT A ON U.USER_NUMBER = A.USER_NUMBER WHERE A.USER_NUMBER IS NULL ORDER BY USER_NUMBER")
	List<Map<String, Object>> withoutAccounts();
	
}
