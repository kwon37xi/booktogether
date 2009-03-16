package com.google.code.booktogether.web.controller;

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

import com.google.code.booktogether.service.BlogService;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;
import com.google.code.booktogether.web.page.PageBean;

/**
 * 리뷰 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class BookReviewController extends AbstractController {

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
	 * BlogService
	 */
	@Resource(name = "blogService")
	BlogService blogService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 리뷰 등록 화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookReviewView.do")
	public ModelAndView handleInsertBookReviewView(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		Book book = bookService.getBook(bookIdNum);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/insertReview");
		mav.addObject("bookInfo", book);
		return mav;

	}

	/**
	 * 리뷰 등록
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookReview.do")
	public ModelAndView handleInsertBookReview(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "isPostBlog", required = false) Integer isPostBlog,
			@RequestParam(value = "review", required = false) String review) {

		Integer userIdNum = getLoginUserIdNum();

		BookReview bookReview = new BookReview();
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(userIdNum);
		bookReview.setRecommend(0);
		bookReview.setTitle(title);
		bookReview.setReview(review);

		UserBlog userBlog = null;

		if (isPostBlog != null && isPostBlog.equals(1)) {

			userBlog = blogService.getUserBlog(userIdNum);

		}

		// 리뷰 등록
		boolean result = bookReviewService.insertReview(bookReview, userBlog);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"등록 실패", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 리뷰 삭제
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookReview.do")
	public ModelAndView handleDeleteBookGrade(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		BookReview bookReview = new BookReview();
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(getLoginUserIdNum());

		// 리뷰 삭제
		boolean result = bookReviewService.deleteReview(bookReview);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"삭제 실패", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 리뷰 수정 화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookReviewView.do")
	public ModelAndView handleModifyBookReviewView(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		BookReview bookReview = new BookReview();
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(getLoginUserIdNum());

		bookReview = bookReviewService.getReview(bookReview);

		ReviewBlogPost reviewBlogPost = new ReviewBlogPost();
		reviewBlogPost.setBookIdNum(bookIdNum);
		reviewBlogPost.setUserIdNum(getLoginUserIdNum());

		reviewBlogPost = blogService.getReviewBlogPost(reviewBlogPost);

		Book book = bookService.getBook(bookIdNum);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/modifyReview");
		mav.addObject("bookInfo", book);
		mav.addObject("bookReviewInfo", bookReview);
		mav.addObject("reviewBlogPost", reviewBlogPost);

		return mav;

	}

	/**
	 * 리뷰 조회(모든 회원)
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getReview.do")
	public ModelAndView handleGetReview(
			HttpServletRequest req,
			@RequestParam(value = "bookReviewIdNum", required = false) Integer bookReviewIdNum) {

		BookReview bookReview = bookReviewService.getReview(bookReviewIdNum);

		Book book = null;

		if (bookReview != null) {

			bookReview.setReview(bookReview.getReview().replaceAll("\r\n",
					"<br/>"));

			book = bookService.getBook(bookReview.getBook().getIdNum());

		} else {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"조회하고자하는 리뷰가 없습니다.", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getReview");
		mav.addObject("bookInfo", book);
		mav.addObject("bookReviewInfo", bookReview);

		return mav;

	}

	/**
	 * 리뷰 조회(내가 작성한 리뷰)-조회화면
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getMyReview.do")
	public ModelAndView handleGetMyReview(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		BookReview bookReview = new BookReview();
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(getLoginUserIdNum());

		bookReview = bookReviewService.getReview(bookReview);

		Book book = bookService.getBook(bookIdNum);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getReview");
		mav.addObject("bookInfo", book);
		mav.addObject("bookReviewInfo", bookReview);

		return mav;

	}

	/**
	 * 리뷰 수정
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookReview.do")
	public ModelAndView handleModifyBookReview(
			HttpServletRequest req,
			@RequestParam(value = "bookReviewIdNum", required = false) Integer bookReviewIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "postNum", required = false) String postNum,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "review", required = false) String review) {

		BookReview bookReview = new BookReview();
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(getLoginUserIdNum());
		bookReview.setIdNum(bookReviewIdNum);
		bookReview.setTitle(title);
		bookReview.setReview(review);

		UserBlog userBlog = null;

		if (postNum != null && !postNum.equals("")) {
			userBlog = blogService.getUserBlog(getLoginUserIdNum());
		}

		// 별점 등록
		boolean result = bookReviewService.modifyReview(bookReview, userBlog,
				postNum);

		if (!result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"수정 실패", RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="
				+ bookIdNum);

	}

	/**
	 * 리뷰 추천하기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyRecommend.do")
	public ModelAndView handleModifyRecommend(
			HttpServletRequest req,
			@RequestParam(value = "bookReviewIdNum", required = false) Integer bookReviewIdNum,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		BookReview bookReview = new BookReview();
		bookReview.setIdNum(bookReviewIdNum);
		bookReview.getBook().setIdNum(bookIdNum);
		bookReview.getUser().setIdNum(getLoginUserIdNum());

		// 리뷰 추천하기
		String message = bookReviewService.modifyReviewRecommend(bookReview);

		if (message != null) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					message, RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/message.do");
		}

		return new ModelAndView("redirect:/book/getReview.do?bookReviewIdNum="
				+ bookReviewIdNum);

	}

	/**
	 * 내가 입력한 리뷰 목록 가지고 오기
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/getListMyReview.do")
	public ModelAndView handleGetListMyReview(
			HttpServletRequest req,
			@RequestParam(value = "userIdNum", required = false) Integer userIdNum,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);

		List<BookReview> bookReviewList = bookReviewService
				.getListMyBookReview(userIdNum, pageBean);

		if (bookReviewList != null) {

			for (int i = 0; i < bookReviewList.size(); i++) {

				Integer bookIdNum = bookReviewList.get(i).getBook().getIdNum();

				bookReviewList.get(i).setBook(bookService.getBook(bookIdNum));

			}

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListMyReview");
		mav.addObject("bookReviewList", bookReviewList);
		mav.addObject("pageBean", pageBean);
		mav.addObject("userIdNum", userIdNum);

		return mav;

	}

	/**
	 * 리뷰목록 조회
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/getListBookReview.do")
	public ModelAndView handleListBookReview(
			HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(20);
		pageBean.setLimit(3);

		Book book = bookService.getBook(bookIdNum);

		List<BookReview> bookReviewList = bookReviewService.getListBookReview(
				bookIdNum, pageBean);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/getListBookReview");
		mav.addObject("bookReviewList", bookReviewList);
		mav.addObject("bookInfo", book);
		mav.addObject("pageBean", pageBean);

		return mav;
	}

}
