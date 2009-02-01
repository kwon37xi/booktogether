package com.google.code.booktogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloAnnotController {

	@RequestMapping("/test.do")
	public ModelAndView handleHello(){
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("hello");
		return mav;
	
	}

}
