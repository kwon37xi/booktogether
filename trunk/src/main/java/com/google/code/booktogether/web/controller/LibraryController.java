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
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Library;

/**
 * Library에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class LibraryController extends AbstractController {

	/**
	 * myLibraryService
	 */
	@Resource(name = "libraryService")
	LibraryService libraryService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 수정화면 보기
	 * 
	 * @param req
	 * @return 수정화면
	 */
	@RequestMapping("/ㅣibrary/modifyLibraryView.do")
	public ModelAndView handleModifyLibraryView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/myLibrary/modifyMyLibrary");

		return mav;

	}

	/**
	 * 수정하기
	 * 
	 * @param req
	 * @return 수정하기
	 */
	@RequestMapping("/ㅣibrary/modifyLibrary.do")
	public ModelAndView handleModifyLibrary(HttpServletRequest req,
			Library library) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUser();

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
		return new ModelAndView("redirect:/ㅣibrary/getㅣibrary.do");

	}
	
	
	/**
	 * 조회하기
	 * 
	 * @param req
	 * @return 조회화면
	 */
	@RequestMapping("/ㅣibrary/getLibrary.do")
	public ModelAndView handleGetLibrary(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId
			) {
		
		// 사용자 아이디, 비밀번호 일치 되는치 검사
		Library library= libraryService.getLibrary(userId);
		
		// 성공시
		if (library!=null) {
			
			log.info("서재가 있다.");
			
		} else { // 실패시
			
			log.info("서재가 없다.");
			
		}
		
		// 경로 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ㅣibrary/getLibrary");
		mav.addObject("library",library);

		return mav;
		
	}

}
