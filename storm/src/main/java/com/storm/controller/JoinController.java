package com.storm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.Service.JoinService;



@Controller
@RequestMapping("/Join")
public class JoinController {
	
	@Autowired
	public JoinService jService;
		
	@RequestMapping("/JoinForm")
	public ModelAndView joinForm(ModelAndView mv) {
		mv.setViewName("/Join/JoinForm");
		System.out.println("컨트롤러:joinForm:리턴");
		return mv;
	}
	
	@RequestMapping("/JoinProc")
	public ModelAndView insertMember(HttpServletRequest req) {
		String email = req.getParameter("email");
		String nick = req.getParameter("nick");
		String pw = req.getParameter("pw");
		System.out.println("컨트롤러: "+ email+nick+pw);
		jService.insertMember(email, nick, pw);

		RedirectView rv = new RedirectView("../Login/LoginForm.storm");
		ModelAndView mv = new ModelAndView();

		mv.setView(rv);
		System.out.println("컨트롤러:insertMember:리턴");
		return mv;	
	}
	
//	@RequestMapping("/Idcheck")
//	public ModelAndView check(@RequestParam String id, ModelAndView mv) {
//		
//		boolean check = jService.check(id);
//		// 1이면 존재하는 아이디 == false
//		if(check) {
//		// 0이면 true
//			mv.addObject("RESULT", "0");
//			mv.setViewName("Join/Imsi");
//		}
//		else {
//			mv.addObject("RESULT", "1");
//			mv.setViewName("Join/Imsi");
//		}		
//		return mv;
//	}
	

}
