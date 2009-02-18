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

import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.BookGrade;

/**
 * 별점 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class BookGradeController extends AbstractController {

	/**
	 * BookGradeService
	 */
	@Resource(name = "bookGradeService")
	BookGradeService bookGradeService;

	
	//로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 별점 등록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookGrade.do")
	public ModelAndView handleInsertBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "grade", required = false) Integer grade) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUserIdNum();

		BookGrade bookGrade = new BookGrade();
		bookGrade.setGrade(grade);
		bookGrade.getBook().setIdNum(bookIdNum);
		bookGrade.getUser().setIdNum(userIdNum);

		// 별점 등록
		boolean result = bookGradeService.insertGrade(bookGrade);

		if (result) {
			sra.setAttribute("message", "별점 등록성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "별점 등록실패",
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
	@RequestMapping("/book/deleteBookGrade.do")
	public ModelAndView handleDeleteBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookGradeIdNum", required = false) Integer bookGradeIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		Integer userIdNum = getLoginUserIdNum();

		BookGrade bookGrade = new BookGrade();
		bookGrade.setIdNum(bookGradeIdNum);
		bookGrade.getBook().setIdNum(bookIdNum);
		bookGrade.getUser().setIdNum(userIdNum);

		// 별점 등록
		boolean result = bookGradeService.deleteGrade(bookGrade);

		if (result) {
			sra.setAttribute("message", "별점 삭제성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "별점 삭제실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ userIdNum);

	}

}
