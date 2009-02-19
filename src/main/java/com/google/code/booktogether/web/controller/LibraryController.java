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
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
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

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "bookService")
	private BookService bookService;

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

			sra.setAttribute("message", "내서재 정보 수정 성공",
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "내서재 정보 수정 실패",
					RequestAttributes.SCOPE_SESSION);

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getlibrary.do?userId="
				+ getLoginUserId());

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
		if (library != null && library.getIsOpen() == 0) {

			Integer userIdNum = library.getUser().getIdNum();

			User user = userService.getUser(userIdNum);

			library.setUser(user);

			log.info(library);

		} else if (library != null && library.getIsOpen() == 1) { // 서재가 없을시

			log.info("비공개다.");

			return new ModelAndView("redirect:/library/unOpenLibraryView.do");

		} else {

			log.info("해당 아이디가 없다.");

			return new ModelAndView("redirect:/");

		}

		// 경로 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/library");
		mav.addObject("library", library);

		return mav;

	}

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

		String userId = getLoginUserId();

		Book book = bookService.getBook(bookIdNum);

		Library library = libraryService.getLibrary(userId);

		boolean result = libraryService.duplicateLibraryBook(
				library.getIdNum(), book.getIdNum());

		if (result) {

			log.info("중복이야~~~");

			new ServletRequestAttributes(req).setAttribute("message",
					"이미 서재에 등록되어있습니다.", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
					+ bookIdNum);

		} else {

			log.info("중복아니다~~");

			ModelAndView mav = new ModelAndView();
			mav.setViewName("library/insertLibraryBook");
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

		log.info(libraryBook);

		if (readDateYear == null || readDateMonth == null
				|| readDateDate == null) {

			libraryBook.setReadDate(new Date());

		} else {

			Calendar cal = Calendar.getInstance();

			cal.set(readDateYear, readDateMonth, readDateDate);

			libraryBook.setReadDate(cal.getTime());

		}

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		boolean result = libraryService.insertLibraryBook(libraryBook);

		// 성공시
		if (result) {

			sra.setAttribute("message", "서재에 책 등록 성공",
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "서재에 책 등록 실패",
					RequestAttributes.SCOPE_SESSION);

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
			@RequestParam(value = "libraryBookIdNum", required = false) Integer libraryBookIdNum) {

		LibraryBook libraryBook = libraryService
				.getLibraryBook(libraryBookIdNum);

		Book book = bookService.getBook(libraryBook.getBook().getIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyLibraryBook");
		mav.addObject("libraryBook", libraryBook);
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

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		if (readDateYear == null || readDateMonth == null
				|| readDateDate == null) {

			libraryBook.setReadDate(new Date());

		} else {

			Calendar cal = Calendar.getInstance();

			cal.set(readDateYear, readDateMonth, readDateDate);

			libraryBook.setReadDate(cal.getTime());

		}

		log.info(libraryBook);

		boolean result = libraryService.modifyLibraryBook(libraryBook);

		// 성공시
		if (result) {

			sra.setAttribute("message", "서재에 책 수정 성공",
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "서재에 책 수정 실패",
					RequestAttributes.SCOPE_SESSION);

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

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		boolean result = libraryService.deleteLibraryBook(libraryBookIdNum);

		// 성공시
		if (result) {

			sra.setAttribute("message", "서재에 책 삭제 성공",
					RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			sra.setAttribute("message", "서재에 책 삭제 실패",
					RequestAttributes.SCOPE_SESSION);

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
			@RequestParam(value = "state", required = false) Integer state) {

		LibraryBook libraryBook = new LibraryBook();
		libraryBook.getLibrary().setIdNum(libraryIdNum);
		libraryBook.setState(state);

		List<LibraryBook> libraryBookList = libraryService.getListLibraryBook(
				libraryBook, 0, 5);

		if (libraryBookList != null) {

			libraryBook = null;

			for (int i = 0; i < libraryBookList.size(); i++) {

				Integer bookIdNum = libraryBookList.get(i).getBook().getIdNum();

				Book book = bookService.getBook(bookIdNum);

				libraryBookList.get(i).setBook(book);

			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/getListLibraryBook");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("state", state);

		return mav;

	}

}
