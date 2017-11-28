package com.storm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class InterceptorUtil extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String	url = request.getRequestURI();
		if(url.contains("Login/")) {
			return true;
		}

		HttpSession session = request.getSession();
		boolean	isLogin = SessionUtil.isLogin(session);
		
		if(isLogin == true) {

			return true;
		}
		else {

			
			response.sendRedirect("../Login/LoginForm.storm");
			return false;

		}
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}
}
