package com.google.code.booktogether.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.util.ParamUtil;

/**
 * Book에 관련된 Controller
 * 등록화면, 등록, 책목록, 책정보, 수정, 수정화면, 삭제
 * @author ParkHaeCheol
 *
 */
@Controller
public class BookController {

	/**
	 * BookService
	 */
	@Resource(name="bookService")
	BookService bookService;

	/**
	 * Book 도메인 DI
	 */
	@Resource(name="bookDomain")
	Book book;


	/**
	 * 책 등록 화면
	 * @return
	 */
	@RequestMapping("/book/insertBookView.do")
	public ModelAndView handleInsertBookView(){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/insertBook");

		return mav;
	}

	/**
	 * 책 등록
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/book/insertBook.do")
	public ModelAndView handleInsertBook(HttpServletRequest req,HttpServletResponse res){

		//파라미터 정보 변수에 세팅
		String name=ParamUtil.validStringParam(req, "name", "no_title");
		String size=ParamUtil.validStringParam(req, "size", "mm");
		String publish_date=ParamUtil.validStringParam(req, "publish_date", "mm");
		String publish_comp=ParamUtil.validStringParam(req, "publish_comp", "해철");
		int page=ParamUtil.validIntegerParam(req, "page", 0);
		int price=ParamUtil.validIntegerParam(req, "price", 0);
		String corver=ParamUtil.validStringParam(req, "corver", "");
		String isbn10=ParamUtil.validStringParam(req, "isbn10", "1234567890");
		String isbn13=ParamUtil.validStringParam(req, "isbn13", "1234567890123");
		String category=ParamUtil.validStringParam(req, "category", "국내");
		String content=ParamUtil.validStringParam(req, "content", "설명");
		String[] author_name=req.getParameterValues("author_name");
		String[] author_div=req.getParameterValues("author_div");

		//책 정보 세팅
		book.setName(name);
		book.setPage(page);
		book.setSize(size);
		book.setPublish_date(publish_date);
		book.setPublish_comp(publish_comp);
		book.setPrice(price);
		book.setCorver(corver);
		book.setISBN10(isbn10);
		book.setISBN13(isbn13);
		book.setCategory(category);
		book.setContent(new StringBuilder(content));

		//지은이 정보 세팅,
		//여러명일경우를 생각하여 배열로 작성
		Author[] authors=new Author[author_name.length];

		for(int i=0;i<authors.length;i++){
			Author author=new Author();
			author.setName(author_name[i]);
			author.setAuthor_div(Integer.parseInt(author_div[i]));
			authors[i]=author;
		}

		book.setAuthors(authors);

		//책 등록
		//지은이 등록 메서드안에 포함
		boolean result=bookService.insertBook(book);


		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/tempBook");
		mav.addObject("result",result);

		return mav;

	}


	/**
	 * 책목록
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/book/listBook.do")
	public ModelAndView handleListBook(HttpServletRequest req,HttpServletResponse res){

		//현재 페이지 
		int pageNo=ParamUtil.validIntegerParam(req, "pageNo", 0);

		//페이지 클래스에 세팅
		PageBean pageBean=new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPagesize(10);

		//책 목록 가지고 오기
		List<Book> booklist=bookService.getListBook(pageBean);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/listBook");
		mav.addObject("booklist",booklist);
		mav.addObject("pageBean",pageBean);

		return mav;

	}

	/**
	 * 책 정보 가지고 오기
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/book/getBook.do")
	public ModelAndView handleGetBook(HttpServletRequest req,HttpServletResponse res){

		//책 ID값
		int id=ParamUtil.validIntegerParam(req, "id", 0);

		//책 정보 가지고 오기
		Book book=bookService.getBook(id);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/getBook");
		mav.addObject("book_info",book);

		return mav;

	}

	/**
	 * 책 수정화면 보기
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/book/modifyBookView.do")
	public ModelAndView handleModifyViewBook(HttpServletRequest req,HttpServletResponse res){

		//책 ID값
		int id=ParamUtil.validIntegerParam(req, "id", 0);

		//책 정보 가지고 오기
		Book book=bookService.getBook(id);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/modifyBook");
		mav.addObject("book_info",book);

		return mav;

	}

	/**
	 * 책 수정하기
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/book/modifyBook.do")
	public ModelAndView handleModifyBook(HttpServletRequest req,HttpServletResponse res){

		//파라미터 정보 변수  세팅
		int id=ParamUtil.validIntegerParam(req, "id", 0);
		String name=ParamUtil.validStringParam(req, "name", "no_title");
		String size=ParamUtil.validStringParam(req, "size", "mm");
		String publish_date=ParamUtil.validStringParam(req, "publish_date", "mm");
		String publish_comp=ParamUtil.validStringParam(req, "publish_comp", "해철1234");
		int page=ParamUtil.validIntegerParam(req, "page", 0);
		int price=ParamUtil.validIntegerParam(req, "price", 0);
		String corver=ParamUtil.validStringParam(req, "corver", "");
		String isbn10=ParamUtil.validStringParam(req, "isbn10", "1234567890");
		String isbn13=ParamUtil.validStringParam(req, "isbn13", "1234567890123");
		String category=ParamUtil.validStringParam(req, "category", "국내");
		String content=ParamUtil.validStringParam(req, "content", "설명");
		String[] author_id=req.getParameterValues("author_id");
		String[] author_name=req.getParameterValues("author_name");
		String[] author_div=req.getParameterValues("author_div");


		book.setId(id);
		book.setName(name);
		book.setPage(page);
		book.setSize(size);
		book.setPublish_date(publish_date);
		book.setPublish_comp(publish_comp);
		book.setPrice(price);
		book.setCorver(corver);
		book.setISBN10(isbn10);
		book.setISBN13(isbn13);
		book.setCategory(category);
		book.setContent(new StringBuilder(content));

		//지은이 정보 세팅,
		//여러명일경우를 생각하여 배열로 작성
		Author[] authors=new Author[author_name.length];

		for(int i=0;i<authors.length;i++){
			Author author=new Author();
			author.setId(Integer.parseInt(author_id[i]));
			author.setName(author_name[i]);
			author.setAuthor_div(Integer.parseInt(author_div[i]));
			authors[i]=author;
		}

		book.setAuthors(authors);

		//책 수정
		boolean result=bookService.modifyBook(book);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/getBook");
		mav.addObject("book_info",book);
		mav.addObject("result",result);

		return mav;

	}
	
	
	/**
	 * 책 삭제
	 * @param req
	 * @param res
	 */
	@RequestMapping("/book/deleteBook.do")
	public void handleDeleteBook(HttpServletRequest req,HttpServletResponse res){

		//책 ID값
		int id=ParamUtil.validIntegerParam(req, "id", 0);

		//책 정보 가지고 오기
		boolean result=bookService.deleteBook(id);

		System.out.println(result);
		
		try {
			res.sendRedirect("/book/listBook.do");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * 책 검색후 책자세히 보기할때 책 내용이 DB에 있는지 검사
	 * @param req
	 * @param res
	 */
	@RequestMapping("/book/deleteBook.do")
	public ModelAndView handleValidBook(HttpServletRequest req,HttpServletResponse res){
		
		//책 ID값
		String isbn=ParamUtil.validStringParam(req, "ISBN", "");
		
		//책 정보 가지고 오기
		Book book=bookService.validBook(isbn);
		
		System.out.println(book);
		
		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/getBook");
		mav.addObject("book_info",book);

		return mav;
		
	}
	

}
