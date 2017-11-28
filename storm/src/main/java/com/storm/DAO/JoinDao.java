package com.storm.DAO;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class JoinDao extends SqlSessionDaoSupport {

	@Autowired
	public SqlSessionTemplate sSession;

	public void insertMember(HashMap map) {
		System.out.println("dao:insertMember");
		System.out.println(map.get("email"));
		System.out.println(map.get("nick"));
		System.out.println(map.get("pw"));

		sSession.insert("join.insertMember", map);
		
	}

	public Integer check(String id) {
		sSession.selectOne("join.idcheck", id);
		return (Integer)sSession.selectOne("join.idcheck", id);
	}
	

	


}
