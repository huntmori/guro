package com.storm.controller;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.storm.Service.AppListService;
import com.storm.VO.AppVO;
import com.storm.VO.TagVO;
import com.storm.util.PageUtil;

@Controller
@RequestMapping("/App")
public class AppListController 
{
	@Autowired
	public AppListService appService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/AppList")
	public ModelAndView	appList
	(@RequestParam(value="nowPage", defaultValue="1") int nowPage)
	{
		System.out.println("applist start");
		System.out.println("appList controller");
		int total = appService.getTotal();
		PageUtil	pInfo	=	new PageUtil(nowPage, total, 25);
		
		ArrayList 	appList 			= appService.getAppList(nowPage, pInfo);
		ArrayList		genreList		=	appService.getGenreList();		//�帣 ����� �޾ƿ´�
		ArrayList		categoryList	=	appService.getCategoryList();	//ī�װ� ����� �޾ƿ´�
		ArrayList		tagList				=	appService.getTagList();			//�±� ����� �޾ƿ´�
		//ArrayList		languageList	=	appService.getLanguageList();	//��� ����� �޾ƿ´�
		
		ModelAndView	mv	=	new ModelAndView();
		mv.addObject("PINFO",		pInfo);
		mv.addObject("APP_LIST",	appList);
		mv.addObject("GENRE_LIST",			genreList);
		mv.addObject("CATEGORY_LIST",	categoryList);
		mv.addObject("TAG_LIST",				tagList);
		
		mv.setViewName("App/AppList");
		System.out.println("PAGE:"+nowPage);
		//System.out.println("PINFO : "+pInfo.endPage+pInfo.totalCount);
		System.out.println("TOTAL : " +total);
		System.out.println(appList.size());
		
		System.out.println("applist end");
		return mv;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/SearchProc")
	public ModelAndView	appSearchProc(HttpServletRequest request,	HttpServletResponse response)
	{
		String[] genreValues = request.getParameterValues("GENRE_VALUES");
		String[] tagValues = request.getParameterValues("TAG_VALUES");
		String[] categoryValues = request.getParameterValues("CATEGORY_VALUES");
		
		ArrayList	genreList = null;
		ArrayList	tagList	=	null;
		ArrayList	categoryList	=	null;
		
		if(genreValues!=null)	{
			genreList = new ArrayList();
			for(String str : genreValues)
				genreList.add(Integer.parseInt(str));
		}
		
		if(tagValues!=null){
			tagList	=	 new ArrayList();
			for(String str : tagValues)
				tagList.add(Integer.parseInt(str));
		}
		if(categoryValues!=null){
			categoryList = new ArrayList();
			for(String str : categoryValues)
				categoryList.add(Integer.parseInt(str));
		}
				
		HashMap	map = new HashMap();
		if(tagList!=null)
			map.put("TAGLIST", tagList);
		if(genreList!=null)
			map.put("GENRELIST", genreList);
		if(categoryList!=null)
			map.put("CATEGORYLIST", categoryList);
		
		ArrayList appList = appService.appSearchProc(map);
		
		int total = appList.size();
		PageUtil	pInfo	=	new PageUtil(1, total, 25);
		
		ArrayList		genreTemp			=	appService.getGenreList();		//�帣 ����� �޾ƿ´�
		ArrayList		categoryTemp	=	appService.getCategoryList();	//ī�װ� ����� �޾ƿ´�
		ArrayList		tagTemp				=	appService.getTagList();			//�±� ����� �޾ƿ´�
		//ArrayList		languageList	=	appService.getLanguageList();	//��� ����� �޾ƿ´�
		
		ModelAndView	mv	=	new ModelAndView();
		mv.addObject("PINFO",		pInfo);
		mv.addObject("APP_LIST",	appList);
		mv.addObject("GENRE_LIST",			genreTemp);
		mv.addObject("CATEGORY_LIST",	categoryTemp);
		mv.addObject("TAG_LIST",				tagTemp);
		mv.setViewName("App/AppList");
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/AppView")
	public	ModelAndView	appView(int nowPage, int app_id)
	{

		AppVO	info	=	appService.getAppInfo(app_id);							//AppVO�� �ҷ��´�		
		ArrayList	genre_list	=	appService.getGenreList(app_id);			//Genre����Ʈ�� �ҷ��´�
		ArrayList	category_list	=	appService.getCategoryList(app_id);	//Category����Ʈ�� �ҷ��´�
		ArrayList	tag_list	=	appService.getTagList(app_id);		//Tag����Ʈ�� �ҷ��´�
		ArrayList	language_list	=	appService.getLanguageList(app_id);		//Language����Ʈ�� �ҷ��´�
		ArrayList	developer_list	=	appService.getDeveloperList(app_id);		//Developer	����Ʈ�� �ҷ��´�
		ArrayList	publisher_list	=	appService.getPublisherList(app_id);		//Publisher		����Ʈ�� �ҷ��´�		
		
		HashMap	positive = appService.getPositiveReview(app_id);
		HashMap	negative = appService.getNegativeReview(app_id);
		Set<String>	keys = positive.keySet();
		for(String str : keys){
			System.out.println(str+"\t"+positive.get(str));
		}
		keys = negative.keySet();
		for(String str : keys){
			System.out.println(str+"\t"+negative.get(str));
		}
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="AjaxTagSearch", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList	tagSearch(@RequestBody String input)
	{
		System.out.println(input);
		String temp = input.replace("=", "");
		System.out.println("TEMP = "+temp);
		
		ArrayList	matchList	=	appService.ajaxTagSearch(temp);
		for(TagVO vo : (ArrayList<TagVO>)matchList)
			System.out.println(vo.getName());
		System.out.println(matchList.size());
		return matchList;
		//ArrayList	list	=	appService.searchTag(text);
	}
}
