package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.ChartDAO;



public class ChartService {
	//	서비스 클래스는 비지니스 로직을 담당하고
	//	혹시 데이터베이스 처리가 필요하면 DAO를 이용할 것이다.
	//	그러므로 DAO 클래스가 필요할 예정인데 이것 역시 <bean> 처리 해 놓았다.
	@Autowired
	public 	ChartDAO		chDAO;
	
	//	원글 게시물 등록 처리를 위한 서비스 함수
	public ArrayList GenreView(	) {
		return (ArrayList) chDAO.GenreView();
	}
	public ArrayList LangView(){
		return (ArrayList) chDAO.LangView();
	}
	public ArrayList TagView(){
		return (ArrayList) chDAO.TagView();
	}
	

}
