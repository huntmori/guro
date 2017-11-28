package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartDAO extends SqlSessionDaoSupport  {
	@Autowired
	private SqlSessionTemplate		sSession;
	//	이 DAO는 보나마나 서비스에서 사용할 예정이다.
	//	당근 서비스에서 이 클래스를 new 시켜서 사용하면 되는데....
	
	//	서비스에서도 이 DAO를 미리 만들어 놓고 사용하도록 조치해 놓자.
	//	xml 파일이 이 클래스를 <bean> 처리 해놓으면 된다.
	public ArrayList GenreView() {
		ArrayList result = (ArrayList) sSession.selectList("Chart.genreView");
		return (ArrayList) sSession.selectList("Chart.genreView");
	}
	public ArrayList LangView() {
		ArrayList result = (ArrayList) sSession.selectList("Chart.langView");
		return (ArrayList) sSession.selectList("Chart.langView");
	}
	public ArrayList TagView(){
		return (ArrayList) sSession.selectList("Chart.tagView");
	}
}
