package com.google.code.booktogether.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserBlog;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;
import com.google.code.booktogether.web.page.PageBean;

/**
 * User에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class UserController extends AbstractController {

	/**
	 * UserService
	 */
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 사용자 등록 화면
	 * 
	 * @return
	 */
	@RequestMapping("/user/join.do")
	public ModelAndView handleJoin() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/join");

		return mav;
	}

	/**
	 * 사용자 등록(회원가입)
	 * 
	 * @param req
	 * @return 사용자 등록 결과화면
	 */
	@RequestMapping("/user/insertUser.do")
	public ModelAndView handleInsertUser(
			HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "pw", required = false) String pw,
			@RequestParam(value = "blog", required = false) String blog,
			@RequestParam(value = "zone", required = false) String zoneNames[],
			@RequestParam(value = "thumnail", required = false) MultipartFile file,
			@RequestParam(value = "isPostBlog", required = false) Integer isPostBlog,
			@RequestParam(value = "webServer", required = false) String webServer,
			@RequestParam(value = "validBlog", required = false) String validBlog,
			@RequestParam(value = "etcInfo", required = false) String etcInfo,
			@RequestParam(value = "blogPw", required = false) String blogPw,
			@RequestParam(value = "blogId", required = false) String blogId) {

		// 썸네일 이미지 저장 경로
		String realPath = req.getSession().getServletContext().getRealPath(
				"/images/user/thumnail/");

		// 이미지 만들기
		String filename = userService.createImageResize(file, realPath);

		Zone[] zones = null;

		// 지역명 세팅하기
		if (zoneNames != null) {

			zones = new Zone[zoneNames.length];

			for (int i = 0; i < zoneNames.length; i++) {

				if (zoneNames[i] != null && !zoneNames[i].equals("")) {

					Zone zone = new Zone();

					// 지역명은 숫자다.(일련번호)
					zone.setZone(Integer.parseInt(zoneNames[i]));

					zones[i] = zone;
				}

			}
		} else {
			zones = new Zone[0];
		}

		// 추가정보 빈에 세팅
		UserAddInfo userAddInfo = new UserAddInfo();
		userAddInfo.setBlog(blog);
		userAddInfo.setThumnail(filename);

		// 사용자 기본정보 세팅
		User user = new User();
		user.setUserId(userId);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setZones(zones);
		user.setUserAddInfo(userAddInfo);

		// 비밀번호 세팅
		UserPw userPw = new UserPw();
		userPw.setPw(pw);

		UserBlog userBlog = null;

		if (isPostBlog != null && isPostBlog.equals(1)
				&& validBlog.equals("true")) {

			// 블로그 정보 세팅
			userBlog = new UserBlog();
			userBlog.setWebServer(webServer);
			userBlog.setBlog(blog);
			userBlog.setBlogDefault(1);
			userBlog.setId(blogId);

			if (webServer.indexOf("egloos") != -1) {
				userBlog.setPw(blogPw);
			}

		}

		// **************
		// 사용자 저장하기
		// **************
		boolean result = userService.insertUser(user, userPw, userBlog);

		if (!result) {

			// 메세지 세션에 담기 new
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"가입실패", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		return new ModelAndView("redirect:/index.do");

	}

	/**
	 * 로그인 화면 보기
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/user/login.do")
	public ModelAndView handleLoginView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/login");

		return mav;

	}

	/**
	 * 로그아웃하기
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/user/logout.do")
	public ModelAndView handleLogout(HttpServletRequest req) {

		// 세션 삭제-전부
		req.getSession().invalidate();

		return new ModelAndView("redirect:/index.do");

	}

	/**
	 * 로그인하기(아이디 비밀번호 일치하는지 검사)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/valiadIdPwUser.do")
	public ModelAndView handleValiadIdPwUser(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "pw", required = false) String pw) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 사용자 아이디, 비밀번호 일치 되는치 검사
		User user = userService.validIdPwUser(userId, pw);

		// 성공시
		if (user != null) {

			sra.setAttribute("idNum", user.getIdNum(),
					RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("nickname", user.getNickname(),
					RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("name", user.getName(),
					RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("userId", user.getUserId(),
					RequestAttributes.SCOPE_SESSION);
			sra.setAttribute("thumnail", user.getUserAddInfo().getThumnail(),
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "아이디가 없거나 비밀번호가 일치 하지 않습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		// 경로 설정
		return new ModelAndView("redirect:/index.do");

	}

	/**
	 * 사용자 목록(사용안함)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/listUser.do")
	public ModelAndView handleListUser(HttpServletRequest req,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		// 페이지 클래스에 세팅
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(10);

		// 책 목록 가지고 오기
		List<User> userlist = userService.getListUser(pageBean);

		// 경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/listUser");
		mav.addObject("userList", userlist);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

	/**
	 * 사용자 정보 가지고 오기
	 * 
	 * @param req
	 * @return 사용자 정보 조회화면
	 */
	@RequestMapping("/user/getUser.do")
	public ModelAndView handleGetUser(HttpServletRequest req) {

		User user = getLoginUser();

		// 경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/getUser");
		mav.addObject("userInfo", user);

		return mav;

	}

	/**
	 * 사용자 수정화면 보기
	 * 
	 * @param req
	 * @return 사용자 수정화면
	 */
	@RequestMapping("/user/modifyUserView.do")
	public ModelAndView handleModifyUserView(HttpServletRequest req) {

		User user = getLoginUser();

		// 경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/modifyUser");
		mav.addObject("userInfo", user);

		return mav;

	}

	/**
	 * 사용자 수정하기
	 * 
	 * @param req
	 * @return 사용자 조회화면
	 */
	@RequestMapping("/user/modifyUser.do")
	public ModelAndView handleModifyUser(
			HttpServletRequest req,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "pw", required = false) String pw,
			@RequestParam(value = "blog", required = false) String blog,
			@RequestParam(value = "zone", required = false) String zoneNames[],
			@RequestParam(value = "currThumnail", required = false) String currThumnail,
			@RequestParam(value = "isdefaultThumnail", required = false) String isdefaultThumnail,
			@RequestParam(value = "userAddInfoIdNum", required = false) Integer userAddInfoIdNum,
			@RequestParam(value = "thumnail", required = false) MultipartFile file) {

		// 썸네일 이미지 저장 경로
		String realPath = req.getSession().getServletContext().getRealPath(
				"/images/user/thumnail/");

		// 사용자 ID값
		Integer userIdNum = getLoginUserIdNum();

		String filename = "";

		if (isdefaultThumnail.equals("yes")) {
			filename = "userDefault.png";
		} else {
			// 썸네일 이미지 변경할시
			if (file != null) {

				// 기본의 이미지 파일 삭제
				userService.deleteThumnail(realPath, currThumnail);

				// 이미지 저장
				filename = userService.createImageResize(file, realPath);

				// **********
				// 여기부분 봐야함

			} else {
				// 변경 안할시 이전의 썸네일 이미지 파일명 사용
				filename = currThumnail;
			}
		}

		Zone[] zones = null;

		if (zoneNames != null) {

			// 지역명 가지고 오기
			zones = new Zone[zoneNames.length];

			for (int i = 0; i < zoneNames.length; i++) {

				if (zoneNames[i] != null && !zoneNames[i].equals("")) {

					Zone zone = new Zone();

					zone.setUserIdNum(getLoginUserIdNum());
					zone.setZone(Integer.parseInt(zoneNames[i]));

					zones[i] = zone;
				}

			}
		} else {

			zones = new Zone[0];

		}

		// 사용자 추가 정보
		UserAddInfo userAddInfo = new UserAddInfo();
		userAddInfo.setIdNum(userAddInfoIdNum);
		userAddInfo.setBlog(blog);
		userAddInfo.setThumnail(filename);

		// 사용자 기본 정보
		User user = new User();
		user.setIdNum(userIdNum);
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setZones(zones);// 지역명
		user.setUserAddInfo(userAddInfo);// 추가정보

		boolean result = userService.modifyUser(user);

		RequestContextHolder.getRequestAttributes().setAttribute("thumnail",
				filename, RequestAttributes.SCOPE_SESSION);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"수정실패", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		return new ModelAndView("redirect:/user/getUser.do");

	}

	/**
	 * 사용자 탈퇴
	 * 
	 * @param req
	 */
	@RequestMapping("/user/deleteUser.do")
	public ModelAndView handleDeleteUser(HttpServletRequest req) {

		// 사용자 ID값
		Integer userIdNum = getLoginUserIdNum();

		boolean result = false;

		// 사용자 탈퇴
		result = userService.deleteUser(userIdNum);

		if (!result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"탈퇴실패", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		return new ModelAndView("redirect:/user/login.do");

	}

	/**
	 * 사용자 ID찾기 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findIdView.do")
	public ModelAndView handleFindUserIdView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findIdView");

		return mav;

	}

	/**
	 * 사용자 ID찾기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findId.do")
	public ModelAndView handleFindUserId(HttpServletRequest req,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email) {

		// 사용자 세팅
		User user = new User();
		user.setName(name);
		user.setEmail(email);

		// 사용자 아이디 찾기
		String userId = userService.findId(user);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findIdResult");
		mav.addObject("name", name);
		mav.addObject("email", email);
		mav.addObject("userId", userId);

		return mav;

	}

	/**
	 * 사용자 PW찾기 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findPwView.do")
	public ModelAndView handleFindUserPwView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findPwView");

		return mav;

	}

	/**
	 * 사용자 PW찾기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findPw.do")
	public ModelAndView handleFindUserPw(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email) {

		User user = new User();

		user.setUserId(userId);
		user.setName(name);
		user.setEmail(email);

		// 사용자 PW찾기
		String message = userService.findPw(user);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findPwResult");
		mav.addObject("userInfo", user);
		mav.addObject("message", message);

		return mav;

	}

	/**
	 * 사용자 Pw변경 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/modifyUserPWView.do")
	public ModelAndView handleModifyUserPwView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/modifyPwView");

		return mav;
	}

	/**
	 * 사용자 Pw 변경하기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/modifyPw.do")
	public ModelAndView handleModifyUserPw(HttpServletRequest req,
			@RequestParam(value = "pw", required = false) String pw,
			@RequestParam(value = "newPw", required = false) String newPw) {

		String userId = getLoginUserId();

		String message = null;

		User user = userService.validIdPwUser(userId, pw);

		// 사용자 인증이 되었다면
		if (user != null) {

			boolean result = userService.modifyPw(user, newPw);

			if (!result) {
				message = "시스템 문제로 인하여 변경이 실패 하였습니다.";
			}
		} else {
			message = "입력하신 비밀번호가 일치 하지 않습니다.";
		}

		if (message == null) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					message, RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		return new ModelAndView("redirect:/index.do");

	}

	/**
	 * 지역명 삭제
	 * 
	 * @param req
	 */
	@RequestMapping("/user/deleteZone.do")
	public ModelAndView handleDeleteZone(
			HttpServletRequest req,
			@RequestParam(value = "zoneIdNum", required = false) Integer zoneIdNum) {

		// 사용자 userId값
		Integer userIdNum = getLoginUserIdNum();

		boolean result = userService.deleteZone(zoneIdNum, userIdNum);

		if (!result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"삭제를 실패하였습니다.", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		return new ModelAndView("redirect:/user/modifyUserView.do");

	}

	/**
	 * 아이디 중복 확인 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/duplicateUserIdView.do")
	public ModelAndView handleDuplicateUserIdView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/duplicateUserId");

		return mav;

	}

	@RequestMapping("/user/duplicateUserIdAjax.do")
	public void duplicateUserIdAjax(HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "userId", required = false) String userId) {

		JSONObject json = new JSONObject();
		boolean result = userService.duplicateUserId(userId);

		String message = null;
		String retValue = null;

		if (result) {
			message = "사용 가능합니다.";
			retValue = "success";
		} else {
			message = "아이디 중복입니다.";
			retValue = "failure";
		}

		json.element("message", message);
		json.element("status", retValue);

		try {
			res.setContentType("application/json;charset=utf-8");
			res.getWriter().print(json.toString());
		} catch (IOException e) {
			throw new BooktogetherException("아이디 중복처리 실패", e);
		}
	}

	/**
	 * 주소 찾기 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findAddrView.do")
	public ModelAndView handleFindAddrView(HttpServletRequest req,
			@RequestParam(value = "eleSeq", required = false) Integer eleSeq) {

		// 주소 선택시 값을 가입창의 input태그에 옮겨야 할때 식별자

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findAddr");
		mav.addObject("eleSeq", eleSeq);

		return mav;

	}

	/**
	 * 주소 찾기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findAddr.do")
	public ModelAndView handleFindAddr(HttpServletRequest req,
			@RequestParam(value = "eleSeq", required = false) Integer eleSeq,
			@RequestParam(value = "addr", required = false) String addr) {

		// 우편정보목록
		List<Zipcode> zipcodeList = userService.getListAddr(addr);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findAddr");
		mav.addObject("eleSeq", eleSeq);
		mav.addObject("addr", addr);
		mav.addObject("zipcodeList", zipcodeList);

		return mav;

	}

}
