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
	
	
//Ŀ�´�Ƽ �Խ��� ���
	@RequestMapping("/CommuMain")
	public ModelAndView boardList
	(@RequestParam(value="nowPage",defaultValue="1")int nowPage){
//	����
//	�Ķ���� �ޱ�
//	�����ͺ��̽����� ����� �̴´�.
//1.�� ������ ������ ���ؼ� ������ ������ �����.
		int total=CService.getTotal();
		PageUtil pInfo=new PageUtil(nowPage,total);
//2.������ ������ �̿��ؼ� �� �������� �ʿ��� ��ϸ� ���Ѵ�.
		ArrayList list=CService.getBoardList(nowPage, pInfo);
//	�信 �𵨷� �˷��ش�.
//	�並 �θ���.
		ModelAndView	mv = new ModelAndView();
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
	public ModelAndView commuView(CommuVO CVO){
	//����
	//�Ķ���͸� �޴´�.
	//�󼼺��� ������ ���Ѵ�.
	//���ۿ� �޸� ����� ���Ѵ�.
			HashMap map=CService.commuView(CVO.oriNo);
	//�並 �θ���.
			ModelAndView mv=new ModelAndView();
			mv.addObject("MAP",map);
		//��û���踦 �� �� nowPage�� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ� ������ ���ؼ�
		//������ ��Ų �Ķ���� �̹Ƿ� �������� ������ �ݵ�� �����ؾ� �Ѵ�.
			mv.addObject("nowPage", CVO.nowPage);
			mv.addObject("communo", CVO.communo);
			mv.setViewName("/Commu/CommuView");
			return mv;
	}
	
	
	@RequestMapping("/CommuFollw")
	public ModelAndView commuFollow(CommuVO CVO,HttpServletRequest req,HttpSession session){
		System.out.println("���⸦ �����ؾߵǴµ�.....");
		String whatdo=req.getParameter("whatdo");
		System.out.println("���Ͷ� = " +whatdo);
		String no =  req.getParameter("data2");
		System.out.println("�̰� Ŀ�´�Ƽ �ѹ�? " +no);
		String noWPage =req.getParameter("nowPage");
		int nowPage = Integer.parseInt(noWPage);
		int communo =Integer.parseInt(no);
		
		session = req.getSession();
		int usrKey =(int) session.getAttribute("KEY");
		
		System.out.println("Ŀ�³�"+communo+"������"+usrKey+"�ӵ�"+whatdo);
		
		
		if(whatdo.equals("add")){
			System.out.println("����ǳ�");
			CService.insertF(CVO);//�����̺� �μ�Ʈ
		}
		else{
			System.out.println("�̰� �������?");
			CService.updateF(whatdo,CVO);
			//Dao.updataIsshow(whatdo, communo, usrNo); 
		}
		RedirectView rv= 
				new RedirectView("../Commu/CommuMain.storm");
		ModelAndView mv=new ModelAndView();
		mv.addObject("communo", CVO.communo);
		mv.addObject("nowPage", CVO.nowPage);
		//req.setAttribute("communo", communo);
		//req.setAttribute("nowpage", nowPage);
		mv.setView(rv);
			return mv;
	}

/*
 * Ŀ�´�Ƽ �Խ��� ��� ó��
 * 
 *  	
 */
	
	public ModelAndView commureplyForm(CommuVO CVO) { 
		
		
		String	content	=	req.getParameter("content");
		String	str_goods_no	=	req.getParameter("gno");
		String	str_originNo	=	req.getParameter("originUID");
		String	str_writerNo	=	req.getParameter("writerNo");
		
		int	goods_no	=	Integer.parseInt(str_goods_no);
		int	origin_no	=	Integer.parseInt(str_originNo);
		int	writer_no	=	new UserDao().getUserNumber(str_writerNo);
		
		new CommentDao().insertNewComment(goods_no, origin_no, writer_no, content);
		req.setAttribute("g_no", goods_no);
		req.setAttribute("originUser", origin_no);
		return "../view/Goods/CommentWriteProcess.jsp";
	}

}
		
		
		







