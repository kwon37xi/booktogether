package com.google.code.booktogether.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.PageBean;

/**
 * Book에 관련된 Controller
 * @author ParkHaeCheol
 * @modifier 09.02.13 ParkYoonYoung - getter/setter 자동 기능을 이용하기 위해 소스 수정
 */
@Controller
public class BookController {

	/**
	 * BookService
	 */
	@Resource(name="bookService")
	BookService bookService;


	/**
	 * BookGradeService
	 */
	@Resource(name="bookGradeService")
	BookGradeService bookGradeService;


	/**
	 * BookReviewService
	 */
	@Resource(name="bookReviewService")
	BookReviewService bookReviewService;


	/**
	 * BookMarkService
	 */
	@Resource(name="bookMarkService")
	BookMarkService bookMarkService;


	/**
	 * 책 등록 화면(지금 안씀)
	 * @return
	 */
	@RequestMapping("/book/insertBookView.do")
	public ModelAndView handleInsertBookView(){

		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/insertBook");

		return mav;
	}

	/**
	 * 책 등록(지금 안씀)
	 * @param req
	 * @return
	 */
	/*
	@RequestMapping("/book/insertBook.do")
	public ModelAndView handleInsertBook(HttpServletRequest req){

		//파라미터 정보 변수에 세팅
		String name=ServletRequestUtils.getStringParameter(req, "name", "no_title");
		String publish_date=ServletRequestUtils.getStringParameter(req, "publish_date", "mm");
		String publish_comp=ServletRequestUtils.getStringParameter(req, "publish_comp", "해철");
		int price=ServletRequestUtils.getIntParameter(req, "price", 0);
		String corver=ServletRequestUtils.getStringParameter(req, "corver", "");
		String isbn10=ServletRequestUtils.getStringParameter(req, "isbn10", "1234567890");
		String isbn13=ServletRequestUtils.getStringParameter(req, "isbn13", "1234567890123");
		String category=ServletRequestUtils.getStringParameter(req, "category", "국내");
		String description=ServletRequestUtils.getStringParameter(req, "description", "설명");
		String[] author_name=ServletRequestUtils.getStringParameters(req,"author_name");
		String[] author_div=ServletRequestUtils.getStringParameters(req,"author_div");

		Book book=new Book();

		//책 정보 세팅
		book.setName(name);
		book.setPublish_date(publish_date);
		book.setPublish_comp(publish_comp);
		book.setPrice(price);
		book.setCorver(corver);
		book.setISBN10(isbn10);
		book.setISBN13(isbn13);
		book.setCategory(category);
		book.setDescription(description);

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
		//boolean result=bookService.insertBook(book);
		boolean result=true;

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/tempBook");
		mav.addObject("result",result);
		mav.addObject("book",book);
		return mav;

	}
	*/
	
	
	// 스프링의 setter/getter 자동 기능을 위해 소스 수정
	@RequestMapping("/book/insertBook.do")
	public ModelAndView handleInsertBook(HttpServletRequest req, Book book){
		
		// 자동 setter/getter 테스트
		System.out.println("name:::"+book.getName());
		System.out.println("isbn:::"+book.getISBN10());
		System.out.println("author_names:::"+ req.getParameterValues("author_name"));

		String[] author_name = book.getAuthor_name();
		String[] author_div = book.getAuthor_div();
		
		//지은이 정보 세팅,
		//여러명일 경우를 생각하여 배열로 작성
		if( author_name != null ){
			Author[] authors=new Author[author_name.length];
	
			for(int i=0;i<authors.length;i++){
				Author author=new Author();
				author.setName(author_name[i]);
				if( author_div != null ){
					author.setAuthor_div(Integer.parseInt(author_div[i]));
				}
				authors[i]=author;
			}
			book.setAuthors(authors);
		}
		
		boolean result=bookService.insertBook(book);
		
		//경로 설정 및 Attribute 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/tempBook");
		mav.addObject("result", result);
		mav.addObject("book", book);
		return mav;
	}
	
	
	/**
	 * 책목록(지금 안씀)
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/listBook.do")
	public ModelAndView handleListBook(HttpServletRequest req){

		//현재 페이지 
		int pageNo=ServletRequestUtils.getIntParameter(req, "pageNo", 0);

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
	 * @return
	 */
	@RequestMapping("/book/getBook.do")
	public ModelAndView handleGetBook(HttpServletRequest req){

		ServletRequestAttributes sra=new ServletRequestAttributes(req);

		//책 ID값
		String id=ServletRequestUtils.getStringParameter(req, "id", "0");

		//책 정보 가지고 오기
		Book book=bookService.getBook(id);

		if(book==null){
			
			//return new ModelAndView("redirect:/book/getBook.do?id="+book.getId());
			return null;
			
		}else{
			
			List<BookGrade> bookgradelist=bookGradeService.getListBookGrade(book.getId(), 0, 5);
			List<BookReview> bookreviewlist=bookReviewService.getListBookReview(book.getId(), 0, 5);
			List<BookMark> bookmarklist=bookMarkService.getListBookMark(book.getId(), 0, 5);


			//자기가 입력한 별점이 있는지 체크
			boolean existGrade=false;

			//자기가 입력한 리뷰가 있는지 체크
			boolean existReview=false;

			Integer user_id=(Integer)sra.getAttribute("id", RequestAttributes.SCOPE_SESSION);

			boolean isExistId=(user_id!=null) ? true : false;

			if(isExistId){
				existGrade=bookGradeService.isExistGrade(book.getId(),user_id);
				existReview=bookReviewService.isExistReview(book.getId(),user_id);
			}

			//경로 설정 및 Attribute 설정
			ModelAndView mav=new ModelAndView();
			mav.setViewName("book/getBook");
			mav.addObject("book_info",book);
			mav.addObject("bookgradelist",bookgradelist);
			mav.addObject("bookreviewlist",bookreviewlist);
			mav.addObject("bookmarklist",bookmarklist);
			mav.addObject("existGrade",existGrade);
			mav.addObject("existReview",existReview);

			return mav;
		}
	}

	/**
	 * 책 수정화면 보기
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBookView.do")
	public ModelAndView handleModifyViewBook(HttpServletRequest req){

		//책 ID값
		String id=ServletRequestUtils.getStringParameter(req, "id", "0");

		//책 정보 가지고 오기
		Book book=bookService.getBook(id);

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/modifyBook");
		mav.addObject("book_info",book);

		return mav;

	}

	/**
	 * 책 수정하기(지금 안씀)
	 * @param req
	 * @return
	 */
	@RequestMapping("/book/modifyBook.do")
	public ModelAndView handleModifyBook(HttpServletRequest req){


		//파라미터 정보 변수  세팅
		String id=ServletRequestUtils.getStringParameter(req, "id", "0");
		String name=ServletRequestUtils.getStringParameter(req, "name", "no_title");
		String publish_date=ServletRequestUtils.getStringParameter(req, "publish_date", "mm");
		String publish_comp=ServletRequestUtils.getStringParameter(req, "publish_comp", "해철1234");
		String price=ServletRequestUtils.getStringParameter(req, "price", "0");
		String corver=ServletRequestUtils.getStringParameter(req, "corver", "");
		String isbn10=ServletRequestUtils.getStringParameter(req, "isbn10", "1234567890");
		String isbn13=ServletRequestUtils.getStringParameter(req, "isbn13", "1234567890123");
		String category=ServletRequestUtils.getStringParameter(req, "category", "국내");
		String description=ServletRequestUtils.getStringParameter(req, "content", "설명");
		String[] author_id=req.getParameterValues("author_id");
		String[] author_name=req.getParameterValues("author_name");
		String[] author_div=req.getParameterValues("author_div");


		Book book=new Book();
		book.setId(id);
		book.setName(name);
		book.setPublish_date(publish_date);
		book.setPublish_comp(publish_comp);
		book.setPrice(price);
		book.setCorver(corver);
		book.setISBN10(isbn10);
		book.setISBN13(isbn13);
		book.setCategory(category);
		book.setDescription(description);

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
	 * 책 삭제(지금 안씀)
	 * @param req
	 * @param res
	 */
	@RequestMapping("/book/deleteBook.do")
	public void handleDeleteBook(HttpServletRequest req,HttpServletResponse res){

		//책 ID값
		int id=ServletRequestUtils.getIntParameter(req, "id", 0);

		//책 삭제
		boolean result=bookService.deleteBook(id);

		System.out.println(result);

		try {
			res.sendRedirect("/book/listBook.do");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	/**
	 * 책 검색
	 * @param req
	 */
	@RequestMapping("/book/searchBook.do")
	public ModelAndView handleSearchBook(HttpServletRequest req){

		//책 ID값
		String query=ServletRequestUtils.getStringParameter(req, "query", "스프링");
		String searchType=ServletRequestUtils.getStringParameter(req, "searchType", "all");
		int pageno=ServletRequestUtils.getIntParameter(req, "pageno", 1);

		//책검색 목록 가지고 오기
		List<Book> booklist=null;
		PageBean pageBean=new PageBean();
		pageBean.setPageNo(pageno);
		pageBean.setLimit(5);
		pageBean.setPagesize(20);

		if(!query.equals("")){
			booklist=bookService.searchBook(query,searchType,pageBean);
		}

		//경로 설정 및 Attribute 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/searchBook");
		mav.addObject("book_list",booklist);
		mav.addObject("pageBean",pageBean);

		return mav;

	}

	/**
	 * 책 검색후 책자세히 보기할때 책 내용이 DB에 있는지 검사
	 * @param req
	 */
	@RequestMapping("/book/checkBook.do")
	public ModelAndView handleCheckBook(HttpServletRequest req){

		//책 ID값
		String isbn=ServletRequestUtils.getStringParameter(req, "ISBN", "");

		//책 정보 가지고 오기
		Book book=bookService.checkBook(isbn);

		return new ModelAndView("redirect:/book/getBook.do?id="+book.getId());

	}


}
