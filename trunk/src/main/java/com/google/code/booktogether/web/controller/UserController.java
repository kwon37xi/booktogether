package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;
import com.google.code.booktogether.web.page.PageBean;

/**
 * User에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class UserController {

	/**
	 * UserService
	 */
	@Resource(name = "userService")
	UserService userService;

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
	public ModelAndView handleInsertUser(HttpServletRequest req) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

		// 썸네일 이미지 저장 경로
		String realPath = multipartRequest.getSession().getServletContext()
				.getRealPath("/images/user/thumnail/");

		// 파라미터 세팅
		String userId = ServletRequestUtils.getStringParameter(
				multipartRequest, "userId", "");
		String email = ServletRequestUtils.getStringParameter(multipartRequest,
				"email", "");
		String nickname = ServletRequestUtils.getStringParameter(
				multipartRequest, "nickname", "");
		String name = ServletRequestUtils.getStringParameter(multipartRequest,
				"name", "");
		String pw = ServletRequestUtils.getStringParameter(multipartRequest,
				"pw", "");
		String blog = ServletRequestUtils.getStringParameter(multipartRequest,
				"blog", "");

		// request의 "file"을 찾아 file객체에 세팅한다.
		MultipartFile file = multipartRequest.getFile("thumnail");

		// 임의의 파일명 현재 시간+기존파일명
		String filename = System.currentTimeMillis()
				+ file.getOriginalFilename();

		// 이미지 만들기
		boolean result = userService
				.createImageResize(file, realPath, filename);

		// 지역명 세팅하기
		String zoneNames[] = ServletRequestUtils.getStringParameters(
				multipartRequest, "zone");

		Zone[] zones = new Zone[zoneNames.length];

		for (int i = 0; i < zoneNames.length; i++) {

			if (zoneNames[i] != null && !zoneNames[i].equals("")) {

				Zone zone = new Zone();

				// 지역명은 숫자다.(일련번호)
				zone.setZone(Integer.parseInt(zoneNames[i]));

				zones[i] = zone;
			}

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

		// **************
		// 사용자 저장하기
		// **************
		result = userService.insertUser(user, userPw);

		// 메세지 세팅
		String message = (result) ? "가입성공" : "가입실패";

		// 메세지 세션에 담기
		new ServletRequestAttributes(req).setAttribute("message", message,
				RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

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
	 * 로그아웃
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/user/logout.do")
	public ModelAndView handleLogout(HttpServletRequest req) {

		// 세션 삭제-전부
		req.getSession().invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/login");

		return mav;

	}

	/**
	 * 사용자 조회(로그인)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/valiadIdPwUser.do")
	public ModelAndView handleValiadIdPwUser(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 현재 페이지
		String userId = ServletRequestUtils.getStringParameter(req, "userId",
				"");
		String pw = ServletRequestUtils.getStringParameter(req, "pw", "");

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

		} else { // 실패시

			sra.setAttribute("message", "아이디가 없거나 비밀번호가 일치 하지 않습니다.",
					RequestAttributes.SCOPE_SESSION);

		}

		// 경로 설정
		return new ModelAndView("redirect:/user/login.do");

	}

	/**
	 * 사용자 목록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/user/listUser.do")
	public ModelAndView handleListUser(HttpServletRequest req) {

		// 현재 페이지
		int pageNo = ServletRequestUtils.getIntParameter(req, "pageNo", 0);

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

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 사용자 ID값
		Integer idNum = (Integer) sra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);

		User user = userService.getUserDetail(idNum);

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

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 사용자 ID값
		Integer idNum = (Integer) sra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);

		User user = userService.getUserDetail(idNum);

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
	public ModelAndView handleModifyUser(HttpServletRequest req) {

		// 파일 업로드시작
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

		ServletRequestAttributes sra = new ServletRequestAttributes(
				multipartRequest);

		// 썸네일 이미지 저장 경로
		String realPath = multipartRequest.getSession().getServletContext()
				.getRealPath("/images/user/thumnail/");

		// 사용자 ID값
		Integer idNum = (Integer) sra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);

		// 파라미터 정보 변수 세팅
		String email = ServletRequestUtils.getStringParameter(multipartRequest,
				"email", "");
		String nickname = ServletRequestUtils.getStringParameter(
				multipartRequest, "nickname", "");
		String name = ServletRequestUtils.getStringParameter(multipartRequest,
				"name", "");
		String blog = ServletRequestUtils.getStringParameter(multipartRequest,
				"blog", "");
		String currThumnail = ServletRequestUtils.getStringParameter(
				multipartRequest, "currThumnail", "");
		int userAddinfoId = ServletRequestUtils.getIntParameter(
				multipartRequest, "userAddInfoId", 0);

		User user = new User();
		user.setIdNum(idNum);

		// 썸네일 파일정보
		MultipartFile file = multipartRequest.getFile("thumnail");

		String filename = "";

		// 썸네일 이미지 변경할시
		if (file != null) {

			// 기본의 이미지 파일 삭제
			userService.deleteThumnail(realPath, currThumnail);

			// 새로운 이미지명 생성
			filename = System.currentTimeMillis() + file.getOriginalFilename();

			// 이미지 저장
			userService.createImageResize(file, realPath, filename);

			// **********
			// 여기부분 봐야함

		} else {
			// 변경 안할시 이전의 썸네일 이미지 파일명 사용
			filename = currThumnail;
		}

		// 지역명 가지고 오기
		String zoneNames[] = ServletRequestUtils.getStringParameters(
				multipartRequest, "zone");

		Zone[] zones = new Zone[zoneNames.length];

		for (int i = 0; i < zoneNames.length; i++) {

			if (zoneNames[i] != null && !zoneNames[i].equals("")) {

				Zone zone = new Zone();

				zone.setZone(Integer.parseInt(zoneNames[i]));

				zones[i] = zone;
			}

		}

		// 사용자 추가 정보
		UserAddInfo userAddInfo = new UserAddInfo();
		userAddInfo.setIdNum(userAddinfoId);
		userAddInfo.setBlog(blog);
		userAddInfo.setThumnail(filename);

		// 사용자 기본 정보
		user.setEmail(email);
		user.setNickname(nickname);
		user.setName(name);
		user.setZones(zones);// 지역명
		user.setUserAddInfo(userAddInfo);// 추가정보

		boolean result = userService.modifyUser(user);

		String message = (result) ? "수정성공" : "수정실패";

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/getUser.do");

	}

	/**
	 * 사용자 탈퇴
	 * 
	 * @param req
	 */
	@RequestMapping("/user/deleteUser.do")
	public ModelAndView handleDeleteUser(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 사용자 ID값
		Integer idNum = (Integer) sra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);

		boolean result = false;

		// 사용자 탈퇴
		result = userService.deleteUser(idNum);

		String message = (result) ? "탈퇴성공" : "탈퇴실패";

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

	}

	/**
	 * 사용자 ID찾기 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findIDView.do")
	public ModelAndView handleFindUserIDView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findIDView");

		return mav;

	}

	/**
	 * 사용자 ID찾기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findId.do")
	public ModelAndView handleFindUserId(HttpServletRequest req,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {

		// 사용자 세팅
		User user = new User();
		user.setName(name);
		user.setEmail(email);

		// 사용자 아이디 찾기
		String id = userService.findId(user);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/findIdResult");
		mav.addObject("name", name);
		mav.addObject("email", email);
		mav.addObject("id", id);

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
			@RequestParam("userId") String userId,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {

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
	 * 사용자 PW변경 화면
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
	 * 사용자 PW 변경하기
	 * 
	 * @param req
	 */
	@RequestMapping("/user/modifyPw.do")
	public ModelAndView handleModifyUserPw(HttpServletRequest req,
			@RequestParam("userId") String userId,
			@RequestParam("pw") String pw, @RequestParam("newPw") String newPw) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		String message = "";

		User user = userService.validIdPwUser(userId, pw);

		// 사용자 인증이 되었다면
		if (user != null) {

			boolean result = userService.modifyPw(user, newPw);

			if (result) {
				message = "변경되었습니다.";
			} else {
				message = "시스템 문제로 인하여 변경이 실패 하였습니다.";
			}
		} else {
			message = "입력하신 비밀번호가 일치 하지 않습니다.";
		}

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/user/login.do");

	}

	/**
	 * 지역명 삭제
	 * 
	 * @param req
	 */
	@RequestMapping("/user/deleteZone.do")
	public ModelAndView handleDeleteZone(HttpServletRequest req,
			@RequestParam("zoneIdNum") Integer zoneIdNum) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 사용자 user_ID값
		Integer id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean result = userService.deleteZone(zoneIdNum, id);

		String message = (result) ? "삭제 되었습니다." : "삭제를 실패하였습니다.";

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

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

	/**
	 * 아이디 중복 확인
	 * 
	 * @param req
	 */
	@RequestMapping("/user/duplicateUserId.do")
	public ModelAndView handleDuplicateUserId(HttpServletRequest req) {

		String userId = ServletRequestUtils.getStringParameter(req, "userId",
				"");

		boolean result = userService.duplicateUser_id(userId);

		String message = "";

		int resultDiv = 0;

		if (result) {
			message = "사용 가능합니다.";
			resultDiv = 1;
		} else {
			message = "아이디 중복입니다.";
			resultDiv = 0;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/duplicateUserId");
		mav.addObject("message", message);
		mav.addObject("resultDiv", resultDiv);
		mav.addObject("userId", userId);

		return mav;

	}

	/**
	 * 주소 찾기 화면
	 * 
	 * @param req
	 */
	@RequestMapping("/user/findAddrView.do")
	public ModelAndView handleFindAddrView(HttpServletRequest req) {

		// 주소 선택시 값을 가입창의 input태그에 옮겨야 할때 식별자
		Integer eleSeq = ServletRequestUtils.getIntParameter(req, "eleSeq", 0);

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
	public ModelAndView handleFindAddr(HttpServletRequest req) {

		Integer eleSeq = ServletRequestUtils.getIntParameter(req, "eleSeq", 0);

		String addr = ServletRequestUtils.getStringParameter(req, "addr", "");

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
