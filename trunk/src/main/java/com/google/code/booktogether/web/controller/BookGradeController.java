package com.google.code.booktogether.web.controller;


import java.util.List;

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
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.domain.User;

/**
 * 별점 관련된 Controller
 * @author ParkHaeCheol
 */
@Controller
public class BookGradeController {

	/**
	 * BookGradeService
	 */
	@Resource(name="bookGradeService")
	BookGradeService bookGradeService;
	
	/**
	 * BookService
	 */
	@Resource(name="bookService")
	BookService bookService;


	/**
	 * 별점 등록
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookGrade.do")
	public ModelAndView handleInsertBookGrade(HttpServletRequest req,HttpServletResponse res){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		int grade=ServletRequestUtils.getIntParameter(req, "grade", 0);
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isExistId=(user_id!=null) ? true : false;

		BookGrade bookGrade=null;
		
		if(isExistId){
			
			bookGrade=new BookGrade();
			bookGrade.setBook(new Book());
			bookGrade.setUser(new User());
			bookGrade.getBook().setId(book_id);
			bookGrade.getUser().setId(user_id);
			bookGrade.setGrade(grade);
			
		}else{
			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("user/login");
			mav.addObject("message","로그아웃 되었습니다.");
			return mav;
		}
		
		
		//별점 등록
		bookGradeService.insertGrade(bookGrade);
		
		
		
		//책 정보 가지고 오기
		Book book=bookService.getBook(book_id);
		
		List<BookGrade> bookgradelist=bookGradeService.getListBookGrade(book.getId(), 0, 5);
		
		//자기가 입력한 별점이 있는지 체크
		boolean existGrade=false;
		
		if(isExistId){
			existGrade=bookGradeService.isExistGrade(book.getId(),user_id);
		}

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/getBook");
		mav.addObject("book_info",book);
		mav.addObject("bookgradelist",bookgradelist);
		mav.addObject("existGrade",existGrade);
		
		//return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		return mav;

	}
	
	
	/**
	 * 별점 등록
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookGrade.do")
	public ModelAndView handleDeleteBookGrade(HttpServletRequest req,HttpServletResponse res){
		
		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);
		
		boolean isExistId=(user_id!=null) ? true : false;
		
		BookGrade bookGrade=null;
		
		if(isExistId){
			
			bookGrade=new BookGrade();
			bookGrade.setId(id);
			bookGrade.setBook(new Book());
			bookGrade.setUser(new User());
			bookGrade.getBook().setId(book_id);
			bookGrade.getUser().setId(user_id);
			
		}else{
			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("user/login");
			mav.addObject("message","로그아웃 되었습니다.");
			return mav;
		}
		
		
		//별점 등록
		boolean result=bookGradeService.deleteGrade(bookGrade);
		
		System.out.println(result);
		
		
		
		//책 정보 가지고 오기
		Book book=bookService.getBook(book_id);
		
		List<BookGrade> bookgradelist=bookGradeService.getListBookGrade(book.getId(), 0, 5);
		
		//자기가 입력한 별점이 있는지 체크
		boolean existGrade=false;
		
		if(isExistId){
			existGrade=bookGradeService.isExistGrade(book.getId(),user_id);
		}
		
		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/getBook");
		mav.addObject("book_info",book);
		mav.addObject("bookgradelist",bookgradelist);
		mav.addObject("existGrade",existGrade);
		
		//return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		return mav;
		
	}

}
