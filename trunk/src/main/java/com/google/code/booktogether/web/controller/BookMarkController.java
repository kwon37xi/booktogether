package com.google.code.booktogether.web.controller;

import java.util.ArrayList;
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
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.BookMarkList;

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

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	BookService bookService;

	// 로그 표시를 위하여
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

		Integer userIdNum = getLoginUserIdNum();

		BookMark bookMark = new BookMark();
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(userIdNum);
		bookMark.setVibeNum(0);
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
	public ModelAndView handleDeleteBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookMarkIdNum", required = false) Integer bookMarkIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUserIdNum();

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
	public ModelAndView handleModifyBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "content", required = false) String content) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUserIdNum();

		BookMark bookMark = new BookMark();
		bookMark.setContent(content);
		bookMark.setPage(page);
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(userIdNum);

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
	public ModelAndView handleModifyVibeBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookMarkIdNum", required = false) Integer bookMarkIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		Integer userIdNum = getLoginUserIdNum();

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

	/**
	 * 내가 입력한 인용구 목록 조회
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getListMyBookMark.do")
	public ModelAndView handleGetListMyBookMark(
			HttpServletRequest req,
			@RequestParam(value = "userIdNum", required = false) Integer userIdNum,
			@RequestParam(value = "startPage", required = false) Integer startPage,
			@RequestParam(value = "endPage", required = false) Integer endPage) {

		startPage = (startPage == null) ? 0 : startPage;
		endPage = (endPage == null) ? 20 : endPage;

		List<BookMarkList> bookListInBookMark=new ArrayList<BookMarkList>();
		
		List<Book> bookList = bookService.getListBookRefBookMark(userIdNum,
				startPage, endPage);

		if (bookList != null) {
			
			for (int i = 0; i < bookList.size(); i++) {
				
				BookMarkList list=new BookMarkList();
				
				list.setBook(bookList.get(i));

				Integer bookIdNum = bookList.get(i).getIdNum();

				List<BookMark>  bookMarkList= bookMarkService
						.getListMyBookMark(userIdNum, bookIdNum);

				list.setBookMarkList(bookMarkList);
				
				bookListInBookMark.add(list);

			}

		} else {
			log.info("아무것도 없다네~~~");
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListMyBookMark");
		mav.addObject("bookListInBookMark", bookListInBookMark);

		return mav;
	}
}
