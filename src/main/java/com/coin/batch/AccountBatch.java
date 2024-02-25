package com.coin.batch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class AccountBatch {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
//	@Scheduled(fixedDelay = 1000)
	@Scheduled(cron="0 0 2 * * ?")
	public void execute() {
		String sql = "SELECT U.USER_NUMBER FROM TB_USER U LEFT JOIN ACCOUNT A ON U.USER_NUMBER = A.USER_NUMBER "
				+ "WHERE A.USER_NUMBER IS NULL ORDER BY USER_NUMBER";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        for(Map<String, Object> row : rows) {
        	Object userNumber = row.get("USER_NUMBER");
            System.out.println("USER_NUMBER: " + userNumber);
            sql = "INSERT INTO account (ACCOUNT, USER_NUMBER) "
					+ "VALUES ((select \r\n"
					+ "    case SUBSTR(REPLACE(account,'-',''), -4) \r\n"
					+ "        when '9999' then SUBSTR(account, 0,5) || LPAD(SUBSTR(account,6,2)+1,2,0) || '-0001'\r\n"
					+ "        else SUBSTR(account, 0, 8) || LPAD(SUBSTR(account,9,4)+1,4,0)\r\n"
					+ "end \r\n"
					+ "from account where REPLACE(account,'-','') = (select max(REPLACE(account,'-','')) from account)), ?)";
			jdbcTemplate.update(sql, userNumber);
        }
        
        
    }
	
}