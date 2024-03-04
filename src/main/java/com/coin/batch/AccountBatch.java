package com.coin.batch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class AccountBatch {
	
	private final SqlSession sqlSession;

    public AccountBatch(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	@Scheduled(cron="0 0 2 * * ?")
	public void execute() {
		
		List<Map<String, Object>> rows = sqlSession.selectList("com.coin.account.mapper.AccountMapper.withoutAccounts");
		
		for(Map<String, Object> row : rows) {
        	int userNumber = ((BigDecimal)row.get("USER_NUMBER")).intValue();
    		sqlSession.insert("com.coin.account.mapper.AccountMapper.addAccount", userNumber);
		}
        
    }
	
}