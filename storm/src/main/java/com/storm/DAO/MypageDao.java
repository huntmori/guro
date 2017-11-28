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
	//회원정보 불러오기
	public MemberVO getMember(String email) {
		
		MemberVO vo = (MemberVO)sSession.selectOne("mypage.getMember", email);
		System.out.println("dao : getMember : "+email);
		return vo;
		
	}
	//회원정보 상세보기
	public MemberVO getModifyView(int key) {
		return sSession.selectOne("mypage.modifyView", key);
		
	}
	//회원정보 수정
	public  int updateUT(int key) {
		return sSession.update("mypage.updateUT", key);
	}
	@Autowired
	public SqlSessionTemplate	sqlSession;
	
	public int CheckProc(HashMap map) {
		
		int	result = sqlSession.selectOne("member.checkId", map);
		return result;
	}

	
	

	
	//내커뮤니티 목록 불러오기
	public ArrayList getMyCommuList(HashMap map) {
		System.out.println("dao: getMyCommuList 왔다 ");
		ArrayList list= (ArrayList)sSession.selectList("mypage.mycommuList", map);
		return list;
	}
	


	


}
