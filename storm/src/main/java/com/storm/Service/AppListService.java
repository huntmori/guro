package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.AppListDAO;
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
		
		HashMap	map	=	new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list	=	appDAO.getAppList(map);
		
		return list;
	}
	
	public ArrayList	getTagList(){
		ArrayList result	=	appDAO.getTagList();
		return result;
	}
}
