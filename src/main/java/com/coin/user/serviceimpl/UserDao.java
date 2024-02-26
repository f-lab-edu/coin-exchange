package com.coin.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coin.user.service.UserDto;

@Repository("userDao")
public class UserDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public int addUser(UserDto user) throws Exception {
		//String sql = "SELECT * FROM TB_USER";
        //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		Long nextVal = 0L;
        String sql = "INSERT INTO tb_user (USER_NUMBER, USER_ID, PASSWORD, ADDRESS, NAME, HANDPHONE) "
        					+ "VALUES (USER_SEQUENCE.NEXTVAL, ?, 	?,		?,		?,		?)";
        int rtn = jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getAddress(), user.getName(), user.getHandPhone());
        
        if(rtn == 1) {
        	sql = "SELECT USER_SEQUENCE.CURRVAL FROM DUAL";
        	nextVal = jdbcTemplate.queryForObject(sql, Long.class);
        }
        
        return nextVal.intValue();
	}
	
	public boolean addAccount(int userNumber) throws Exception {
		System.out.println("계좌 생성 시작");
		try {
			Thread.sleep(5000); //5초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        String sql = "INSERT INTO account (ACCOUNT, USER_NUMBER) "
        					+ "VALUES ( \r\n"
        					+ "(select 	\r\n"
        					+ "    case SUBSTR(REPLACE(account,'-',''), -4) 	\r\n"
        					+ "        when '9999' then SUBSTR(account, 0,5) || LPAD(SUBSTR(account,6,2)+1,2,0) || '-0001'	\r\n"
        					+ "        else SUBSTR(account, 0, 8) || LPAD(SUBSTR(account,9,4)+1,4,0)	\r\n"
        					+ "end \r\n"
        					+ "from account "
        					+ "where REPLACE(account,'-','') = (select max(REPLACE(account,'-','')) from account)), ?)";
        int rtn = jdbcTemplate.update(sql, 0);
        
        //return rtn == 1? true: false;
        return false;
	}
	
}

