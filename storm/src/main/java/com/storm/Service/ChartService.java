package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.ChartDAO;



public class ChartService {
	//	���� Ŭ������ �����Ͻ� ������ ����ϰ�
	//	Ȥ�� �����ͺ��̽� ó���� �ʿ��ϸ� DAO�� �̿��� ���̴�.
	//	�׷��Ƿ� DAO Ŭ������ �ʿ��� �����ε� �̰� ���� <bean> ó�� �� ���Ҵ�.
	@Autowired
	public 	ChartDAO		chDAO;
	
	//	���� �Խù� ��� ó���� ���� ���� �Լ�
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
