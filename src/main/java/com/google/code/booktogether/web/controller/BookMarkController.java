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
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.User;

/**
 * 인용구 관련된 Controller
 * @author ParkHaeCheol
 */
@Controller
public class BookMarkController {

	/**
	 * BookMarkService
	 */
	@Resource(name="bookMarkService")
	BookMarkService bookMarkService;

	/**
	 * 인용구 등록
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/insertBookMark.do")
	public ModelAndView handleInsertBookMark(HttpServletRequest req,HttpServletResponse res){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//파라미터 정보 변수에 세팅
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		int page=ServletRequestUtils.getIntParameter(req, "page", 0);
		String content=ServletRequestUtils.getStringParameter(req, "content", "");

		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isExistId=(user_id!=null) ? true : false;

		BookMark bookMark=null;

		if(isExistId){

			bookMark=new BookMark();
			bookMark.setBook(new Book());
			bookMark.setUser(new User());
			bookMark.getBook().setId(book_id);
			bookMark.getUser().setId(user_id);
			bookMark.setContent(content);
			bookMark.setPage(page);


		}else{

			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}

		//인용구 등록
		boolean result=bookMarkService.insertBookMark(bookMark);

		if(result){
			sra.setAttribute("message","인용구 등록성공",RequestAttributes.SCOPE_SESSION);
		}else{
			sra.setAttribute("message","인용구 등록실패",RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);

	}


	/**
	 * 별점 삭제
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/deleteBookMark.do")
	public ModelAndView handleDeleteBookMark(HttpServletRequest req,HttpServletResponse res){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//파라미터 정보 변수에 세팅
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);

		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isExistId=(user_id!=null) ? true : false;

		BookMark bookMark=null;

		if(isExistId){

			bookMark=new BookMark();
			bookMark.setId(id);
			bookMark.setBook(new Book());
			bookMark.setUser(new User());
			bookMark.getBook().setId(book_id);
			bookMark.getUser().setId(user_id);

		}else{

			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");
		}


		//인용구 삭제
		boolean result=bookMarkService.deleteBookMark(bookMark);

		if(result){
			sra.setAttribute("message","인용구 삭제성공",RequestAttributes.SCOPE_SESSION);
		}else{
			sra.setAttribute("message","인용구 삭제실패",RequestAttributes.SCOPE_SESSION);
		}
		
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);

	}
	
	
	
	/**
	 * 인용구 수정
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookMark.do")
	public ModelAndView handleModifyBookMark(HttpServletRequest req,HttpServletResponse res){
		
		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		int page=ServletRequestUtils.getIntParameter(req, "page", 0);
		String content=ServletRequestUtils.getStringParameter(req, "content", "");

		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

		boolean isExistId=(user_id!=null) ? true : false;

		BookMark bookMark=null;

		if(isExistId){

			bookMark=new BookMark();
			bookMark.setBook(new Book());
			bookMark.setUser(new User());
			bookMark.getBook().setId(book_id);
			bookMark.getUser().setId(user_id);
			bookMark.setContent(content);
			bookMark.setPage(page);

		}else{

			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}
		
		//인용구 수정
		boolean result=bookMarkService.modifyBookMark(bookMark);
		
		if(result){
			sra.setAttribute("message","인용구 수정성공",RequestAttributes.SCOPE_SESSION);
		}else{
			sra.setAttribute("message","인용구 수정실패",RequestAttributes.SCOPE_SESSION);
		}
		
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		
	}
	
	
	/**
	 * 공감수 올리기
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyVibe.do")
	public ModelAndView handleModifyVibeBookMark(HttpServletRequest req,HttpServletResponse res){
		
		ServletRequestAttributes sra=new ServletRequestAttributes(req);
		
		//파라미터 정보 변수에 세팅
		int bookmark_id=ServletRequestUtils.getIntParameter(req, "id", 0);
		int book_id=ServletRequestUtils.getIntParameter(req, "book_id", 0);
		
		Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);
		
		boolean isExistId=(user_id!=null) ? true : false;
		
		BookMark bookMark=null;
		
		if(isExistId){
			
			bookMark=new BookMark();
			bookMark.setUser(new User());
			bookMark.setBook(new Book());
			bookMark.getUser().setId(user_id);
			bookMark.getBook().setId(book_id);
			bookMark.setId(bookmark_id);
			
		}else{
			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);
			return new ModelAndView("redirect:/user/login.do");
		}
		
		//인용구 수정
		String message=bookMarkService.modifyVibe(bookMark);
		
		sra.setAttribute("message",message,RequestAttributes.SCOPE_SESSION);
		
		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);
		
	}
	

}
