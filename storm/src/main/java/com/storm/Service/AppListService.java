package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.AppListDAO;
import com.storm.VO.AppVO;
import com.storm.util.PageUtil;

public class AppListService 
{
	@Autowired
	public AppListDAO	appDAO;

	public int getTotal() {
		int result = appDAO.getTotal();
		return result;
	}

	public ArrayList getAppList(int nowPage, PageUtil pInfo) {
		int start	=	(nowPage-1)*(pInfo.listCount)+1;
		int end	=	start+(pInfo.listCount-1);
		System.out.println(start+"\t"+end);
		
		HashMap	map	=	new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list	=	appDAO.getAppList(map);
		System.out.println(list.size());
		return list;
	}
	
	public ArrayList	getTagList(){
		ArrayList result	=	appDAO.getTagList();
		return result;
	}

	public AppVO getAppInfo(int app_id) {
		// TODO Auto-generated method stub
		return appDAO.getAppInfo(app_id);
	}

	public ArrayList getGenreList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getGenreList(app_id);
	}

	public ArrayList getCategoryList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getCategoryList(app_id);
	}

	public ArrayList getTagList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getTagList(app_id);
	}

	public ArrayList getLanguageList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getLanguageList(app_id);
	}

	public ArrayList getDeveloperList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getDeveloperList(app_id);
	}

	public ArrayList getPublisherList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getPublisherList(app_id);
	}

	public ArrayList getGenreList() {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getGenreList();
	}

	public ArrayList getCategoryList() {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getCategoryList();
	}

	public ArrayList getLanguageList() {
		// TODO Auto-generated method stub
		return (ArrayList)appDAO.getLanguageList();
	}
}
