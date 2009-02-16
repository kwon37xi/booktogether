package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.User;

/**
 * 리뷰 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class BookReviewController {

	/**
	 * BookReviewService
	 */
	@Resource(name = "bookReviewService")
	BookReviewService bookReviewService;

	/**
	 * BookService
	 */
	@Resource(name = "bookService")
	BookService bookService;

	/**
	 * 리뷰 등록 화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookReviewView.do")
	public ModelAndView handleInsertBookReviewView(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		Book book = null;

		if (isExistId) {

			book = bookService.getBook(book_id);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/insertReview");
		mav.addObject("book_info", book);
		return mav;

	}

	/**
	 * 리뷰 등록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookReview.do")
	public ModelAndView handleInsertBookReview(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);
		String title = ServletRequestUtils.getStringParameter(req, "title", "");
		String review = ServletRequestUtils.getStringParameter(req, "review",
				"");

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);
			bookReview.setRecommend(0);
			bookReview.setTitle(title);
			bookReview.setReview(review);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		// 리뷰 등록
		boolean result = bookReviewService.insertReview(bookReview);

		if (result) {
			sra.setAttribute("message", "등록 성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "등록 실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id=" + book_id);

	}

	/**
	 * 리뷰 삭제
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookReview.do")
	public ModelAndView handleDeleteBookGrade(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		// 리뷰 삭제
		boolean result = bookReviewService.deleteReview(bookReview);

		if (result) {
			sra.setAttribute("message", "삭제 성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "삭제 실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id=" + book_id);

	}

	/**
	 * 리뷰 수정 화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookReviewView.do")
	public ModelAndView handleModifyBookReviewView(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		bookReview = bookReviewService.getReview(bookReview);

		Book book = bookService.getBook(book_id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/modifyReview");
		mav.addObject("book_info", book);
		mav.addObject("bookReview_info", bookReview);

		return mav;

	}

	/**
	 * 리뷰 조회(모든 회원)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getReview.do")
	public ModelAndView handleGetReview(HttpServletRequest req) {

		// 파라미터 정보 변수에 세팅
		int id = ServletRequestUtils.getIntParameter(req, "id", 0);

		BookReview bookReview = bookReviewService.getReview(id);

		Book book = bookService.getBook(bookReview.getBook().getIdNum());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/getReview");
		mav.addObject("book_info", book);
		mav.addObject("bookReview_info", bookReview);

		return mav;

	}

	/**
	 * 리뷰 조회(내가 작성한 리뷰)-조회화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getMyReview.do")
	public ModelAndView handleGetMyReview(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		bookReview = bookReviewService.getReview(bookReview);

		Book book = bookService.getBook(book_id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/getReview");
		mav.addObject("book_info", book);
		mav.addObject("bookReview_info", bookReview);

		return mav;

	}

	/**
	 * 리뷰 수정
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookReview.do")
	public ModelAndView handleModifyBookReview(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int id = ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);
		String title = ServletRequestUtils.getStringParameter(req, "title", "");
		String review = ServletRequestUtils.getStringParameter(req, "review",
				"");

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);
			bookReview.setId(id);
			bookReview.setTitle(title);
			bookReview.setReview(review);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		// 별점 등록
		boolean result = bookReviewService.modifyReview(bookReview);

		if (result) {
			sra.setAttribute("message", "수정 성공",
					RequestAttributes.SCOPE_SESSION);
		} else {
			sra.setAttribute("message", "수정 실패",
					RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id=" + book_id);

	}

	/**
	 * 리뷰 추천하기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyRecommend.do")
	public ModelAndView handleModifyRecommend(HttpServletRequest req) {

		ServletRequestAttributes sra = new ServletRequestAttributes(req);

		// 파라미터 정보 변수에 세팅
		int id = ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id = ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id = (Integer) sra.getAttribute("id",
				RequestAttributes.SCOPE_SESSION);

		boolean isExistId = (user_id != null) ? true : false;

		BookReview bookReview = null;

		if (isExistId) {

			bookReview = new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setIdNum(book_id);
			bookReview.getUser().setIdNum(user_id);
			bookReview.setId(id);

		} else {

			sra.setAttribute("message", "로그아웃되었습니다.",
					RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		// 리뷰 추천하기
		String message = bookReviewService.modifyReviewRecommend(bookReview);

		sra.setAttribute("message", message, RequestAttributes.SCOPE_SESSION);

		return new ModelAndView("redirect:/book/getReview.do?id=" + id);

	}

}
