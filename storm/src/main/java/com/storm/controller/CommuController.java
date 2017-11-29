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
import com.storm.VO.CommuVO;
import com.storm.util.PageUtil;


@Controller
@RequestMapping("/Commu")
public class CommuController {
	
	@Autowired 
	private CommuService CService;
	
	
//Ŀ�´�Ƽ �Խ��� ���
	@RequestMapping("/CommuMain")
	public ModelAndView commumain
	(@RequestParam(value="nowPage",defaultValue="1")int nowPage){
//	����
//	�Ķ���� �ޱ�
//	�����ͺ��̽����� ����� �̴´�.
//1.�� ������ ������ ���ؼ� ������ ������ �����.
		int total=CService.getcommuTotal();
		PageUtil pInfo=
				new PageUtil(nowPage,total);
//2.������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���Ѵ�.
		ArrayList list= CService.getCommuList(nowPage, pInfo);
//	�信 �𵨷� �˷��ش�.
//	�並 �θ���.
		
		ModelAndView mv=new ModelAndView();
//	���� �Է��Ѵ�.
//�Լ�	addObject("Ű��",������);
		mv.addObject("PINFO",pInfo);
		mv.addObject("LIST",list);
		mv.setViewName("Commu/CommuMain");
		System.out.println("��������?"+mv);
		
		return mv;
	}
	
	//�󼼺��� ��û�� ó���� ��Ʈ�ѷ� �Լ�
	@RequestMapping("/CommuView")
	public ModelAndView commuView
	(CommuVO CVO,HttpSession session,
	@RequestParam(value="nowPage",defaultValue="1")int nowPage){
//		����
//		�Ķ���� �ޱ�
//		�����ͺ��̽����� ����� �̴´�.
	//1.�� ������ ������ ���ؼ� ������ ������ �����.
			int total=CService.getboardTotal();
			PageUtil pInfo=new PageUtil(nowPage,total);
	//2.������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���Ѵ�.
			ArrayList list= CService.getBlist(nowPage, pInfo,CVO);

			HashMap map=CService.commuView(CVO.communo);
	
			int usrKey=Integer.parseInt(String.valueOf(session.getAttribute("KEY")));
			String whatshow="";
			CVO.usrKey=usrKey;
			if(usrKey == 1 ){
				whatshow=CService.selectshow(CVO);
				System.out.println(CVO);
				System.out.println("�ȷο� Ŀ�³ѹ�= "+CVO.communo);
				System.out.println("�̰� �ȷο��� y , N�� �˷��ش�  "+ whatshow);
			}
			else{
				System.out.println("usrKey is null");
			}
	//�並 �θ���.
			ModelAndView mv=new ModelAndView();
			mv.addObject("MAP",map);
			System.out.println(map);
			mv.addObject("SHOWLIST", whatshow);
			System.out.println("SHOWLIST= "+whatshow);
			mv.addObject("PINFO",pInfo);
			mv.addObject("BLIST",list);
		//��û���踦 �� �� nowPage�� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ� ������ ���ؼ�
		//������ ��Ų �Ķ���� �̹Ƿ� �������� ������ �ݵ�� �����ؾ� �Ѵ�.
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
		
		
		System.out.println("Ŀ�³�"+communo+"������"+usrKey+"�ӵ�"+whatdo);
		
		CVO.communo=communo;
		CVO.usrKey=usrKey;
		
		if(whatdo.equals("add")){
			System.out.println("�ȷο� ����ϱ�!!");
			CService.insertF(CVO);//�����̺� �μ�Ʈ
		}
		else{
			System.out.println("�ȷο� ������Ʈ !!");
			System.out.println("�����ư��?= "+whatdo);
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
		
		String usrId=(String.valueOf(session.getAttribute("UID")));
		CVO.usrId=usrId;
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("MAP",map);
		mv.addObject("LIST",CVO);
		System.out.println("WriteCommu MAP ="+map);
		System.out.println("WriteCommu CVO ="+CVO);
	//��û���踦 �� �� nowPage�� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ� ������ ���ؼ�
	//������ ��Ų �Ķ���� �̹Ƿ� �������� ������ �ݵ�� �����ؾ� �Ѵ�.
		mv.addObject("nowPage", CVO.nowPage);
		mv.addObject("communo", CVO.communo);
		mv.setViewName("/Commu/WriteCommu");
		return mv;
	}
	
	@RequestMapping("/WriteProc")
	public ModelAndView writeProc(HttpSession session,CommuVO CVO){
		String	id = (String) session.getAttribute("UID");
		CVO.usrId = id;
		
		//		��� �Է��ϰ�
		CService.binsert(CVO);
		System.out.println(CVO.usrId);
		
		//		�並 �θ���.
		RedirectView rv=
			new RedirectView("../Commu/CommuView.storm");
		ModelAndView mv =new ModelAndView();
		mv.addObject("WLIST", CVO);
		mv.setView(rv);
		return mv;
	}
}

/*
 * Ŀ�´�Ƽ �Խ��� ��� ó��
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
		







