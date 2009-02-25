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
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.page.PageBean;

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

	/**
	 * BookService
	 */
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

		Library library = libraryService.getLibrary(getLoginUserId(),
				getLoginUserIdNum());

		// library.setNotice(library.getNotice().replaceAll("\r\n", "<br/>"));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyLibrary");
		mav.addObject("library", library);

		return mav;

	}

	/**
	 * 서재 수정하기
	 * 
	 * @param req
	 * @return 수정하기
	 */
	@RequestMapping("/library/modifyLibrary.do")
	public ModelAndView handleModifyLibrary(HttpServletRequest req,
			Library library) {

		if (log.isInfoEnabled()) {
			log.info(library);
		}

		library.getUser().setIdNum(getLoginUserIdNum());

		// 사용자 아이디, 비밀번호 일치 되는치 검사
		boolean result = libraryService.modifyLibrary(library);

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내서재 정보 수정 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내서재 정보 수정 실패", RequestAttributes.SCOPE_SESSION);

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getLibrary.do?userId="
				+ getLoginUserId());

	}

	/**
	 * 서재 조회하기
	 * 
	 * @param req
	 * @return 조회화면
	 */
	@RequestMapping("/library/getLibrary.do")
	public ModelAndView handleGetLibrary(HttpServletRequest req) {

		Library library = getLibrary();

		library.setNotice(library.getNotice().replaceAll("\r\n", "<br/>"));

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

		Book book = bookService.getBook(bookIdNum);

		Library library = libraryService.getLibrary(getLoginUserId(),
				getLoginUserIdNum());

		boolean result = libraryService.duplicateLibraryBook(
				library.getIdNum(), book.getIdNum());

		if (result) {

			if (log.isInfoEnabled()) {
				log.info("중복이야~~~");
			}

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"이미 서재에 등록되어있습니다.", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
					+ bookIdNum);

		} else {

			if (log.isInfoEnabled()) {
				log.info("중복아니다~~");
			}

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
			@RequestParam(value = "readDateDate", required = false) Integer readDateDate,
			@RequestParam(value = "purchaseDateYear", required = false) Integer purchaseDateYear,
			@RequestParam(value = "purchaseDateMonth", required = false) Integer purchaseDateMonth,
			@RequestParam(value = "purchaseDateDate", required = false) Integer purchaseDateDate,
			@RequestParam(value = "purchasePrice", required = false) Integer purchasePrice,
			@RequestParam(value = "beginReadYear", required = false) Integer beginReadYear,
			@RequestParam(value = "beginReadMonth", required = false) Integer beginReadMonth,
			@RequestParam(value = "beginReadDate", required = false) Integer beginReadDate,
			@RequestParam(value = "endReadYear", required = false) Integer endReadYear,
			@RequestParam(value = "endReadMonth", required = false) Integer endReadMonth,
			@RequestParam(value = "endReadDate", required = false) Integer endReadDate,
			@RequestParam(value = "quality", required = false) Integer quality,
			@RequestParam(value = "bookstate", required = false) Integer bookstate) {

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

		if (libraryBook.getIsPossess() != null && result) {

			PossessBook possessBook = new PossessBook();
			possessBook.setPurchasePrice(purchasePrice);
			possessBook.setState(bookstate);
			possessBook.setQuality(quality);
			possessBook.getBook().setIdNum(libraryBook.getBook().getIdNum());
			possessBook.getUser().setIdNum(getLoginUserIdNum());

			if (purchaseDateYear != null || purchaseDateMonth != null
					|| purchaseDateDate != null) {

				cal.set(purchaseDateYear, purchaseDateMonth - 1,
						purchaseDateDate);
				possessBook.setPurchaseDate(cal.getTime());

			}

			if (beginReadYear != null || beginReadMonth != null
					|| beginReadDate != null) {

				cal.set(beginReadYear, beginReadMonth - 1, beginReadDate);
				possessBook.setBeginRead(cal.getTime());

			}

			if (endReadYear != null || endReadMonth != null
					|| endReadDate != null) {

				cal.set(endReadYear, endReadMonth - 1, endReadDate);
				possessBook.setEndRead(cal.getTime());

			}

			result = libraryService.insertPossessBook(possessBook);

		}

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 등록 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 등록 실패", RequestAttributes.SCOPE_SESSION);

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

		PossessBook possessBook = null;

		if (libraryBook.getIsPossess() != null) {
			possessBook = libraryService.getPossessBook(libraryBook.getBook()
					.getIdNum(), getLoginUserIdNum());
		}

		Book book = bookService.getBook(libraryBook.getBook().getIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyLibraryBook");
		mav.addObject("libraryBook", libraryBook);
		mav.addObject("bookInfo", book);
		mav.addObject("possessBook", possessBook);

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
			@RequestParam(value = "beforeIsPossess", required = false) Integer beforeIsPossess,
			@RequestParam(value = "possessIdNum", required = false) Integer possessIdNum,
			@RequestParam(value = "readDateYear", required = false) Integer readDateYear,
			@RequestParam(value = "readDateMonth", required = false) Integer readDateMonth,
			@RequestParam(value = "readDateDate", required = false) Integer readDateDate,
			@RequestParam(value = "purchaseDateYear", required = false) Integer purchaseDateYear,
			@RequestParam(value = "purchaseDateMonth", required = false) Integer purchaseDateMonth,
			@RequestParam(value = "purchaseDateDate", required = false) Integer purchaseDateDate,
			@RequestParam(value = "purchasePrice", required = false) Integer purchasePrice,
			@RequestParam(value = "beginReadYear", required = false) Integer beginReadYear,
			@RequestParam(value = "beginReadMonth", required = false) Integer beginReadMonth,
			@RequestParam(value = "beginReadDate", required = false) Integer beginReadDate,
			@RequestParam(value = "endReadYear", required = false) Integer endReadYear,
			@RequestParam(value = "endReadMonth", required = false) Integer endReadMonth,
			@RequestParam(value = "endReadDate", required = false) Integer endReadDate,
			@RequestParam(value = "quality", required = false) Integer quality,
			@RequestParam(value = "bookstate", required = false) Integer bookstate) {

		// 0은 등록, 1은 삭제, 2는 수정
		Integer serviceDiv = 1;

		Calendar cal = Calendar.getInstance();

		if (readDateYear == null || readDateMonth == null
				|| readDateDate == null) {

			libraryBook.setReadDate(new Date());

		} else {

			cal.set(readDateYear, readDateMonth - 1, readDateDate);

			libraryBook.setReadDate(cal.getTime());

		}

		PossessBook possessBook = new PossessBook();

		possessBook.setIdNum(possessIdNum);
		possessBook.setPurchasePrice(purchasePrice);
		possessBook.setState(bookstate);
		possessBook.setQuality(quality);
		possessBook.getBook().setIdNum(libraryBook.getBook().getIdNum());
		possessBook.getUser().setIdNum(getLoginUserIdNum());

		if (purchaseDateYear != null || purchaseDateMonth != null
				|| purchaseDateDate != null) {

			cal.set(purchaseDateYear, purchaseDateMonth - 1, purchaseDateDate);
			possessBook.setPurchaseDate(cal.getTime());

		}

		if (beginReadYear != null || beginReadMonth != null
				|| beginReadDate != null) {

			cal.set(beginReadYear, beginReadMonth - 1, beginReadDate);
			possessBook.setBeginRead(cal.getTime());

		}

		if (endReadYear != null || endReadMonth != null || endReadDate != null) {

			cal.set(endReadYear, endReadMonth - 1, endReadDate);
			possessBook.setEndRead(cal.getTime());

		}

		boolean result = false;

		Integer isPossess = libraryBook.getIsPossess();

		isPossess = (isPossess == null) ? 0 : isPossess;

		beforeIsPossess = (beforeIsPossess == null) ? 0 : beforeIsPossess;

		if (isPossess.intValue() != beforeIsPossess.intValue()) {

			if (libraryBook.getIsPossess() == null) {

				serviceDiv = 1;

			} else if (libraryBook.getIsPossess() == 1) {

				serviceDiv = 0;

			}

			result = libraryService.modifyLibraryBook(libraryBook, possessBook,
					serviceDiv);

		} else {

			serviceDiv = 2;

			result = libraryService.modifyLibraryBook(libraryBook, possessBook,
					serviceDiv);
		}

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 수정 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 수정 실패", RequestAttributes.SCOPE_SESSION);

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

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 삭제 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"서재에 책 삭제 실패", RequestAttributes.SCOPE_SESSION);

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

		if (libraryBookList != null) {

			libraryBook = null;

			for (int i = 0; i < libraryBookList.size(); i++) {

				Integer bookIdNum = libraryBookList.get(i).getBook().getIdNum();

				libraryBookList.get(i).setBook(bookService.getBook(bookIdNum));

			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/getListLibraryBook");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("state", state);
		mav.addObject("libraryIdNum", libraryIdNum);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

	/**
	 * 내가 보유한 책 목록 조회
	 * 
	 * @param req
	 * @return 내가 보유한 책 목록 화면
	 */
	@RequestMapping("/library/getListPossessBook.do")
	public ModelAndView handleGetListPossessBook(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);

		List<PossessBook> possessBookList = libraryService.getListPossessBook(
				userId, pageBean);

		if (possessBookList != null) {

			for (int i = 0; i < possessBookList.size(); i++) {

				Integer bookIdNum = possessBookList.get(i).getBook().getIdNum();

				possessBookList.get(i).setBook(bookService.getBook(bookIdNum));
			}

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/getListPossessBook");
		mav.addObject("possessBookList", possessBookList);
		mav.addObject("pageBean", pageBean);
		mav.addObject("userId", userId);

		return mav;

	}

	/**
	 * 내가 보유한 책 수정하기 화면 조회
	 * 
	 * @param req
	 * @return 내가 보유한 책 수정화면
	 */
	@RequestMapping("/library/modifyPossessBookView.do")
	public ModelAndView handleModifyPossessBookView(
			HttpServletRequest req,
			@RequestParam(value = "possessBookIdNum", required = false) Integer possessBookIdNum) {

		PossessBook possessBook = libraryService
				.getPossessBook(possessBookIdNum);

		if (possessBook != null) {

			Integer bookIdNum = possessBook.getBook().getIdNum();

			possessBook.setBook(bookService.getBook(bookIdNum));

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyPossessBook");
		mav.addObject("possessBook", possessBook);

		return mav;

	}

	/**
	 * 내가 보유한 책 수정하기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/modifyPossessBook.do")
	public ModelAndView handleModifyPossessBook(
			HttpServletRequest req,
			PossessBook possessBook,
			@RequestParam(value = "readDateYear", required = false) Integer readDateYear,
			@RequestParam(value = "readDateMonth", required = false) Integer readDateMonth,
			@RequestParam(value = "readDateDate", required = false) Integer readDateDate,
			@RequestParam(value = "purchaseDateYear", required = false) Integer purchaseDateYear,
			@RequestParam(value = "purchaseDateMonth", required = false) Integer purchaseDateMonth,
			@RequestParam(value = "purchaseDateDate", required = false) Integer purchaseDateDate,
			@RequestParam(value = "beginReadYear", required = false) Integer beginReadYear,
			@RequestParam(value = "beginReadMonth", required = false) Integer beginReadMonth,
			@RequestParam(value = "beginReadDate", required = false) Integer beginReadDate,
			@RequestParam(value = "endReadYear", required = false) Integer endReadYear,
			@RequestParam(value = "endReadMonth", required = false) Integer endReadMonth,
			@RequestParam(value = "endReadDate", required = false) Integer endReadDate) {

		Calendar cal = Calendar.getInstance();

		if (purchaseDateYear != null || purchaseDateMonth != null
				|| purchaseDateDate != null) {

			cal.set(purchaseDateYear, purchaseDateMonth - 1, purchaseDateDate);
			possessBook.setPurchaseDate(cal.getTime());

		}

		if (beginReadYear != null || beginReadMonth != null
				|| beginReadDate != null) {

			cal.set(beginReadYear, beginReadMonth - 1, beginReadDate);
			possessBook.setBeginRead(cal.getTime());

		}

		if (endReadYear != null || endReadMonth != null || endReadDate != null) {

			cal.set(endReadYear, endReadMonth - 1, endReadDate);
			possessBook.setEndRead(cal.getTime());

		}

		if (log.isInfoEnabled()) {
			log.info(possessBook);
		}

		boolean result = libraryService.modifyPossessBook(possessBook);

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내소유 책 수정 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내소유 책 수정 실패", RequestAttributes.SCOPE_SESSION);
		}

		// 경로 설정
		return new ModelAndView(
				"redirect:/library/getListPossessBook.do?userId="
						+ getLoginUserId());

	}

	/**
	 * 내가 보유한 책 조회
	 * 
	 * @param req
	 * @return 내가 보유한 책 조회화면
	 */
	@RequestMapping("/library/getPossessBook.do")
	public ModelAndView handleGetPossessBook(
			HttpServletRequest req,
			@RequestParam(value = "possessBookIdNum", required = false) Integer possessBookIdNum) {

		PossessBook possessBook = libraryService
				.getPossessBook(possessBookIdNum);

		if (possessBook != null) {

			Integer bookIdNum = possessBook.getBook().getIdNum();

			possessBook.setBook(bookService.getBook(bookIdNum));

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/getPossessBook");
		mav.addObject("possessBook", possessBook);

		return mav;

	}

	/**
	 * 내가 보유한 책 삭제
	 * 
	 * @param req
	 * @return 내가 보유한 책 목록
	 */
	@RequestMapping("/library/deletePossessBook.do")
	public ModelAndView handleDeletePossessBook(
			HttpServletRequest req,
			@RequestParam(value = "possessBookIdNum", required = false) Integer possessBookIdNum) {

		boolean result = libraryService.deletePossessBook(getLoginUserIdNum(),
				possessBookIdNum);

		if (log.isInfoEnabled()) {
			log.info(result);
		}

		// 경로 설정
		return new ModelAndView(
				"redirect:/library/getListPossessBook.do?userId="
						+ getLoginUserId());

	}

	/**
	 * 내 생활반경의 책가지고 오기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/getListZoneBook.do")
	public ModelAndView handleGetListZoneBook(HttpServletRequest req,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(20);

		List<PossessBook> possessBookList = libraryService.getListZoneBook(
				userId, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/getListZoneBook");
		mav.addObject("possessBookList", possessBookList);
		mav.addObject("pageBean", pageBean);
		mav.addObject("userId", userId);

		return mav;

	}

	/**
	 * 서재 검색
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchLibrary.do")
	public ModelAndView handleSearchLibrary(
			HttpServletRequest req,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "searchType", required = false) String searchType) {

		List<User> userList = null;

		if (query != null && searchType != null) {

			if (log.isInfoEnabled()) {
				log.info(query);
				log.info(searchType);
			}

			userList = libraryService.getListSearchLibrary(query, searchType);

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/searchLibrary");
		mav.addObject("userList", userList);

		return mav;

	}

	/**
	 * 서재 검색
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchBookInLibrary.do")
	public ModelAndView handleSearchBookInLibrary(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;
		bookName = (bookName == null) ? "" : bookName.trim();

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(5);

		boolean moreLibraryBookList = false;
		boolean morePosssessBookList = false;

		List<LibraryBook> libraryBookList = libraryService.getListLibraryBook(
				libraryIdNum, bookName, pageBean);

		if (libraryBookList != null) {

			for (int i = 0; i < libraryBookList.size(); i++) {

				Book book = bookService.getBook(libraryBookList.get(i)
						.getBook().getIdNum());

				libraryBookList.get(i).setBook(book);

			}
		}
		moreLibraryBookList = (libraryBookList.size() >= pageBean.getPageSize()) ? true
				: false;

		List<PossessBook> posssessBookList = libraryService.getListPossessBook(
				libraryIdNum, bookName, pageBean);

		if (posssessBookList != null) {
			for (int i = 0; i < posssessBookList.size(); i++) {

				Book book = bookService.getBook(posssessBookList.get(i)
						.getBook().getIdNum());

				posssessBookList.get(i).setBook(book);

			}
		}

		morePosssessBookList = (posssessBookList.size() >= pageBean.getPageSize()) ? true
				: false;

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/searchBookInLibrary");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("posssessBookList", posssessBookList);
		mav.addObject("moreLibraryBookList", moreLibraryBookList);
		mav.addObject("morePosssessBookList", morePosssessBookList);
		mav.addObject("libraryIdNum", libraryIdNum);

		return mav;

	}
}
