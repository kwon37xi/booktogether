package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.util.ParamUtil;


@Controller
public class BookController {

	private Logger log=Logger.getLogger(this.getClass());

	@Resource(name="bookService")
	BookService bookService;

	@Resource(name="bookDomain")
	Book book;
	
	@RequestMapping("/book/insertBookView.do")
	public ModelAndView handleInsertBookView(){

		if(log.isInfoEnabled()){
			log.info("시작");
		}

		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/insertBook");

		if(log.isInfoEnabled()){
			log.info("종료");
		}

		return mav;

	}
	

	@RequestMapping("/book/insertBook.do")
	public ModelAndView handleInsertBook(HttpServletRequest req,HttpServletResponse res){

		if(log.isInfoEnabled()){
			log.info("시작");
		}

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
		String author_name=ParamUtil.validStringParam(req, "author_name", "지은이");
		int author_div=ParamUtil.validIntegerParam(req, "author_div", 0);


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

		Author author=new Author();

		author.setName(author_name);
		author.setAuthor_div(author_div);

		book.setAuthors(new Author[]{author});


		boolean result=bookService.insertBook(book);

		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/tempBook");
		mav.addObject("result",result);

		if(log.isInfoEnabled()){
			log.info("종료");
		}

		return mav;

	}
	
	
	@RequestMapping("/book/listBook.do")
	public ModelAndView handleListBook(HttpServletRequest req,HttpServletResponse res){
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		int pageNo=ParamUtil.validIntegerParam(req, "pageNo", 0);
		
		PageBean pageBean=new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPagesize(10);
		
		List<Book> booklist=bookService.getListBook(pageBean);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("book/listBook");
		mav.addObject("booklist",booklist);
		mav.addObject("pageBean",pageBean);
		
		if(log.isInfoEnabled()){
			log.info("종료");
		}
		
		return mav;
		
	}


	


}
