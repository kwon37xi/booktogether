package com.google.code.booktogether.web.controller.abst;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Controller 추상화 클래스
 * 
 * @author ParkHaeCheol
 */
public abstract class AbstractController {

	public Integer getLoginUser(){
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();

		Integer idNum = (Integer) ra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);
		
		return idNum;
	}
	
}
