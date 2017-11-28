package com.storm.Service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.DAO.JoinDao;


public class JoinService {

	@Autowired
	public JoinDao jDao;

	@SuppressWarnings("unchecked")
	public void insertMember(String email, String nick, String pw) {
		HashMap map = new HashMap();
		map.put("email", email);
		map.put("nick", nick);
		map.put("pw", pw);
		System.out.println("dao:insertMember: "+email);
		jDao.insertMember(map);
	}

	public boolean check(String id) {
		int check = jDao.check(id);
		boolean result = true;
		if(check==1){
			result= false;
			return result;
		}	
	return result;
	}

}
