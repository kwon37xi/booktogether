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

			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");

		}


		//별점 등록
		boolean result=bookGradeService.insertGrade(bookGrade);

		if(result){
			sra.setAttribute("message","별점 등록성공",RequestAttributes.SCOPE_SESSION);
		}else{
			sra.setAttribute("message","별점 등록실패",RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);

	}


	/**
	 * 별점 삭제
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

			sra.setAttribute("message","로그아웃되었습니다.",RequestAttributes.SCOPE_SESSION);

			return new ModelAndView("redirect:/user/login.do");
		}


		//별점 등록
		boolean result=bookGradeService.deleteGrade(bookGrade);

		if(result){
			sra.setAttribute("message","별점 삭제성공",RequestAttributes.SCOPE_SESSION);
		}else{
			sra.setAttribute("message","별점 삭제실패",RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?id="+book_id);

	}

}
