package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zone;

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

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)req;


		String realPath=multipartRequest.getSession().getServletContext().getRealPath("/images/user/thumnail/");

		//request의 "file"을 찾아 file객체에 세팅한다.

		String user_id=ServletRequestUtils.getStringParameter(multipartRequest, "user_id", "");
		String email=ServletRequestUtils.getStringParameter(multipartRequest, "email", "");
		String nickname=ServletRequestUtils.getStringParameter(multipartRequest, "nickname", "");
		String name=ServletRequestUtils.getStringParameter(multipartRequest, "name", "");
		String pw=ServletRequestUtils.getStringParameter(multipartRequest, "pw", "");
		String blog=ServletRequestUtils.getStringParameter(multipartRequest, "blog", "");

		MultipartFile file = multipartRequest.getFile("thumnail");

		String filename = System.currentTimeMillis()+file.getOriginalFilename();

		boolean result=userService.createImageResize(file, realPath, filename);
		
		System.out.println(result);

		String zoneNames[]=ServletRequestUtils.getStringParameters(multipartRequest, "zone");

		Zone[] zones=new Zone[zoneNames.length];

		for(int i=0;i<zoneNames.length;i++){

			Zone zone=new Zone();

			zone.setZone(zoneNames[i]);

			zones[i]=zone;

		}
		
		UserAddInfo userAddInfo=new UserAddInfo();
		userAddInfo.setBlog(blog);
		userAddInfo.setThumnail(filename);


		User user=new User();

		user.setUser_id(user_id);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setZones(zones);
		user.setUserAddInfo(userAddInfo);

		UserPw userPw=new UserPw();
		userPw.setPw(pw);

		result=userService.insertUser(user,userPw);

		String message="";

		if(result){
			message="가입성공";
		}else{
			message="가입실패";
		}

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

	}


	/**
	 * 로그인 화면 보기
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/user/login.do")
	public ModelAndView handleLoginView(HttpServletRequest req){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/login");

		return mav;

	}



	/**
	 * 로그아웃
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/user/logout.do")
	public ModelAndView handleLogout(HttpServletRequest req){

		//세션 삭제
		req.getSession().invalidate();

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/login");

		return mav;

	}


	/**
	 * 사용자 조회(로그인)
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/valiadIdPwUser.do")
	public ModelAndView handleValiadIdPwUser(HttpServletRequest req){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//현재 페이지 
		String user_id=ServletRequestUtils.getStringParameter(req, "user_id", "");
		String pw=ServletRequestUtils.getStringParameter(req, "pw", "");

		//사용자 아이디, 비밀번호 일치 되는치 검사
		User user=userService.validIdPwUser(user_id, pw);

		//성공시
		if(user!= null) {

			sra.setAttribute("id", user.getId(), RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("nickname", user.getNickname(), RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("name", user.getName(), RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("user_id", user.getUser_id(), RequestAttributes.SCOPE_SESSION);

		}else{		//실패시 

			sra.setAttribute("message", "아이디가 없거나 비밀번호가 일치 하지 않습니다.", RequestAttributes.SCOPE_SESSION);

		}


		//경로 설정 및 Attribute 설정
		return new ModelAndView("redirect:/user/login.do");

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

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//사용자 ID값
		int p_id=ServletRequestUtils.getIntParameter(req, "id",0);
		Integer s_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isSession=(p_id==0 && s_id!=null)?true:false;

		User user=new User();

		if(isSession){
			user=userService.getUserDetail(s_id);
		}else{
			user=userService.getUserDetail(p_id);
		}

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

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//사용자 ID값
		int p_id=ServletRequestUtils.getIntParameter(req, "id",0);
		Integer s_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isSession=(p_id==0 && s_id!=null)?true:false;

		User user=new User();

		if(isSession){
			user=userService.getUserDetail(s_id);
		}else{
			user=userService.getUserDetail(p_id);
		}

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/modifyUser");
		mav.addObject("user_info",user);

		return mav;

	}



	/**
	 * 사용자 수정하기
	 * @param req
	 * @return 사용자 조회화면
	 */
	@RequestMapping("/user/modifyUser.do")
	public ModelAndView handleModifyUser(HttpServletRequest req){

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)req;

		ServletRequestAttributes sra=new ServletRequestAttributes(multipartRequest);

		String realPath=multipartRequest.getSession().getServletContext().getRealPath("/images/user/thumnail/");

		//사용자 ID값
		int id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		//파라미터 정보 변수  세팅
		String email=ServletRequestUtils.getStringParameter(multipartRequest, "email", "");
		String nickname=ServletRequestUtils.getStringParameter(multipartRequest, "nickname", "");
		String name=ServletRequestUtils.getStringParameter(multipartRequest, "name", "");
		String blog=ServletRequestUtils.getStringParameter(multipartRequest, "blog", "");
		String curr_thumnail=ServletRequestUtils.getStringParameter(multipartRequest, "curr_thumnail", "");
		int userAddinfo_id=ServletRequestUtils.getIntParameter(multipartRequest, "userAddInfo_id", 0);

		User user=new User();

		user.setId(id);


		MultipartFile file = multipartRequest.getFile("thumnail");

		String filename="";

		if(file!=null){

			boolean result=userService.deleteThumnail(realPath,curr_thumnail);
			
			System.out.println(result);

			filename = System.currentTimeMillis()+file.getOriginalFilename();

			result=userService.createImageResize(file, realPath, filename);
			
			System.out.println(result);
			
		}else{
			filename=curr_thumnail;
		}


		String zoneNames[]=ServletRequestUtils.getStringParameters(multipartRequest, "zone");

		Zone[] zones=new Zone[zoneNames.length];

		for(int i=0;i<zoneNames.length;i++){

			Zone zone=new Zone();

			zone.setUser_id(id);
			zone.setZone(zoneNames[i]);

			zones[i]=zone;

		}

		UserAddInfo userAddInfo=new UserAddInfo();
		userAddInfo.setId(userAddinfo_id);
		userAddInfo.setUser_id(id);
		userAddInfo.setBlog(blog);
		userAddInfo.setThumnail(filename);


		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setZones(zones);
		user.setUserAddInfo(userAddInfo);


		boolean result=userService.modifyUser(user);

		String message="";

		if(result){
			message="수정성공";
		}else{
			message="수정실패";
		}

		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/getUser.do?id="+id);

	}

	/**
	 * 사용자 탈퇴
	 * @param req
	 */
	@RequestMapping("/user/deleteUser.do")
	public ModelAndView handleDeleteUser(HttpServletRequest req){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//사용자 ID값
		int p_id=ServletRequestUtils.getIntParameter(req, "id",0);

		Integer s_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isSession=(p_id==0 && s_id!=null)?true:false;

		boolean result=false;

		//사용자 탈퇴
		if(isSession){
			result=userService.deleteUser(s_id);
		}else{
			result=userService.deleteUser(p_id);
		}

		String message="";

		if(result){
			message="탈퇴성공";
		}else{
			message="탈퇴실패";
		}

		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

	}


	/**
	 * 사용자 ID찾기 화면
	 * @param req
	 */
	@RequestMapping("/user/findIDView.do")
	public ModelAndView handleFindUserIDView(HttpServletRequest req){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/findIDView");

		return mav;

	}

	/**
	 * 사용자 ID찾기
	 * @param req
	 */
	@RequestMapping("/user/findID.do")
	public ModelAndView handleFindUserID(HttpServletRequest req){

		String name=ServletRequestUtils.getStringParameter(req, "name","");
		String email=ServletRequestUtils.getStringParameter(req, "email","");

		User user=new User();

		user.setName(name);
		user.setEmail(email);

		String id= userService.findID(user);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/findIDResult");
		mav.addObject("name",name);
		mav.addObject("email",email);
		mav.addObject("id",id);

		return mav;

	}


	/**
	 * 사용자 PW찾기 화면 
	 * @param req
	 */
	@RequestMapping("/user/findPWView.do")
	public ModelAndView handleFindUserPWView(HttpServletRequest req){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/findPWView");

		return mav;

	}

	/**
	 * 사용자 PW찾기
	 * @param req 
	 */
	@RequestMapping("/user/findPW.do")
	public ModelAndView handleFindUserPW(HttpServletRequest req){

		String user_id=ServletRequestUtils.getStringParameter(req, "user_id","");
		String name=ServletRequestUtils.getStringParameter(req, "name","");
		String email=ServletRequestUtils.getStringParameter(req, "email","");

		User user=new User();

		user.setUser_id(user_id);
		user.setName(name);
		user.setEmail(email);

		String message= userService.findPW(user);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/findPWResult");
		mav.addObject("user_id",user_id);
		mav.addObject("name",name);
		mav.addObject("message",message);

		return mav;

	}

	/**
	 * 사용자 PW변경 화면
	 * @param req
	 */
	@RequestMapping("/user/modifyUserPWView.do")
	public ModelAndView handleModifyUserPWView(HttpServletRequest req){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/modifyPWView");

		return mav;
	}


	/**
	 * 사용자 PW 변경하기
	 * @param req 
	 */
	@RequestMapping("/user/modifyPW.do")
	public ModelAndView handleModifyUserPW(HttpServletRequest req){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//사용자 user_ID값
		String user_id=(String)sra.getAttribute("user_id", RequestAttributes.SCOPE_SESSION);
		String pw=ServletRequestUtils.getStringParameter(req, "pw","");
		String newPw=ServletRequestUtils.getStringParameter(req, "newPw","");
		String message="";

		User user=userService.validIdPwUser(user_id, pw);

		if(user!=null){

			boolean result=userService.modifyPW(user,newPw);

			if(result){
				message="변경되었습니다.";
			}else{
				message="시스템 문제로 인하여 변경이 실패 하였습니다.";
			}
		}else{
			message="입력하신 비밀번호가 일치 하지 않습니다.";
		}

		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

	}


	/**
	 * 지역명 삭제
	 * @param req 
	 */
	@RequestMapping("/user/deleteZone.do")
	public ModelAndView handleDeleteZone(HttpServletRequest req){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//사용자 user_ID값
		int id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);
		int zone_id=ServletRequestUtils.getIntParameter(req, "zone_id",0);

		String message="";

		boolean result=userService.deleteZone(zone_id, id);

		if(result){
			message="삭제 되었습니다.";
		}else{
			message="삭제를 실패하였습니다.";
		}

		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/modifyUserView.do");

	}
	
	
	
	/**
	 * 아이디 중복 확인 화면
	 * @param req 
	 */
	@RequestMapping("/user/duplicateUserIdView.do")
	public ModelAndView handleDuplicateUserIdView(HttpServletRequest req){
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/duplicateUserId");

		return mav;
		
	}
	
	
	/**
	 * 아이디 중복 확인
	 * @param req 
	 */
	@RequestMapping("/user/duplicateUserId.do")
	public ModelAndView handleDuplicateUserId(HttpServletRequest req){
		
		String user_id=ServletRequestUtils.getStringParameter(req, "user_id", "");
		
		boolean result=userService.duplicateUser_id(user_id);
		
		String message="";
		int result_div=0;
		
		if(result){
			message="사용 가능합니다.";
			result_div=1;
		}else{
			message="아이디 중복입니다.";
			result_div=0;
		}
		
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/duplicateUserId");
		mav.addObject("message",message);
		mav.addObject("result_div",result_div);
		
		return mav;
		
	}

}
