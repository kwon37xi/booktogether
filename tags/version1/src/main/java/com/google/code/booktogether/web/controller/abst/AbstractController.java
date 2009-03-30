package com.google.code.booktogether.web.controller.abst;

import javax.annotation.Resource;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.Library;
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

	public String getLoginUserId() {

		String userId = (String) RequestContextHolder.getRequestAttributes()
				.getAttribute("userId", RequestAttributes.SCOPE_SESSION);

		return userId;
	}

	public Integer getLoginUserIdNum() {

		Integer idNum = (Integer) RequestContextHolder.getRequestAttributes()
				.getAttribute("idNum", RequestAttributes.SCOPE_SESSION);

		return idNum;
	}

	public User getLoginUser() {

		Integer userIdNum = getLoginUserIdNum();

		return userService.getUserDetail(userIdNum);
	}

	public Library getLibrary() {

		return (Library) RequestContextHolder.getRequestAttributes()
				.getAttribute("library", RequestAttributes.SCOPE_REQUEST);

	}

}
