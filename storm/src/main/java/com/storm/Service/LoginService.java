package com.storm.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.LoginDAO;



public class LoginService {

	@Autowired
	public LoginDAO lDao;
	
	public HashMap loginProc(String id, String pw) {
		HashMap map = new HashMap();
		map.put("email", id);
		map.put("pw", pw);
		
		HashMap result = lDao.loginProc(map);
		System.out.println("¼­ºñ½º:loginProc:"+id+pw);
		return result;
	}
}
