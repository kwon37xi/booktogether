package com.google.code.booktogether.web.controller.abst;

import javax.annotation.Resource;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.User;

/**
 * Controller 추상화 클래스
 * 
 * @author ParkHaeCheol
 */
public abstract class AbstractController {
	
	/**
	 * UserService
	 */
	@Resource(name = "userService")
	protected UserService userService;
	
	
	

	public String getLoginUserId(){
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();

		String userId = (String) ra.getAttribute("userId",
				RequestAttributes.SCOPE_SESSION);
		
		return userId;
	}
	
	public Integer getLoginUserIdNum(){
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		
		Integer idNum = (Integer) ra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);
		
		return idNum;
	}
	
	public User getLoginUser(){
		
		Integer userIdNum=getLoginUserIdNum();
		
		User user=userService.getUserDetail(userIdNum);
		
		return user;
	}
	
}
