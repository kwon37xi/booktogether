package com.google.code.booktogether.web.controller;

import java.util.List;

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
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.page.PageBean;

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

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	BookService bookService;

	// 로그 표시를 위하여
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

	/**
	 * 내가 입력한 별점 목록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getListMyBookGrade.do")
	public ModelAndView handleListBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "userIdNum", required = false) Integer userIdNum,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);

		List<BookGrade> bookGradeList = bookGradeService.getListMyBookGrade(
				userIdNum, pageBean);

		if (bookGradeList != null) {

			for (int i = 0; i < bookGradeList.size(); i++) {

				Integer bookIdNum = bookGradeList.get(i).getBook().getIdNum();

				Book book = bookService.getBook(bookIdNum);

				bookGradeList.get(i).setBook(book);

			}

		} else {

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListMyGrade");
		mav.addObject("bookGradeList", bookGradeList);
		mav.addObject("pageBean", pageBean);

		return mav;

	}
}
