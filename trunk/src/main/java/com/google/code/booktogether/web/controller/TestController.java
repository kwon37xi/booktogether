package com.google.code.booktogether.web.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.TestService;
import com.google.code.booktogether.web.domain.Test;


@Controller
public class TestController {

	TestService testService;

	@Resource(name="testService")
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@RequestMapping("/test.do")
	public ModelAndView handleHello(){

		Test test=new Test();
		test.setMessage("요~섹쉬~~");

		boolean result=testService.insertTest(test);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("test");
		mav.addObject("result",result);

		return mav;

	}

	//한글 깨짐 현상 때문에 테스트용
	public void charSet(String str_kr) throws UnsupportedEncodingException{
		String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8"};

		for(int i=0; i<charset.length ; i++){
			for(int j=0 ; j<charset.length ; j++){
				if(i==j) continue;

				System.out.println(charset[i]+" : "+charset[j]+" :"+new String(str_kr.getBytes(charset[i]),charset[j]));
			}
		}
	}




}
