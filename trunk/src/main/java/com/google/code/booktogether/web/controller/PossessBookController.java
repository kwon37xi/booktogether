package com.google.code.booktogether.web.controller;

import java.util.Calendar;
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
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.page.PageBean;

/**
 * Library에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class PossessBookController extends AbstractController {

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
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("possessbook/getListPossessBook");
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
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "possessBookIdNum", required = false) Integer possessBookIdNum) {

		PossessBook possessBook = libraryService
				.getPossessBook(possessBookIdNum);
		
		Library library=libraryService.getLibrary(libraryIdNum, getLoginUserIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("possessbook/modifyPossessBook");
		mav.addObject("possessBook", possessBook);
		mav.addObject("library", library);

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

		if (purchaseDateYear != null && purchaseDateMonth != null
				&& purchaseDateDate != null) {

			cal.set(purchaseDateYear, purchaseDateMonth - 1, purchaseDateDate);
			possessBook.setPurchaseDate(cal.getTime());

		}

		if (beginReadYear != null && beginReadMonth != null
				&& beginReadDate != null) {

			cal.set(beginReadYear, beginReadMonth - 1, beginReadDate);
			possessBook.setBeginRead(cal.getTime());

		}

		if (endReadYear != null && endReadMonth != null && endReadDate != null) {

			cal.set(endReadYear, endReadMonth - 1, endReadDate);
			possessBook.setEndRead(cal.getTime());

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
				"redirect:/possessbook/getListPossessBook.do?userId="
						+ getLoginUserId());

	}

	/**
	 * 내 소유책으로 등록 화면 조회
	 * 
	 * @param req
	 * @return 내소유책 등록화면
	 */
	@RequestMapping("/library/insertPossessBookView.do")
	public ModelAndView handleInsertPossessBookView(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		Book book = bookService.getBook(bookIdNum);
		Library library = libraryService.getLibrary(getLoginUserId(),
				getLoginUserIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("possessbook/insertPossessBook");
		mav.addObject("bookInfo", book);
		mav.addObject("library", library);

		return mav;

	}

	/**
	 * 내 소유책으로 등록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/insertPossessBook.do")
	public ModelAndView handleInsertPossessBook(
			HttpServletRequest req,
			PossessBook possessBook,
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

		if (purchaseDateYear != null && purchaseDateMonth != null
				&& purchaseDateDate != null) {

			cal.set(purchaseDateYear, purchaseDateMonth - 1, purchaseDateDate);
			possessBook.setPurchaseDate(cal.getTime());

		}

		if (beginReadYear != null && beginReadMonth != null
				&& beginReadDate != null) {

			cal.set(beginReadYear, beginReadMonth - 1, beginReadDate);
			possessBook.setBeginRead(cal.getTime());

		}

		if (endReadYear != null && endReadMonth != null && endReadDate != null) {

			cal.set(endReadYear, endReadMonth - 1, endReadDate);
			possessBook.setEndRead(cal.getTime());

		}

		possessBook.getUser().setIdNum(getLoginUserIdNum());

		boolean result = libraryService.insertPossessBook(possessBook);

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내소유 책 등록 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내소유 책 등록 실패", RequestAttributes.SCOPE_SESSION);
		}

		// 경로 설정
		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ possessBook.getBook().getIdNum());

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
			
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "userIdNum", required = false) Integer userIdNum,
			@RequestParam(value = "possessBookIdNum", required = false) Integer possessBookIdNum) {

		PossessBook possessBook = libraryService
				.getPossessBook(possessBookIdNum);
		
		Library library=libraryService.getLibrary(libraryIdNum, userIdNum);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("possessbook/getPossessBook");
		mav.addObject("possessBook", possessBook);
		mav.addObject("library", library);

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
				"redirect:/possessbook/getListPossessBook.do?userId="
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
		mav.setViewName("possessbook/getListZoneBook");
		mav.addObject("possessBookList", possessBookList);
		mav.addObject("pageBean", pageBean);
		mav.addObject("userId", userId);

		return mav;

	}

	/**
	 * 서재내 책 검색(소유책)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchPossessBookInLibrary.do")
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

		List<PossessBook> possessBookList = libraryService.getListPossessBook(
				libraryIdNum, bookName, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("possessbook/searchPossessBook");
		mav.addObject("possessBookList", possessBookList);
		mav.addObject("libraryIdNum", libraryIdNum);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

}
