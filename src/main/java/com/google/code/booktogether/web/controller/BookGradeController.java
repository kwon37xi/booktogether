package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
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
	private BookGradeService bookGradeService;

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	/**
	 * 별점 등록 (로그인 필요)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookGrade.do")
	public ModelAndView handleInsertBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "grade", required = false) Integer grade) {

		BookGrade bookGrade = new BookGrade();
		bookGrade.setGrade(grade);
		bookGrade.getBook().setIdNum(bookIdNum);
		bookGrade.getUser().setIdNum(getLoginUserIdNum());

		// 별점 등록
		boolean result = bookGradeService.insertGrade(bookGrade);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"별점 등록실패", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 별점 삭제 (로그인필요)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookGrade.do")
	public ModelAndView handleDeleteBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookGradeIdNum", required = false) Integer bookGradeIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		// 파라미터 정보 변수에 세팅
		Integer userIdNum = getLoginUserIdNum();

		BookGrade bookGrade = new BookGrade();
		bookGrade.setIdNum(bookGradeIdNum);
		bookGrade.getBook().setIdNum(bookIdNum);
		bookGrade.getUser().setIdNum(userIdNum);

		// 별점 등록
		boolean result = bookGradeService.deleteGrade(bookGrade);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"별점 삭제실패", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 내가 입력한 별점 목록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/getListMyBookGrade.do")
	public ModelAndView handleListMyBookGrade(
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
		mav.addObject("userIdNum", userIdNum);

		return mav;

	}

	/**
	 * 별점 목록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getListBookGrade.do")
	public ModelAndView handleListBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setLimit(3);
		pageBean.setPageSize(20);
		
		Book book=bookService.getBook(bookIdNum);

		List<BookGrade> bookGradeList = bookGradeService.getListBookGrade(
				bookIdNum, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListBookGrade");
		mav.addObject("bookGradeList", bookGradeList);
		mav.addObject("pageBean", pageBean);
		mav.addObject("bookInfo", book);

		return mav;

	}
}
