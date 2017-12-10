package com.storm.DAO;

import java.util.ArrayList;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.CrawlVO.KeywordVO;
import com.storm.VO.AppVO;
import com.storm.VO.KeyWordVO;
import com.storm.VO.ReviewVO;
import com.storm.crawlUtil.KeywordCounter;
import com.storm.crawlUtil.WordExtractor;


@SuppressWarnings("rawtypes")
public class AppListDAO extends SqlSessionDaoSupport
{
	@Autowired
	public SqlSessionTemplate		sSession;
	
	public	static	String	nameSpace ="AppList.";
	
	
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
	
	public AppVO	getAppInfo(int app_id)
	{
		return	(AppVO)sSession.selectOne(nameSpace+"appInfo", app_id);
	}

	public ArrayList getGenreList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appGenreList", app_id);
	}

	public ArrayList getCategoryList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appCategoryList", app_id);
	}

	public ArrayList getTagList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appTagList", app_id);
	}

	public ArrayList getLanguageList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appLanguageList",app_id);
	}

	public ArrayList getDeveloperList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appDeveloperList",app_id);
	}

	public ArrayList getPublisherList(int app_id) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"appPublisherList",app_id);
	}

	public ArrayList getGenreList() {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"genreList");
	}

	public ArrayList getCategoryList() {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"categoryList");
	}

	public ArrayList getLanguageList() {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"languageList");
	}

	public ArrayList appSearchProc(HashMap map) {
		// TODO Auto-generated method stub
		ArrayList result = (ArrayList)sSession.selectList(nameSpace+"searchSelectedAppOption", map);
		
		return result;
	}

	public ArrayList ajaxTagSearch(String temp) {
		// TODO Auto-generated method stub
		return (ArrayList)sSession.selectList(nameSpace+"ajaxTagSearch", temp);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<KeyWordVO> getPositiveKeyword(int app_id) {
		ArrayList<KeyWordVO>	list = (ArrayList)sSession.selectList(nameSpace+"getPositiveKeyword", app_id);
		System.out.println(list.size());
		
		return list;
	}

	public ArrayList<KeyWordVO> getNegativeKeyword(int app_id) {
		ArrayList<KeyWordVO>	list = (ArrayList)sSession.selectList(nameSpace+"getNegativeKeyword", app_id);
		System.out.println(list.size());
		
		return list;
	}

	public AppVO getHotGame() {
		// TODO Auto-generated method stub
		int app_id	=	sSession.selectOne(nameSpace+"getHotGame");
		AppVO	result	=	sSession.selectOne(nameSpace+"getApp", app_id);
		return result;
	}

	public AppVO getRecentGame() {
		// TODO Auto-generated method stub
		int app_id	=	sSession.selectOne(nameSpace+"getRecentGame");
		AppVO	result	=	sSession.selectOne(nameSpace+"getApp", app_id);
		return result;
	}

	public AppVO getCommingSoon() {
		// TODO Auto-generated method stub
		int app_id	=	sSession.selectOne(nameSpace+"getCommingSoon");
		AppVO	result	=	sSession.selectOne(nameSpace+"getApp", app_id);
		return result;
	}

	public ArrayList getTitleMatch(String text) {
		// TODO Auto-generated method stub
		return (ArrayList) sSession.selectList(nameSpace+"appTitleSearch",text);
	}

	public ArrayList getTextMatch(String text) {
		// TODO Auto-generated method stub
		return (ArrayList) sSession.selectList(nameSpace+"appTextSearch",text);
	}
}
