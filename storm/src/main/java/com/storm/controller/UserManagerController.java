package com.storm.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.VO.UserManagerVO;
import com.storm.Service.UserManagerService;
import com.storm.util.PageUtil;

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {
	// 앞에서와 같은 개념으로 컨트롤러는 주로 서비스 클래스를 이용해서
	// 작업을 진행할 예정이다.

	@Autowired
	public UserManagerService umS;

	// 목록 보기 요청 처리 함수
	@RequestMapping("/UserList")
	public ModelAndView userList(@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
		/*
		 * String strPage = req.getParameter("nowPage"); int nowPage = 0;
		 * if(strPage == null || strPage.length() == 0) { nowPage = 1; } else {
		 * nowPage = Integer.parseInt(strPage); }
		 */

		// 할일
		// 파라메터 받고
		// 데이터베이스에서 목록 뽑아오고
		// 1. 총 데이터 개수를 구해서 페이지 정보를 만들곻
		int total = umS.getTotal();
		PageUtil pInfo = new PageUtil(nowPage, total);

		// 2. 페이지 정보를 이용해서 그 페이지에 필요한 목록만 구해서
		ArrayList list = umS.getUserList(nowPage, pInfo);
		// 뷰에 모델로 알려주고

		// 뷰를 부른다.
		ModelAndView mv = new ModelAndView();
		// 모델 입력....... 함수 addObject("키값", 데이터);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("UserManager/UserList");
		return mv;
	}
	
	// 사용자상세보기 요청을 처리할 컨트롤러 함수
	@RequestMapping("/UserView")
	public ModelAndView userView(UserManagerVO umVO) {
		// 할일
		// 파라메터 받고
		// 상세보기 내용 구하고
		// 이 원글에 달린 댓글을 구하고
		HashMap map = umS.userView(umVO.userKey);
		
		// 뷰를 부른다.
		ModelAndView mv = new ModelAndView();
		// Map으로 묶인것을 분리해서 모델로 전달해도 된다.
		// mv.addObject("VIEW", map.get("VIEW"));
		// mv.addObject("LIST", map.get("LIST"));

		mv.addObject("MAP", map);
		// 요청 설계를 할 때 nowPage는 이 컨트롤러서 필요해서 받은것이 아니고
		// 다음을 위해서 릴레이 시킨 파라메터 이므로
		// 잊지말고 다음에 반드시 전달해야 한다.
		mv.addObject("nowPage", umVO.nowPage);
		mv.setViewName("UserManager/UserView");
		return mv;
	}	
	
	
	// 검색 요청 처리함수
	@RequestMapping("UserSearch")
	public ModelAndView userSearch(UserManagerVO umVO) {
		// 파라메터 받고

		// 검색하고
		ArrayList list = umS.userSearch(umVO);
		// 결과본다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("LIST", list);
		mv.addObject("nowPage", umVO.nowPage);		
		mv.setViewName("UserManager/UserSearch");
		return mv;
	}

	// 수정하기 폼 요청 처리 함수
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(UserManagerVO umVO) {
		// 할일
		// 파라메터 받고

		// 수정하기를 위해서 현재 사용자를 알려준다.(디비에서 알아낸다.)
		// 내용은 상세보기에서 현재 사용자의 내용을 알아내는 처리를 해놓았다.
		// 이것을 이용하면 될것이다.
		UserManagerVO vo = umS.getModifyView(umVO.userKey);

		// 뷰를 부른다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", umVO.nowPage);
		mv.setViewName("UserManager/ModifyForm");
		return mv;
	}

	// 수정하기 요청 처리함수
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(UserManagerVO umVO) {
		// 할일
		// 파라메터 받기

		// 수정 처리하기
		int count = umS.updateUser(umVO);
		// if(count == 0) {
		// // 비번이 달라서 수정 안됨
		// }
		// else {
		// // 비번이 같아서 수정 됨
		// }

		// 뷰 부르기
		ModelAndView mv = new ModelAndView();
		mv.addObject("nowPage", umVO.nowPage);
		mv.addObject("userKey", umVO.userKey);
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("UserManager/ImsiView");
		// 임시뷰에서는 수정 여부를 알려주고
		// 곧장 상세보기로 가야하므로
		// 상세보기 필요한 userKey, nowPage를 알려줄 필요가 있다.
		// 임시뷰에서는 수정 여부를 이용해서 alert 창을 출력해야 하므로
		// 수정 여부를 판단하는 어떤 결과값을 알려줄 필요가 있다.
		return mv;
	}

	// 삭제 요청 처리함수
	@RequestMapping("/DeleteProc")
	public ModelAndView deleteProc(UserManagerVO umVO) {

		int count = umS.deleteUser(umVO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("nowPage", umVO.nowPage);
		mv.addObject("userKey", umVO.userKey);
		mv.addObject("count", count);
		mv.addObject("from", "delete");
		mv.setViewName("UserManager/ImsiView");
		return mv;
	}
}
