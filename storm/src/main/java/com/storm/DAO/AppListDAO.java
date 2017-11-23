package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class AppListDAO extends SqlSessionDaoSupport
{
	@Autowired
	public SqlSessionTemplate		sSession;
	
	public int	getTotal()
	{
		return sSession.selectOne("AppList.getTotal");
	}

	public ArrayList getAppList(HashMap map) {
		ArrayList	result	=	(ArrayList)	sSession.selectList("AppList.appList", map);
		return result;
	}
	
	public ArrayList	getTagList(){
		ArrayList	result	=	(ArrayList) sSession.selectList("AppList.tagList");
		return result;
	}
}
