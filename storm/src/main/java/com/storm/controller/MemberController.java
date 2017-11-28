package com.storm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.storm.Service.MemberService;
import com.storm.VO.MemberVO;

@Controller
@RequestMapping("/Member")
public class MemberController {
	
	@Autowired
	public MemberService mbService;
		
	@RequestMapping("/MemberForm")
	public ModelAndView joinForm(ModelAndView mv) {
		mv.setViewName("/Member/MemberForm");
		return mv;
	}
	
	@RequestMapping("/MemberProc")
	public ModelAndView MembeProc(MemberVO mbVO){
		
		
	
		mbService.insertMember(mbVO);
		
	
	
		RedirectView	rv = new RedirectView("../Login/LoginForm.storm");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	//중복검사
	
	
	@RequestMapping(value="/Member/CheckProc", method=RequestMethod.POST)
	@ResponseBody
	public String checkProc(Model model, String id) {
		
		//String	id = req.getParameter("id");
		System.out.println("request data : " + id);
	
		int result = mbService.checkProc(id);
		System.out.println("result : " + result);
		return String.valueOf(result);
	}

	
}
