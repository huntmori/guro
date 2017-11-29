package com.storm.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.storm.util.PageUtil;
import com.storm.VO.MemberVO;
import com.storm.Service.CommuService;
import com.storm.Service.MypageService;

@Controller
public class MypageController {

	@Autowired
	public MypageService mService;
	@Autowired
	public CommuService CService;
	
	//������������� ���� ��ûó�� �Լ�
	@RequestMapping("Mypage/MypageList")
	public ModelAndView mypageList(HttpSession session) {
		
		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);

		ModelAndView mv = new ModelAndView();
//		session.setAttribute("VO", vo);
		mv.addObject("VO", vo);
		mv.setViewName("Mypage/MypageList");
		System.out.println("��Ʈ�ѷ� :mypageList: "+ email+"__" + vo.getKey());

		return mv;

	}
	
	
	//ȸ������ ������
	@RequestMapping("Mypage/ModifyForm")
	public ModelAndView ModifyForm(HttpSession session) {

		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);

		ModelAndView mv = new ModelAndView();
		mv.addObject("VO", vo);
		mv.setViewName("Mypage/ModifyForm");
		System.out.println("��Ʈ�ѷ� :ModifyForm: "+ email+"__" + vo.getKey());
		
		return mv; 
	}
	
	
	
	//ȸ������ ����
	@RequestMapping("Mypage/ModifyProc")
	public ModelAndView ModifyProc(HttpSession session) {

		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);
		int count= mService.updateUT(vo.getKey());
		System.out.println("��Ʈ�ѷ�: ModifyProc: "+vo.getKey());
//		BigDecimal	temp	=	(BigDecimal) session.getAttribute("KEY");
//		int key= temp.intValue();
//		MemberVO vo= mService.ModifyProc(key);
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("VO",vo);
		System.out.println("mv.EMAIL:\t"+mv.getModel().get("EMAIL"));
		System.out.println("mv.KEY:\t"+mv.getModel().get("KEY"));
		System.out.println("��Ʈ�ѷ� :ModifyProc :"+"PW");
		mv.setViewName("Mypage/ModifyForm");
		return mv;
	}
	
	
	
	//��Ŀ�´�Ƽ��� �ҷ�����
	@RequestMapping("Mypage/MyCommuList")
	public ModelAndView myCommuList(@RequestParam(value="nowPage", defaultValue="1")
							int nowPage, HttpSession session, MemberVO mVO) {

		String email=(String)session.getAttribute("UID");
		mVO= mService.getMember(email);
		
		int total= CService.getcommuTotal();
		PageUtil pInfo= new PageUtil(nowPage, total);
		
		ArrayList list= mService.getMyCommuList(nowPage, pInfo, mVO);
		
		ModelAndView	mv = new ModelAndView();
		mv.addObject("PINFO",pInfo);
		mv.addObject("LIST",list);
		mv.setViewName("Mypage/MyCommuList");
		System.out.println("��Ʈ�ѷ�: myCommuList: "+list);
		return mv;

	}

}
