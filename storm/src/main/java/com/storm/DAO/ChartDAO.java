package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class ChartDAO extends SqlSessionDaoSupport  {
	@Autowired
	private SqlSessionTemplate		sSession;
	//	�� DAO�� �������� ���񽺿��� ����� �����̴�.
	//	��� ���񽺿��� �� Ŭ������ new ���Ѽ� ����ϸ� �Ǵµ�....
	
	//	���񽺿����� �� DAO�� �̸� ����� ���� ����ϵ��� ��ġ�� ����.
	//	xml ������ �� Ŭ������ <bean> ó�� �س����� �ȴ�.
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
