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
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.page.PageBean;

/**
 * Book에 관련된 Controller
 * 
 * @author ParkHaeCheol
 * 
 */
@Controller
public class BookController extends AbstractController {

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	/**
	 * BookGradeService
	 */
	@Resource(name = "bookGradeService")
	private BookGradeService bookGradeService;

	/**
	 * BookReviewService
	 */
	@Resource(name = "bookReviewService")
	private BookReviewService bookReviewService;

	/**
	 * BookMarkService
	 */
	@Resource(name = "bookMarkService")
	private BookMarkService bookMarkService;

	/**
	 * 책 정보 가지고 오기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getBook.do")
	public ModelAndView handleGetBook(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		// 책 정보 가지고 오기
		Book book = bookService.getBook(bookIdNum);

		if (book == null) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"해당 책 내용이 없습니다.", RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/book/searchBook.do");

		} else {

			boolean moreGrade = false;
			boolean moreBookMark = false;
			boolean moreReview = false;

			PageBean pageBean = new PageBean();
			pageBean.setPageNo(1);
			pageBean.setPageSize(5);

			List<BookGrade> bookGradeList = bookGradeService.getListBookGrade(
					book.getIdNum(), pageBean);

			moreGrade = pageBean.isNextPage();

			List<BookReview> bookReviewList = bookReviewService
					.getListBookReview(book.getIdNum(), pageBean);

			moreBookMark = pageBean.isNextPage();

			List<BookMark> bookMarkList = bookMarkService.getListBookMark(book
					.getIdNum(), pageBean);

			moreReview = pageBean.isNextPage();

			Integer userIdNum = getLoginUserIdNum();

			// 자기가 입력한 별점이 있는지 체크
			boolean existGrade = bookGradeService.isExistGrade(book.getIdNum(),
					userIdNum);

			// 자기가 입력한 리뷰가 있는지 체크
			boolean existReview = bookReviewService.isExistReview(book
					.getIdNum(), userIdNum);

			// 경로 설정 및 Attribute 설정
			ModelAndView mav = new ModelAndView();
			mav.setViewName("book/getBook");
			mav.addObject("bookInfo", book);
			mav.addObject("bookGradeList", bookGradeList);
			mav.addObject("bookReviewList", bookReviewList);
			mav.addObject("bookMarkList", bookMarkList);
			mav.addObject("existGrade", existGrade);
			mav.addObject("existReview", existReview);
			mav.addObject("moreGrade", moreGrade);
			mav.addObject("moreBookMark", moreBookMark);
			mav.addObject("moreReview", moreReview);

			return mav;
		}
	}

	/**
	 * 책 수정화면 보기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookView.do")
	public ModelAndView handleModifyViewBook(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		// 책 정보 가지고 오기
		Book book = bookService.getBook(bookIdNum);

		// 경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/modifyBook");
		mav.addObject("bookInfo", book);

		return mav;

	}

	/**
	 * 책 검색
	 * 
	 * @param req
	 */
	@RequestMapping("/book/searchBook.do")
	public ModelAndView handleSearchBook(
			HttpServletRequest req,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		query = (query == null || query.equals("")) ? "스프링" : query;
		searchType = (searchType == null) ? "all" : searchType;
		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setLimit(5);
		pageBean.setPageSize(20);

		// 책검색 목록 가지고 오기
		List<Book> bookList = bookService.searchBook(query, searchType,
				pageBean);

		// 경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/searchBook");
		mav.addObject("bookList", bookList);
		mav.addObject("pageBean", pageBean);

		return mav;

	}

	/**
	 * 책 검색후 책자세히 보기할때 책 내용이 DB에 있는지 검사
	 * 
	 * @param req
	 */
	@RequestMapping("/book/checkBook.do")
	public ModelAndView handleCheckBook(HttpServletRequest req,
			@RequestParam(value = "ISBN", required = false) String isbn) {

		// 책 정보 가지고 오기
		Book book = bookService.checkBook(isbn);

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ book.getIdNum());

	}

}
