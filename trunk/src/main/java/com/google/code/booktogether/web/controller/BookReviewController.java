package com.google.code.booktogether.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.User;

/**
 * 리뷰 관련된 Controller
 * @author ParkHaeCheol
 */
@Controller
public class BookReviewController {

	/**
	 * BookReviewService
	 */
	@Resource(name="bookReviewService")
	BookReviewService bookReviewService;
	
	/**
	 * 리뷰 등록
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookReview.do")
	public ModelAndView handleInsertBookReview(HttpServletRequest req,HttpServletResponse res){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		String title=ServletRequestUtils.getStringParameter(req, "title", "");
		String review=ServletRequestUtils.getStringParameter(req, "review", "");
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isExistId=(user_id!=null) ? true : false;

		BookReview bookReview=null;
		
		if(isExistId){
			
			bookReview=new BookReview();
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setId(book_id);
			bookReview.getUser().setId(user_id);
			bookReview.setRecommend(0);
			bookReview.setTitle(title);
			bookReview.setReview(review);
			
		}else{
			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("user/login");
			mav.addObject("message","로그아웃 되었습니다.");
			return mav;
		}
		
		//별점 등록
		boolean result = bookReviewService.insertReview(bookReview);
		
		System.out.println(result);
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);

	}
	
	
	/**
	 * 리뷰 삭제
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookReview.do")
	public ModelAndView handleDeleteBookGrade(HttpServletRequest req,HttpServletResponse res){
		
		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);
		
		boolean isExistId=(user_id!=null) ? true : false;
		
		BookReview bookReview=null;
		
		if(isExistId){
			
			bookReview=new BookReview();
			bookReview.setId(id);
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setId(book_id);
			bookReview.getUser().setId(user_id);
			
		}else{
			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("user/login");
			mav.addObject("message","로그아웃 되었습니다.");
			return mav;
		}
		
		
		//별점 등록
		boolean result=bookReviewService.deleteReview(bookReview);
		
		System.out.println(result);
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		
	}
	
	
	/**
	 * 리뷰 수정
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookReview.do")
	public ModelAndView handleModifyBookGrade(HttpServletRequest req,HttpServletResponse res){
		
		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);
		
		boolean isExistId=(user_id!=null) ? true : false;
		
		BookReview bookReview=null;
		
		if(isExistId){
			
			bookReview=new BookReview();
			bookReview.setId(id);
			bookReview.setBook(new Book());
			bookReview.setUser(new User());
			bookReview.getBook().setId(book_id);
			bookReview.getUser().setId(user_id);
			
		}else{
			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("user/login");
			mav.addObject("message","로그아웃 되었습니다.");
			return mav;
		}
		
		
		//별점 등록
		boolean result=bookReviewService.modifyReview(bookReview);
		
		System.out.println(result);
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		
	}

}
