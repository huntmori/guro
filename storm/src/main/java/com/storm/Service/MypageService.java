package com.storm.Service;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.util.PageUtil;
import com.storm.VO.CommuVO;
import com.storm.VO.MemberVO;
import com.storm.DAO.MemberDAO;
import com.storm.DAO.MypageDao;

public class MypageService {

	@Autowired
	public MypageDao mDao;
	//회원정보 불러오기
	public MemberVO getMember(String email) {
		MemberVO vo = mDao.getMember(email);
		System.out.println("서비스 :getMember : " +email);
		return vo;
	}
	
	//회원정보 수정을 위한 원정보 꺼내기
	public MemberVO getModifyView(int key) {
		MemberVO vo = mDao.getModifyView(key);
		System.out.println("서비스 :getModifyView"+key);
		return vo;
	}
	//회원정보 수정처리
	public int updateUT(int key) {
//		HashMap	map	=	new HashMap();
//		map.put("email", mVO.getEmail());
//		map.put("key", mVO.getKey());
//		map.put("MVO", mVO);
		
		return mDao.updateUT(key);
	}


	
	//중복검사	
		public int checkProc(String id) {
			
			HashMap	map = new HashMap();
			map.put("id", id);
			
			
			int	result = mDao.CheckProc(map);
			return result;
		}
	
	
	public ArrayList getMyCommuList(int nowPage, PageUtil pInfo, MemberVO mVO) {
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		int	end = start + (pInfo.listCount - 1);
		System.out.println("서비스: getMyCommuList 시작");
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("KEY", mVO.getKey());
		System.out.println("서비스: "+mVO.getKey());
		ArrayList	list = mDao.getMyCommuList(map);
			
		return list;
		//	근데 오늘은 아직 실행하면 안된다.
		//	왜냐하면 PageInfo를 new 시키지 않았기 때문이다.
		
	}



}


