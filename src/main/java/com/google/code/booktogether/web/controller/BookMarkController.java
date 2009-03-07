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
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.servlet.ModelAndView;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.BookMarkList;
import com.google.code.booktogether.web.page.PageBean;

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
	 * 인용구 등록 (로그인필요)
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

		BookMark bookMark = new BookMark();
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(getLoginUserIdNum());
		bookMark.setVibeNum(0);
		bookMark.setContent(content);
		bookMark.setPage(page);

		// 인용구 등록
		boolean result = bookMarkService.insertBookMark(bookMark);

		if (result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 등록성공", RequestAttributes.SCOPE_SESSION);
		} else {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 등록실패", RequestAttributes.SCOPE_SESSION);
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
	@RequestMapping("/book/deleteBookMark.do")
	public ModelAndView handleDeleteBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookMarkIdNum", required = false) Integer bookMarkIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		BookMark bookMark = new BookMark();
		bookMark.setIdNum(bookMarkIdNum);
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(getLoginUserIdNum());

		// 인용구 삭제
		boolean result = bookMarkService.deleteBookMark(bookMark);

		if (result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 삭제성공", RequestAttributes.SCOPE_SESSION);
		} else {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 삭제실패", RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 인용구 수정 (로그인필요)
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

		BookMark bookMark = new BookMark();
		bookMark.setContent(content);
		bookMark.setPage(page);
		bookMark.getBook().setIdNum(bookIdNum);
		bookMark.getUser().setIdNum(getLoginUserIdNum());

		// 인용구 수정
		boolean result = bookMarkService.modifyBookMark(bookMark);

		if (result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 수정성공", RequestAttributes.SCOPE_SESSION);
		} else {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"인용구 수정실패", RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 공감수 올리기 (로그인필요)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyVibe.do")
	public ModelAndView handleModifyVibeBookMark(
			HttpServletRequest req,
			@RequestParam(value = "bookMarkIdNum", required = false) Integer bookMarkIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		Integer userIdNum = getLoginUserIdNum();

		String message = "";

		if (userIdNum != null) {

			BookMark bookMark = new BookMark();
			bookMark.getUser().setIdNum(userIdNum);
			bookMark.getBook().setIdNum(bookIdNum);
			bookMark.setIdNum(bookMarkIdNum);

			// 인용구 수정
			message = bookMarkService.modifyVibe(bookMark);
		} else {
			message = "공감을 하실려면 로그인을 하셔야 합니다.";
		}

		RequestContextHolder.getRequestAttributes().setAttribute("message",
				message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 내가 입력한 인용구 목록 조회
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/getListMyBookMark.do")
	public ModelAndView handleGetListMyBookMark(
			HttpServletRequest req,
			@RequestParam(value = "userIdNum", required = false) Integer userIdNum,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);

		List<BookMarkList> bookListInBookMark = new ArrayList<BookMarkList>();

		List<Book> bookList = bookService.getListBookRefBookMark(userIdNum,
				pageBean);

		if (bookList != null) {

			for (int i = 0; i < bookList.size(); i++) {

				BookMarkList list = new BookMarkList();

				list.setBook(bookList.get(i));

				Integer bookIdNum = bookList.get(i).getIdNum();

				List<BookMark> bookMarkList = bookMarkService
						.getListMyBookMark(userIdNum, bookIdNum);

				for (int j = 0; j < bookMarkList.size(); j++) {

					bookMarkList.get(j).setContent(
							bookMarkList.get(j).getContent().replaceAll("\r\n",
									"<br/>"));

				}

				list.setBookMarkList(bookMarkList);

				bookListInBookMark.add(list);

			}

		} else {
			log.info("아무것도 없다네~~~");
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListMyBookMark");
		mav.addObject("bookListInBookMark", bookListInBookMark);
		mav.addObject("pageBean", pageBean);
		mav.addObject("userIdNum", userIdNum);

		return mav;
	}
}
