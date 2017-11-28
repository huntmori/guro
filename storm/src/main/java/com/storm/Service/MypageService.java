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
	//ȸ������ �ҷ�����
	public MemberVO getMember(String email) {
		MemberVO vo = mDao.getMember(email);
		System.out.println("���� :getMember : " +email);
		return vo;
	}
	
	//ȸ������ ������ ���� ������ ������
	public MemberVO getModifyView(int key) {
		MemberVO vo = mDao.getModifyView(key);
		System.out.println("���� :getModifyView"+key);
		return vo;
	}
	//ȸ������ ����ó��
	public int updateUT(int key) {
//		HashMap	map	=	new HashMap();
//		map.put("email", mVO.getEmail());
//		map.put("key", mVO.getKey());
//		map.put("MVO", mVO);
		
		return mDao.updateUT(key);
	}


	
	//�ߺ��˻�	
		public int checkProc(String id) {
			
			HashMap	map = new HashMap();
			map.put("id", id);
			
			
			int	result = mDao.CheckProc(map);
			return result;
		}
	
	
	public ArrayList getMyCommuList(int nowPage, PageUtil pInfo, MemberVO mVO) {
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		int	end = start + (pInfo.listCount - 1);
		System.out.println("����: getMyCommuList ����");
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("KEY", mVO.getKey());
		System.out.println("����: "+mVO.getKey());
		ArrayList	list = mDao.getMyCommuList(map);
			
		return list;
		//	�ٵ� ������ ���� �����ϸ� �ȵȴ�.
		//	�ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
		
	}



}


