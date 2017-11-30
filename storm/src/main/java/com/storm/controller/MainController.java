package com.storm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.storm.Service.AppListService;
import com.storm.VO.AppVO;

@Controller
public class MainController 
{
	@Autowired
	public AppListService appService;
	
	@RequestMapping("main")
	public	ModelAndView	redirectMainController(){
		System.out.println("Test");
		
		//���Ѱ����� �����´�.
		AppVO	hotGame	=	appService.getHotGame();
		
		//�ֽŰ����� �����´�.
		AppVO	recentGame	=	appService.getRecentGame();

		//��ÿ��� ������ �����´�.
		AppVO		commingSoon	=	appService.getCommingSoon();
		
		System.out.println(hotGame.getTitle()+"\t"+recentGame.getTitle()+"\t"+commingSoon.getTitle());
		ModelAndView	mv	=	new ModelAndView();
		
		mv.addObject("RECENT", recentGame);
		mv.addObject("SOON",commingSoon);
		mv.addObject("HOT",hotGame);
		mv.setViewName("index");
		return mv;
	}
}
