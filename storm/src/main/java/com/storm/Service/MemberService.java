package com.storm.Service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.DAO.MemberDAO;
import com.storm.VO.MemberVO;

//@Service <-중복현상
public class MemberService {
//회원가입
	@Autowired
	public MemberDAO mbDAO;

	public void insertMember(MemberVO mbVO) {
	
		mbDAO.insertMember(mbVO);
	}

	
	
//중복검사	
	public int checkProc(String id) {
		
		HashMap	map = new HashMap();
		map.put("id", id);
		
		
		int	result = mbDAO.CheckProc(map);
		return result;
	}

	


}
