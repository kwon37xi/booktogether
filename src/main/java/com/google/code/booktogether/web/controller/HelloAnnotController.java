package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.CurrentDateService;


@Controller
public class HelloAnnotController {
	
	CurrentDateService currentDateService;
	

	@RequestMapping("/test.do")
	public ModelAndView handleHello(){
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("hello");
		mav.addObject("currDate", currentDateService.getCurrentDate());
		
		return mav;
	
	}
	
	@Resource(name="currentDateService")
	public void setCurrentDateService(CurrentDateService currentDateService){
		this.currentDateService=currentDateService;
	}

}
