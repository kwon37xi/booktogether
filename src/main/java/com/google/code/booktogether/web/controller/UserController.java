package com.google.code.booktogether.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.User;

/**
 * User에 관련된 Controller
 * @author ParkHaeCheol
 */
@Controller
public class UserController {

	/**
	 * UserService
	 */
	@Resource(name="userService")
	UserService userService;

	/**
	 * Book 도메인 DI
	 */
	@Resource(name="userDomain")
	User user;


	/**
	 * 사용자 등록 화면
	 * @return
	 */
	@RequestMapping("/user/join.do")
	public ModelAndView handleJoin(){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/join");

		return mav;
	}

	/**
	 * 사용자 등록(회원가입)
	 * @param req
	 * @return 사용자 등록 결과화면
	 */
	@RequestMapping("/user/insertUser.do")
	public ModelAndView handleInsertUser(HttpServletRequest req){

		String user_id=ServletRequestUtils.getStringParameter(req, "user_id", "");
		String email=ServletRequestUtils.getStringParameter(req, "email", "");
		String nickname=ServletRequestUtils.getStringParameter(req, "nickname", "");
		String name=ServletRequestUtils.getStringParameter(req, "name", "");
		String pw=ServletRequestUtils.getStringParameter(req, "pw", "");

		user.setUser_id(user_id);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setPw(pw);
		
		boolean result=userService.insertUser(user);

		
		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/joinResult");
		mav.addObject("result",result);

		return mav;

	}


	/**
	 * 사용자 목록
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/listUser.do")
	public ModelAndView handleListUser(HttpServletRequest req){

		//현재 페이지 
		int pageNo=ServletRequestUtils.getIntParameter(req, "pageNo", 0);

		//페이지 클래스에 세팅
		PageBean pageBean=new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPagesize(10);

		//책 목록 가지고 오기
		List<User> userlist=userService.getListUser(pageBean);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/listUser");
		mav.addObject("userlist",userlist);
		mav.addObject("pageBean",pageBean);

		return mav;

	}

	/**
	 * 사용자 정보 가지고 오기
	 * @param req
	 * @return 사용자 정보 조회화면
	 */
	@RequestMapping("/user/getUser.do")
	public ModelAndView handleGetUser(HttpServletRequest req){

		//사용자 ID값
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);

		//책 정보 가지고 오기
		User user=userService.getUser(id);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/getUser");
		mav.addObject("user_info",user);

		return mav;

	}

	/**
	 * 사용자 수정화면 보기
	 * @param req
	 * @return 사용자 수정화면
	 */
	@RequestMapping("/user/modifyUserView.do")
	public ModelAndView handleModifyUserView(HttpServletRequest req){

		//사용자 ID값
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);

		//책 정보 가지고 오기
		User user=userService.getUser(id);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/modifyUser");
		mav.addObject("user_info",user);

		return mav;

	}

	/**
	 * 사용자 수정하기
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/user/modifyUser.do")
	public ModelAndView handleModifyUser(HttpServletRequest req){

		//파라미터 정보 변수  세팅
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);
		String email=ServletRequestUtils.getStringParameter(req, "email", "");
		String nickname=ServletRequestUtils.getStringParameter(req, "nickname", "");
		String name=ServletRequestUtils.getStringParameter(req, "name", "");
		String pw=ServletRequestUtils.getStringParameter(req, "pw", "");
		
		user.setId(id);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setPw(pw);
		
		boolean result=userService.modifyUser(user);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/getUser");
		mav.addObject("user_info",user);
		mav.addObject("result",result);

		return mav;

	}
	
	/**
	 * 책 삭제
	 * @param req
	 * @param res
	 */
	@RequestMapping("/user/deleteUser.do")
	public void handleDeleteUser(HttpServletRequest req,HttpServletResponse res){

		//사용자 ID값
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);

		//책 정보 가지고 오기
		boolean result=userService.deleteUser(id);

		try {
			res.sendRedirect("/user/listUser.do");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	

}
