package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.MemberVO;

public class MypageDao extends SqlSessionDaoSupport {

	@Autowired
	public SqlSessionTemplate sSession;
	//ȸ������ �ҷ�����
	public MemberVO getMember(String email) {
		
		MemberVO vo = (MemberVO)sSession.selectOne("mypage.getMember", email);
		System.out.println("dao : getMember : "+email);
		return vo;
		
	}
	//ȸ������ �󼼺���
	public MemberVO getModifyView(int key) {
		return sSession.selectOne("mypage.modifyView", key);
		
	}
	//ȸ������ ����
	public  int updateUT(int key) {
		return sSession.update("mypage.updateUT", key);
	}
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	public int CheckProc(HashMap map) {
		
		int	result = sqlSession.selectOne("member.checkId", map);
		return result;
	}

	
	

	
	//��Ŀ�´�Ƽ ��� �ҷ�����
	public ArrayList getMyCommuList(HashMap map) {
		System.out.println("dao: getMyCommuList �Դ� ");
		ArrayList list= (ArrayList)sSession.selectList("mypage.mycommuList", map);
		return list;
	}
	


	


}
