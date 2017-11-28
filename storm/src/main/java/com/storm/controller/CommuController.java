package com.storm.controller;

import java.util.ArrayList;   
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.Service.CommuService;
import com.storm.util.PageUtil;
import com.storm.VO.CommuVO;
import com.storm.VO.MemberVO;


@Controller
@RequestMapping("/Commu")
public class CommuController {
	
	@Autowired 
	private CommuService CService;
	
	
//커뮤니티 게시판 목록
	@RequestMapping("/CommuMain")
	public ModelAndView commumain
	(@RequestParam(value="nowPage",defaultValue="1")int nowPage){
//	할일
//	파라메터 받기
//	데이터베이스에서 목록을 뽑는다.
//1.총 데이터 개수를 구해서 페이지 정보를 만든다.
		int total=CService.getTotal();
		PageUtil pInfo=new PageUtil(nowPage,total);
//2.페이지 정보를 이요해서 그 페이지에 필요한 목록만 구한다.
		ArrayList list= CService.getCommuList(nowPage, pInfo);
//	뷰에 모델로 알려준다.
//	뷰를 부른다.
		
		ModelAndView mv=new ModelAndView();
//	모델을 입력한다.
//함수	addObject("키값",데이터);
		mv.addObject("PINFO",pInfo);
		mv.addObject("LIST",list);
		mv.setViewName("Commu/CommuMain");
		System.out.println("마지막은?"+mv);
		
		return mv;
	}
	
	//상세보기 요청을 처리할 컨트롤러 함수
	@RequestMapping("/CommuView")
	public ModelAndView commuView(CommuVO CVO,HttpSession session){
	//할일
	//파라메터를 받는다.
	//상세보기 내용을 구한다.
	//원글에 달린 댓글을 구한다.
			HashMap map=CService.commuView(CVO.communo);
	
			int usrKey=Integer.parseInt(String.valueOf(session.getAttribute("KEY")));
			String whatshow="";
			CVO.usrKey=usrKey;
			if(usrKey == 1 ){
				whatshow=CService.selectshow(CVO);
				System.out.println(CVO);
				System.out.println("팔로우 커뮤넘버= "+CVO.communo);
				System.out.println("이건 팔로우의 y , N을 알려준다  "+ whatshow);
			}
			else{
				System.out.println("usrKey is null");
			}
	//뷰를 부른다.
			ModelAndView mv=new ModelAndView();
			mv.addObject("MAP",map);
			System.out.println(map);
			mv.addObject("SHOWLIST", whatshow);
			System.out.println("SHOWLIST= "+whatshow);
		//요청설계를 할 때 nowPage는 컨트롤러가 필요해서 받은것이 아니고 다음을 위해서
		//릴레이 시킨 파라메터 이므로 잊지말고 다음에 반드시 전달해야 한다.
			mv.addObject("nowPage", CVO.nowPage);
			mv.addObject("communo", CVO.communo);
			mv.setViewName("/Commu/CommuView");
			return mv;
	}
	
	
	@RequestMapping("/CommuFollow")
	public ModelAndView commuFollow(CommuVO CVO,HttpServletRequest req,HttpSession session){
		
		String whatdo=req.getParameter("whatdo");
		String no =  req.getParameter("data2");
		String noWPage =req.getParameter("nowpage");
		int nowPage = Integer.parseInt(noWPage);
		int communo =Integer.parseInt(no);
		
		int usrKey =Integer.parseInt(String.valueOf(session.getAttribute("KEY")));
		
		
		System.out.println("커뮤넘"+communo+"유저넘"+usrKey+"왓두"+whatdo);
		
		CVO.communo=communo;
		CVO.usrKey=usrKey;
		
		if(whatdo.equals("add")){
			System.out.println("팔로우 등록하기!!");
			CService.insertF(CVO);//맵테이블에 인서트
		}
		else{
			System.out.println("팔로우 업데이트 !!");
			System.out.println("실행버튼은?= "+whatdo);
			CService.updateF(whatdo,CVO);
			//Dao.updataIsshow(whatdo, communo, usrNo); 
		}
		RedirectView rv= 

				new RedirectView("../Commu/CommuView.storm");

				new RedirectView("../Commu/CommuMain.storm");

		ModelAndView mv=new ModelAndView();
		mv.addObject("communo", CVO.communo);
		mv.addObject("nowPage", CVO.nowPage);
		//req.setAttribute("communo", communo);
		//req.setAttribute("nowpage", nowPage);
		mv.setView(rv);
			return mv;
	}

	
	@RequestMapping("/WriteCommu")
	public ModelAndView writeCommu(CommuVO CVO,HttpSession session){
		HashMap map=CService.commuView(CVO.communo);
		
		int usrKey=Integer.parseInt(String.valueOf(session.getAttribute("KEY")));
		CVO.usrKey=usrKey;
	
		ModelAndView mv=new ModelAndView();
		mv.addObject("MAP",map);
		System.out.println(map);
	//요청설계를 할 때 nowPage는 컨트롤러가 필요해서 받은것이 아니고 다음을 위해서
	//릴레이 시킨 파라메터 이므로 잊지말고 다음에 반드시 전달해야 한다.
		mv.addObject("nowPage", CVO.nowPage);
		mv.addObject("communo", CVO.communo);
		mv.setViewName("/Commu/WriteCommu");
		return mv;
	}
	
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(){
		
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/Commu/CommuView");
		return mv;
	}
}

/*
 * 커뮤니티 게시판 댓글 처리
 * 
 *  	
 
	@RequestMapping("/commureplyForm")
	public ModelAndView commureplyForm(CommuVO CVO) { 

		ModelAndView	mv = new ModelAndView();
		mv.addObject("oriNo", CVO.oriNo);
		mv.addObject("nowPage", aVO.nowPage);
		mv.setViewName("AnBoard/AnWriteForm");
		return mv;
		
		int	goods_no	=	Integer.parseInt(str_goods_no);
		int	origin_no	=	Integer.parseInt(str_originNo);
		int	writer_no	=	new UserDao().getUserNumber(str_writerNo);
		
		new CommentDao().insertNewComment(goods_no, origin_no, writer_no, content);
		req.setAttribute("g_no", goods_no);
		req.setAttribute("originUser", origin_no);
		return "../view/Goods/CommentWriteProcess.jsp";
	}

>>>>>>> branch 'master' of https://github.com/huntmori/guro.git
}
<<<<<<< HEAD
=======
		
		*/
		







