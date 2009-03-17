package com.google.code.booktogether.web.controller;

import java.util.Calendar;
import java.util.Date;
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

import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.page.PageBean;

/**
 * Library에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class LibraryBookController extends AbstractController {

	/**
	 * LibraryService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 서재에 책 등록하기 화면
	 * 
	 * @param req
	 * @return 등록하기 화면
	 */
	@RequestMapping("/library/insertLibraryBookView.do")
	public ModelAndView handleInsertLibraryBookView(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		Book book = bookService.getBook(bookIdNum);

		Library library = libraryService.getLibrary(getLoginUserId(),
				getLoginUserIdNum());

		boolean result = libraryService.duplicateLibraryBook(
				library.getIdNum(), book.getIdNum());

		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"이미 서재에 등록되어있습니다.", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		} else {

			if (log.isInfoEnabled()) {
				log.info("중복아니다~~");
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("librarybook/insertLibraryBook");
			mav.addObject("library", library);
			mav.addObject("bookInfo", book);

			return mav;
		}

	}

	/**
	 * 서재에 책 등록하기
	 * 
	 * @param req
	 * @return 서재 시작화면
	 */
	@RequestMapping("/library/insertLibraryBook.do")
	public ModelAndView handleInsertLibraryBook(
			HttpServletRequest req,
			LibraryBook libraryBook,
			@RequestParam(value = "readDateYear", required = false) Integer readDateYear,
			@RequestParam(value = "readDateMonth", required = false) Integer readDateMonth,
			@RequestParam(value = "readDateDate", required = false) Integer readDateDate) {

		if (log.isInfoEnabled()) {
			log.info(libraryBook);
		}

		Calendar cal = Calendar.getInstance();

		if (readDateYear == null || readDateMonth == null
				|| readDateDate == null) {

			libraryBook.setReadDate(new Date());

		} else {

			cal.set(readDateYear, readDateMonth - 1, readDateDate);

			libraryBook.setReadDate(cal.getTime());

		}

		boolean result = libraryService.insertLibraryBook(libraryBook);

		// 실패시
		if (!result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 등록 실패", RequestAttributes.SCOPE_SESSION);

			// 경로 설정
			return new ModelAndView("redirect:/message.do");

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getLibrary.do?userId="
				+ getLoginUserId());

	}

	/**
	 * 서재 책 수정화면 보기
	 * 
	 * @param req
	 * @return 서재책 수정화면
	 */
	@RequestMapping("/library/modifyLibraryBookView.do")
	public ModelAndView handleModifyLibraryBookView(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "libraryBookIdNum", required = false) Integer libraryBookIdNum) {

		Library library = libraryService.getLibrary(libraryIdNum,
				getLoginUserIdNum());

		LibraryBook libraryBook = libraryService
				.getLibraryBook(libraryBookIdNum);

		Book book = bookService.getBook(libraryBook.getBook().getIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("librarybook/modifyLibraryBook");
		mav.addObject("libraryBook", libraryBook);
		mav.addObject("library", library);
		mav.addObject("bookInfo", book);

		return mav;

	}

	/**
	 * 서재에 책 수정하기
	 * 
	 * @param req
	 * @return 서재 시작화면
	 */
	@RequestMapping("/library/modifyLibraryBook.do")
	public ModelAndView handleModifyLibraryBook(
			HttpServletRequest req,
			LibraryBook libraryBook,
			@RequestParam(value = "readDateYear", required = false) Integer readDateYear,
			@RequestParam(value = "readDateMonth", required = false) Integer readDateMonth,
			@RequestParam(value = "readDateDate", required = false) Integer readDateDate) {

		Calendar cal = Calendar.getInstance();

		if (readDateYear == null || readDateMonth == null
				|| readDateDate == null) {

			libraryBook.setReadDate(new Date());

		} else {

			cal.set(readDateYear, readDateMonth - 1, readDateDate);

			libraryBook.setReadDate(cal.getTime());

		}

		boolean result = libraryService.modifyLibraryBook(libraryBook);

		// 실패시
		if (!result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 수정 실패", RequestAttributes.SCOPE_SESSION);

			// 경로 설정
			return new ModelAndView("redirect:/message.do");

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getLibrary.do?userId="
				+ getLoginUserId());

	}

	/**
	 * 서재에 책 삭제하기
	 * 
	 * @param req
	 * @return 서재 시작화면
	 */
	@RequestMapping("/library/deleteLibraryBook.do")
	public ModelAndView handleDeleteLibraryBook(
			HttpServletRequest req,
			@RequestParam(value = "libraryBookIdNum", required = false) Integer libraryBookIdNum) {

		if (log.isInfoEnabled()) {
			log.info("삭제 할려고한다.");
		}

		boolean result = libraryService.deleteLibraryBook(libraryBookIdNum);

		// 실패시
		if (!result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 삭제 실패", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/message.do");

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getLibrary.do?userId="
				+ getLoginUserId());

	}

	/**
	 * 서재에 책 목록 보여주기
	 * 
	 * @param req
	 * @return 서재 책 목록 화면
	 */
	@RequestMapping("/library/getListLibraryBook.do")
	public ModelAndView handleGetListLibraryBook(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "state", required = false) Integer state,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		LibraryBook libraryBook = new LibraryBook();
		libraryBook.getLibrary().setIdNum(libraryIdNum);
		libraryBook.setState(state);

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);

		List<LibraryBook> libraryBookList = libraryService.getListLibraryBook(
				libraryBook, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("librarybook/getListLibraryBook");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("state", state);
		mav.addObject("libraryIdNum", libraryIdNum);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

	/**
	 * 서재내 책 검색(읽고 싶은책, 읽은책, 읽고 있는책)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchLibraryBookInLibrary.do")
	public ModelAndView handleSearchBookInLibrary(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;
		bookName = (bookName == null) ? "" : bookName.trim();

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(10);

		List<LibraryBook> libraryBookList = libraryService.getListLibraryBook(
				libraryIdNum, bookName, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("librarybook/searchLibraryBook");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("libraryIdNum", libraryIdNum);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

}
