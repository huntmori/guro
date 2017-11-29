package com.storm.DAO;

import java.util.HashMap;  

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginDAO extends SqlSessionDaoSupport {
	
	@Autowired
	public SqlSessionTemplate sqlSession;

	public HashMap loginProc(HashMap map) {
		HashMap result = sqlSession.selectOne("login.login", map);
		System.out.println("dao:loginProc");
		return result;
	}
}
