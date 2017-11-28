package com.storm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/AdminPage")
public class adminController 
{
	@RequestMapping("/Test")
	public ModelAndView text()
	{
		System.out.println("HELLO MASTER");

		ModelAndView	mv	= new ModelAndView();
		mv.setViewName("manageIndex");
		return mv;
	}
}
