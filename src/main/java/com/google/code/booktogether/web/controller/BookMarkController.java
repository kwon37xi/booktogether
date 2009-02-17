package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.springframework.web.servlet.ModelAndView;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.BookMark;

/**
 * 인용구 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class BookMarkController extends AbstractController {

	/**
	 * BookMarkService
	 */
	@Resource(name = "bookMarkService")
	BookMarkService bookMarkService;

	//로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());
	
	
	
	/**
	 * 인용구 등록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookMark.do")
	public ModelAndView handleInsertBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "content", required = false) String content) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUser();

		BookMark bookMark = new BookMark();
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(userIdNum);
		bookMark.setContent(content);
		bookMark.setPage(page);

		// 인용구 등록
		boolean result = bookMarkService.insertBookMark(bookMark);

		if (result) {
			sra.setAttribute("message", "인용구 등록성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "인용구 등록실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 별점 삭제
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookMark.do")
	public ModelAndView handleDeleteBookMark(HttpServletRequest req,
			HttpServletResponse res) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		Integer bookMarkIdNum = ServletRequestUtils.getIntParameter(req,
				"bookMarkIdNum", 0);
		Integer bookIdNum = ServletRequestUtils.getIntParameter(req,
				"bookIdNum", 0);

		Integer userIdNum = getLoginUser();

		BookMark bookMark = new BookMark();
		bookMark.setIdNum(bookMarkIdNum);
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(userIdNum);

		// 인용구 삭제
		boolean result = bookMarkService.deleteBookMark(bookMark);

		if (result) {
			sra.setAttribute("message", "인용구 삭제성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "인용구 삭제실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 인용구 수정
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookMark.do")
	public ModelAndView handleModifyBookMark(HttpServletRequest req,
			HttpServletResponse res) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		Integer bookIdNum = ServletRequestUtils.getIntParameter(req,
				"bookIdNum", 0);
		Integer page = ServletRequestUtils.getIntParameter(req, "page", 0);
		String content = ServletRequestUtils.getStringParameter(req, "content",
				"");

		Integer userIdNum = getLoginUser();

		BookMark bookMark = new BookMark();
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(userIdNum);
		bookMark.setContent(content);
		bookMark.setPage(page);

		// 인용구 수정
		boolean result = bookMarkService.modifyBookMark(bookMark);

		if (result) {
			sra.setAttribute("message", "인용구 수정성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "인용구 수정실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 공감수 올리기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyVibe.do")
	public ModelAndView handleModifyVibeBookMark(HttpServletRequest req,
			HttpServletResponse res) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		Integer bookMarkIdNum = ServletRequestUtils.getIntParameter(req,
				"bookMarkIdNum", 0);
		Integer bookIdNum = ServletRequestUtils.getIntParameter(req,
				"bookIdNum", 0);

		Integer userIdNum = getLoginUser();

		BookMark bookMark = new BookMark();
		bookMark.getUser().setIdNum(userIdNum);
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.setIdNum(bookMarkIdNum);

		// 인용구 수정
		String message = bookMarkService.modifyVibe(bookMark);

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

}
