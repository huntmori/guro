package com.storm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.util.StringUtil;
import com.storm.Service.LoginService;



@Controller
public class LoginController {
	@Autowired
	public LoginService	lService;
	
	@RequestMapping("Login/LoginForm")
	public String loginForm() {
		System.out.println("�׽�Ʈ1:loginForm");
		return "Login/LoginForm";
	}
	
	@RequestMapping("Login/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		HashMap result = lService.loginProc(id, pw);
		System.out.println("�׽�Ʈ3:"+result);
		
		if(result != null && !StringUtil.isNull((String)result.get("EMAIL"))) {
			session.setAttribute("UID", (String)result.get("EMAIL"));
			session.setAttribute("NICK", (String)result.get("NICK"));
			session.setAttribute("KEY", result.get("KEY"));
		}
		
		else { 
			new RedirectView("../Login/LoginForm.storm");
		}
		
		RedirectView rv = new RedirectView("/storm/"); //���� �α��������� �ٲپ���
		ModelAndView mv = new ModelAndView();
		
		mv.setView(rv);
		System.out.println("�׽�Ʈ4:loginProc");
		return mv;
	}
	
	@RequestMapping("Login/Logout")
	public ModelAndView logout(HttpSession session, RedirectView rv, ModelAndView mv) {
		session.removeAttribute("UID");
		rv.setUrl("../Login/LoginForm.storm");
		mv.setView(rv);
		return mv;	
	}
}




