package com.coin.batch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@SpringBootApplication
//@EnableScheduling
public class AccountBatch {
	
	//@Scheduled(cron="0 0 2 * * ?")
	public void execute() {
		
//		List<Map<String, Object>> rows = sqlSession.selectList("com.coin.account.mapper.AccountMapper.withoutAccounts");
//		
//		for(Map<String, Object> row : rows) {
//        	int userNumber = ((BigDecimal)row.get("USER_NUMBER")).intValue();
//    		sqlSession.insert("com.coin.account.mapper.AccountMapper.addAccount", userNumber);
//		}
//        
    }
	
}