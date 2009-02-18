package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.User;

/**
 * Library에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class LibraryController extends AbstractController {

	/**
	 * LibraryService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	// 사용자 Service
	@Resource(name = "userService")
	private UserService userService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	
	
	/**
	 * 비공개화면보기
	 * 
	 * @param req
	 * @return 비공개화면
	 */
	@RequestMapping("/library/unOpenLibraryView.do")
	public ModelAndView handleUnOpenLibraryView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/library/unOpenLibrary");

		return mav;

	}
	
	/**
	 * 수정화면 보기
	 * 
	 * @param req
	 * @return 수정화면
	 */
	@RequestMapping("/library/modifyLibraryView.do")
	public ModelAndView handleModifyLibraryView(HttpServletRequest req) {
		
		String userId = getLoginUserId();
		
		Library library = libraryService.getLibrary(userId);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyLibrary");
		mav.addObject("library", library);
		
		return mav;
		
	}

	/**
	 * 수정하기
	 * 
	 * @param req
	 * @return 수정하기
	 */
	@RequestMapping("/library/modifyLibrary.do")
	public ModelAndView handleModifyLibrary(HttpServletRequest req,
			Library library) {
		
		log.info(library);

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUserIdNum();

		library.getUser().setIdNum(userIdNum);

		// 사용자 아이디, 비밀번호 일치 되는치 검사
		boolean result = libraryService.modifyLibrary(library);

		// 성공시
		if (result) {

			sra.setAttribute("message", "내서재 정보 수정 실패",
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "내서재 정보 수정 성공",
					RequestAttributes.SCOPE_SESSION);

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getlibrary.do?userId="+getLoginUserId());

	}

	/**
	 * 조회하기
	 * 
	 * @param req
	 * @return 조회화면
	 */
	@RequestMapping("/library/getLibrary.do")
	public ModelAndView handleGetLibrary(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId) {

		Library library = libraryService.getLibrary(userId);

		// 서재가 있을시
		if (library != null && library.getIsOpen()==0) {

			Integer userIdNum = library.getUser().getIdNum();

			User user = userService.getUser(userIdNum);

			library.setUser(user);
			
			log.info(library);

		} else if(library != null && library.getIsOpen()==1){ // 서재가 없을시

			log.info("비공개다.");
			
			return new ModelAndView("redirect:/library/unOpenLibraryView.do");

		}else{
			
			log.info("해당 아이디가 없다.");
			
			return new ModelAndView("redirect:/");
			
		}

		// 경로 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/library");
		mav.addObject("library", library);

		return mav;

	}

}
