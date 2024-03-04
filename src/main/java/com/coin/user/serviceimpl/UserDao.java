package com.coin.user.serviceimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coin.user.service.UserDTO;

@Repository("userDao")
public class UserDao {
	
	private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	public int addUser(UserDTO userDto) throws Exception {
		int rtn = sqlSession.insert("com.coin.user.mapper.UserMapper.addUser", userDto);
		if(rtn == 1) {
			return sqlSession.selectOne("com.coin.user.mapper.UserMapper.getUserNumber");
		} else {
			throw new Exception("user insert Error");
//			return 0;
		}
	}
	
}

