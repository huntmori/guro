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
		int total=CService.getTotal();
		PageUtil pInfo=new PageUtil(nowPage,total);
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
	public ModelAndView commuView(CommuVO CVO,HttpSession session){
	//����
	//�Ķ���͸� �޴´�.
	//�󼼺��� ������ ���Ѵ�.
	//���ۿ� �޸� ����� ���Ѵ�.
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
	//��û���踦 �� �� nowPage�� ��Ʈ�ѷ��� �ʿ��ؼ� �������� �ƴϰ� ������ ���ؼ�
	//������ ��Ų �Ķ���� �̹Ƿ� �������� ������ �ݵ�� �����ؾ� �Ѵ�.
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
