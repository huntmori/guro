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
		System.out.println("테스트1:loginForm");
		return "Login/LoginForm";
	}
	
	@RequestMapping("Login/LoginProc")
	public ModelAndView loginProc(HttpServletRequest req, HttpSession session) {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		HashMap result = lService.loginProc(id, pw);
		System.out.println("테스트3:"+result);
		
		if(result != null && !StringUtil.isNull((String)result.get("EMAIL"))) {
			session.setAttribute("UID", (String)result.get("EMAIL"));
			session.setAttribute("NICK", (String)result.get("NICK"));
			session.setAttribute("KEY", result.get("KEY"));
		}
		
		else { 
			new RedirectView("../Login/LoginForm.storm");
		}
		
		RedirectView rv = new RedirectView("/storm/"); //여기 로그인폼에서 바꾸었음
		ModelAndView mv = new ModelAndView();
		
		mv.setView(rv);
		System.out.println("테스트4:loginProc");
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




