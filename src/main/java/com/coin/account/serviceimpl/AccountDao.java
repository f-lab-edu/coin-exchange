package com.coin.account.serviceimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coin.user.service.UserDTO;

@Repository("accountDao")
public class AccountDao {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final SqlSession sqlSession;

    public AccountDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	public boolean addAccount(int userNumber)  {
		
		int rtn = 0;
		
		try {
			Thread.sleep(5000); //5초 대기
			rtn = sqlSession.insert("com.coin.account.mapper.AccountMapper.addAccount", userNumber);
		} catch (InterruptedException e) {
			log.error("Thread sleep error");
		}
		
        return rtn == 1? true: false;
	}
	
}

