package com.storm.Service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.DAO.MemberDAO;
import com.storm.VO.MemberVO;

//@Service <-�ߺ�����
public class MemberService {
//ȸ������
	@Autowired
	public MemberDAO mbDAO;

	public void insertMember(MemberVO mbVO) {
		
		System.out.println(mbVO.getEmail());
		System.out.println(mbVO.getPw());
		System.out.println(mbVO.getNick());
		System.out.println(mbVO.getKey());
		mbDAO.insertMember(mbVO);
	}

	
	
//�ߺ��˻�	
	public int checkProc(String id) {
		
		HashMap	map = new HashMap();
		map.put("id", id);
		
		
		int	result = mbDAO.CheckProc(map);
		return result;
	}

	


}
