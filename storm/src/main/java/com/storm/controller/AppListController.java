package com.storm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.storm.Service.AppListService;
import com.storm.VO.AppVO;
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
		System.out.println("applist start");
		System.out.println("appList controller");
		int total = appService.getTotal();
		PageUtil	pInfo	=	new PageUtil(nowPage, total, 25);
		
		ArrayList appList = appService.getAppList(nowPage, pInfo);
		ArrayList	tagList	=	appService.getTagList();
		
		ModelAndView	mv	=	new ModelAndView();
		mv.addObject("PINFO",		pInfo);
		mv.addObject("APP_LIST",	appList);
		mv.addObject("TAG_LIST",	tagList);
		
		mv.setViewName("App/AppList");
		System.out.println("PAGE:"+nowPage);
		System.out.println("TOTAL : " +total);
		System.out.println(appList.size());
		
		System.out.println("applist end");
		return mv;
	}
	@RequestMapping("/AppSearchForm")
	public ModelAndView	appSearchForm()
	{
		ArrayList		genreList		=	appService.getGenreList();		//�帣 ����� �޾ƿ´�
		ArrayList		categoryList	=	appService.getCategoryList();	//ī�װ� ����� �޾ƿ´�
		ArrayList		tagList				=	appService.getTagList();			//�±� ����� �޾ƿ´�
		ArrayList		languageList	=	appService.getLanguageList();	//��� ����� �޾ƿ´�
				
		ModelAndView	mv	=	new ModelAndView();
									mv.addObject("GENRE_LIST",			genreList);
									mv.addObject("CATEGORY_LIST",	categoryList);
									mv.addObject("TAG_LIST",				tagList);
									mv.addObject("LANGUAGE_LIST",	languageList);
									mv.setViewName("App/AppSearch");
		return mv;
	}
	
	@RequestMapping("/AppView")
	public	ModelAndView	appView(int nowPage, int app_id)
	{
		//AppVO�� �ҷ��´�
		AppVO	info	=	appService.getAppInfo(app_id);
		
		//Genre����Ʈ�� �ҷ��´�
		ArrayList	genre_list	=	appService.getGenreList(app_id);

		//Category����Ʈ�� �ҷ��´�
		ArrayList	category_list	=	appService.getCategoryList(app_id);
		
		//Tag����Ʈ�� �ҷ��´�
		ArrayList	tag_list	=	appService.getTagList(app_id);
		
		//Language����Ʈ�� �ҷ��´�
		ArrayList	language_list	=	appService.getLanguageList(app_id);

		//Developer	����Ʈ�� �ҷ��´�
		ArrayList	developer_list	=	appService.getDeveloperList(app_id);
		
		//Publisher		����Ʈ�� �ҷ��´�
		ArrayList	publisher_list	=	appService.getPublisherList(app_id);
		
		System.out.println(info.getId());
		System.out.println(genre_list.size());
		System.out.println(category_list.size());
		System.out.println(language_list.size());
		System.out.println(developer_list.size());
		System.out.println(publisher_list.size());
		
		ModelAndView	mv	=	new ModelAndView();
		mv.addObject("APP_INFO",				info);
		mv.addObject("GENRE_LIST",			genre_list);
		mv.addObject("TAG_LIST",				tag_list);
		mv.addObject("CATEGORY_LIST",	category_list);
		mv.addObject("LANGUAGE_LIST",	language_list);
		mv.addObject("DEVELOPER_LIST",	developer_list);
		mv.addObject("PUBLISHER_LIST",	publisher_list);
		mv.setViewName("App/AppView");
		return mv;
	}
}
