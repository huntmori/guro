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
	// �տ����� ���� �������� ��Ʈ�ѷ��� �ַ� ���� Ŭ������ �̿��ؼ�
	// �۾��� ������ �����̴�.

	@Autowired
	public UserManagerService umS;

	// ��� ���� ��û ó�� �Լ�
	@RequestMapping("/UserList")
	public ModelAndView userList(@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
		/*
		 * String strPage = req.getParameter("nowPage"); int nowPage = 0;
		 * if(strPage == null || strPage.length() == 0) { nowPage = 1; } else {
		 * nowPage = Integer.parseInt(strPage); }
		 */

		// ����
		// �Ķ���� �ް�
		// �����ͺ��̽����� ��� �̾ƿ���
		// 1. �� ������ ������ ���ؼ� ������ ������ �����
		int total = umS.getTotal();
		PageUtil pInfo = new PageUtil(nowPage, total);

		// 2. ������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���ؼ�
		ArrayList list = umS.getUserList(nowPage, pInfo);
		// �信 �𵨷� �˷��ְ�

		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		// �� �Է�....... �Լ� addObject("Ű��", ������);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("UserManager/UserList");
		return mv;
	}
	
	// ����ڻ󼼺��� ��û�� ó���� ��Ʈ�ѷ� �Լ�
	@RequestMapping("/UserView")
	public ModelAndView userView(UserManagerVO umVO) {
		// ����
		// �Ķ���� �ް�
		// �󼼺��� ���� ���ϰ�
		// �� ���ۿ� �޸� ����� ���ϰ�
		HashMap map = umS.userView(umVO.userKey);
		
		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		// Map���� ���ΰ��� �и��ؼ� �𵨷� �����ص� �ȴ�.
		// mv.addObject("VIEW", map.get("VIEW"));
		// mv.addObject("LIST", map.get("LIST"));

		mv.addObject("MAP", map);
		// ��û ���踦 �� �� nowPage�� �� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ�
		// ������ ���ؼ� ������ ��Ų �Ķ���� �̹Ƿ�
		// �������� ������ �ݵ�� �����ؾ� �Ѵ�.
		mv.addObject("nowPage", umVO.nowPage);
		mv.setViewName("UserManager/UserView");
		return mv;
	}	
	
	
	// �˻� ��û ó���Լ�
	@RequestMapping("UserSearch")
	public ModelAndView userSearch(UserManagerVO umVO) {
		// �Ķ���� �ް�

		// �˻��ϰ�
		ArrayList list = umS.userSearch(umVO);
		// �������.
		ModelAndView mv = new ModelAndView();
		mv.addObject("LIST", list);
		mv.addObject("nowPage", umVO.nowPage);		
		mv.setViewName("UserManager/UserSearch");
		return mv;
	}

	// �����ϱ� �� ��û ó�� �Լ�
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(UserManagerVO umVO) {
		// ����
		// �Ķ���� �ް�

		// �����ϱ⸦ ���ؼ� ���� ����ڸ� �˷��ش�.(��񿡼� �˾Ƴ���.)
		// ������ �󼼺��⿡�� ���� ������� ������ �˾Ƴ��� ó���� �س��Ҵ�.
		// �̰��� �̿��ϸ� �ɰ��̴�.
		UserManagerVO vo = umS.getModifyView(umVO.userKey);

		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", umVO.nowPage);
		mv.setViewName("UserManager/ModifyForm");
		return mv;
	}

	// �����ϱ� ��û ó���Լ�
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(UserManagerVO umVO) {
		// ����
		// �Ķ���� �ޱ�

		// ���� ó���ϱ�
		int count = umS.updateUser(umVO);
		// if(count == 0) {
		// // ����� �޶� ���� �ȵ�
		// }
		// else {
		// // ����� ���Ƽ� ���� ��
		// }

		// �� �θ���
		ModelAndView mv = new ModelAndView();
		mv.addObject("nowPage", umVO.nowPage);
		mv.addObject("userKey", umVO.userKey);
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("UserManager/ImsiView");
		// �ӽú信���� ���� ���θ� �˷��ְ�
		// ���� �󼼺���� �����ϹǷ�
		// �󼼺��� �ʿ��� userKey, nowPage�� �˷��� �ʿ䰡 �ִ�.
		// �ӽú信���� ���� ���θ� �̿��ؼ� alert â�� ����ؾ� �ϹǷ�
		// ���� ���θ� �Ǵ��ϴ� � ������� �˷��� �ʿ䰡 �ִ�.
		return mv;
	}

	// ���� ��û ó���Լ�
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
