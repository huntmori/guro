package com.storm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.storm.Service.AppListService;
import com.storm.util.PageUtil;

@Controller
@RequestMapping("/App")
public class AppListController 
{
	@Autowired
	public AppListService appService;
	
	@RequestMapping("/AppList")
	public ModelAndView	appList
	(@RequestParam(value="nowPage", defaultValue="1") int nowPage)
	{
		System.out.println("appList controller");
		int total = appService.getTotal();
		PageUtil	pInfo	=	new PageUtil(nowPage, total, 25);
		
		ArrayList appList = appService.getAppList(nowPage, pInfo);
		ArrayList	tagList	=	appService.getTagList();
		
		ModelAndView	mv	=	new ModelAndView();
		mv.addObject("PINFO",pInfo);
		mv.addObject("APP_LIST",	appList);
		mv.addObject("TAG_LIST", tagList);
		mv.setViewName("App/AppList");
		
		System.out.println("TOTAL : " +total);
		
		return mv;
	}
}
