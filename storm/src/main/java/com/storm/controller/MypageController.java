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
	
	//마이페이지목록 보기 요청처리 함수
	@RequestMapping("Mypage/MypageList")
	public ModelAndView mypageList(HttpSession session) {
		
		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);

		ModelAndView mv = new ModelAndView();
//		session.setAttribute("VO", vo);
		mv.addObject("VO", vo);
		mv.setViewName("Mypage/MypageList");
		System.out.println("컨트롤러 :mypageList: "+ email+"__" + vo.getKey());

		return mv;

	}
	
	
	//회원정보 수정폼
	@RequestMapping("Mypage/ModifyForm")
	public ModelAndView ModifyForm(HttpSession session) {

		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);

		ModelAndView mv = new ModelAndView();
		mv.addObject("VO", vo);
		mv.setViewName("Mypage/ModifyForm");
		System.out.println("컨트롤러 :ModifyForm: "+ email+"__" + vo.getKey());
		
		return mv; 
	}
	
	
	
	//회원정보 수정
	@RequestMapping("Mypage/ModifyProc")
	public ModelAndView ModifyProc(HttpSession session) {

		String email= (String)session.getAttribute("UID");
		MemberVO vo= mService.getMember(email);
		int count= mService.updateUT(vo.getKey());
		System.out.println("컨트롤러: ModifyProc: "+vo.getKey());
//		BigDecimal	temp	=	(BigDecimal) session.getAttribute("KEY");
//		int key= temp.intValue();
//		MemberVO vo= mService.ModifyProc(key);
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("VO",vo);
		System.out.println("mv.EMAIL:\t"+mv.getModel().get("EMAIL"));
		System.out.println("mv.KEY:\t"+mv.getModel().get("KEY"));
		System.out.println("컨트롤러 :ModifyProc :"+"PW");
		mv.setViewName("Mypage/ModifyForm");
		return mv;
	}
	
	
	
	//내커뮤니티목록 불러오기
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
		System.out.println("컨트롤러: myCommuList: "+list);
		return mv;

	}

}
