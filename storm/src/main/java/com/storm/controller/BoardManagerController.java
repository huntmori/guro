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

import com.storm.Service.BoardManagerService;
import com.storm.util.PageUtil;
import com.storm.VO.BoardManagerVO;

@Controller
@RequestMapping("/BoardManager")
public class BoardManagerController {
	// �տ����� ���� �������� ��Ʈ�ѷ��� �ַ� ���� Ŭ������ �̿��ؼ�
	// �۾��� ������ �����̴�.

	@Autowired
	public BoardManagerService bmS;

	// ��� ���� ��û ó�� �Լ�
	@RequestMapping("/BoardList")
	public ModelAndView boardList(@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
		/*
		 * String strPage = req.getParameter("nowPage"); int nowPage = 0;
		 * if(strPage == null || strPage.length() == 0) { nowPage = 1; } else {
		 * nowPage = Integer.parseInt(strPage); }
		 */

		// ����
		// �Ķ���� �ް�
		// �����ͺ��̽����� ��� �̾ƿ���
		// 1. �� ������ ������ ���ؼ� ������ ������ �����
		int total = bmS.getTotal();
		PageUtil pInfo = new PageUtil(nowPage, total);

		// 2. ������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���ؼ�
		ArrayList list = bmS.getBoardList(nowPage, pInfo);
		// �信 �𵨷� �˷��ְ�

		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		// �� �Է�....... �Լ� addObject("Ű��", ������);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("BoardManager/BoardList");
		return mv;
	}

	// ����ڻ󼼺��� ��û�� ó���� ��Ʈ�ѷ� �Լ�
	@RequestMapping("/BoardView")
	public ModelAndView boardView(BoardManagerVO bmVO) {
		// ����
		// �Ķ���� �ް�
		// �󼼺��� ���� ���ϰ�
		HashMap map = bmS.boardView(bmVO.boardNo);
		
		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		// Map���� ���ΰ��� �и��ؼ� �𵨷� �����ص� �ȴ�.
		// mv.addObject("VIEW", map.get("VIEW"));
		// mv.addObject("LIST", map.get("LIST"));

		mv.addObject("MAP", map);
		// ��û ���踦 �� �� nowPage�� �� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ�
		// ������ ���ؼ� ������ ��Ų �Ķ���� �̹Ƿ�
		// �������� ������ �ݵ�� �����ؾ� �Ѵ�.
		mv.addObject("nowPage", bmVO.nowPage);
		mv.setViewName("BoardManager/BoardView");
		return mv;
	}

	// �˻� ��û ó���Լ�
	@RequestMapping("BoardSearch")
	public ModelAndView boardSearch(BoardManagerVO bmVO) {
		// �Ķ���� �ް�
		// �˻��ϰ�
		ArrayList list = bmS.boardSearch(bmVO);
		// �������.
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("LIST", list);
		mv.addObject("nowPage", bmVO.nowPage);
		mv.setViewName("BoardManager/BoardSearch");
		return mv;
	}

	// �����ϱ� �� ��û ó�� �Լ�
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(BoardManagerVO bmVO) {
		// ����
		// �Ķ���� �ް�

		// �����ϱ⸦ ���ؼ� ���� ����ڸ� �˷��ش�.(��񿡼� �˾Ƴ���.)
		// ������ �󼼺��⿡�� ���� ������� ������ �˾Ƴ��� ó���� �س��Ҵ�.
		// �̰��� �̿��ϸ� �ɰ��̴�.
		BoardManagerVO vo = bmS.getModifyView(bmVO.boardNo);

		// �並 �θ���.
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", bmVO.nowPage);
		mv.setViewName("BoardManager/ModifyForm");
		return mv;
	}

	// �����ϱ� ��û ó���Լ�
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(BoardManagerVO bmVO) {
		// ����
		// �Ķ���� �ޱ�

		// ���� ó���ϱ�
		int count = bmS.updateBoard(bmVO);
		// if(count == 0) {
		// // ����� �޶� ���� �ȵ�
		// }
		// else {
		// // ����� ���Ƽ� ���� ��
		// }

		// �� �θ���
		ModelAndView mv = new ModelAndView();
		mv.addObject("nowPage", bmVO.nowPage);
		mv.addObject("boardNo", bmVO.boardNo);
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("BoardManager/ImsiView");
		// �ӽú信���� ���� ���θ� �˷��ְ�
		// ���� �󼼺���� �����ϹǷ�
		// �󼼺��� �ʿ��� userKey, nowPage�� �˷��� �ʿ䰡 �ִ�.
		// �ӽú信���� ���� ���θ� �̿��ؼ� alert â�� ����ؾ� �ϹǷ�
		// ���� ���θ� �Ǵ��ϴ� � ������� �˷��� �ʿ䰡 �ִ�.
		return mv;
	}

	// ���� ��û ó���Լ�
	@RequestMapping("/DeleteProc")
	public ModelAndView deleteProc(BoardManagerVO bmVO) {

		int count = bmS.deleteBoard(bmVO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("nowPage", bmVO.nowPage);
		mv.addObject("boardNo", bmVO.boardNo);
		mv.addObject("count", count);
		mv.addObject("from", "delete");
		mv.setViewName("BoardManager/ImsiView");
		return mv;
	}
}
