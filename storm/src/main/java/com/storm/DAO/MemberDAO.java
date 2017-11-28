package com.storm.DAO;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.MemberVO;

public class MemberDAO extends SqlSessionDaoSupport {

//ȸ������
	
	@Autowired
	public SqlSessionTemplate sSession;

	public void insertMember(MemberVO mbVO) {
		sSession.insert("member.insertMember", mbVO);
		
	}

//�ߺ��˻�

	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	public int CheckProc(HashMap map) {
		
		int	result = sqlSession.selectOne("member.checkId", map);
		return result;
	}

}
