package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.dao.BookJdbcDao;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource(name="bookJdbcDao")
	private BookJdbcDao bookJdbcDao;
	
	
	private Logger log=Logger.getLogger(this.getClass());
	

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book getBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getListBook(PageBean pageBean) {
		
		int dbcount=bookJdbcDao.getDbcount();
		
		pageBean.setDbcount(dbcount);
		
		List<Book> booklist=bookJdbcDao.getListBook(pageBean.getStartPage(), pageBean.getPagesize());
		
		return booklist;
	}

	@Override
	//책 등록
	public boolean insertBook(Book book) {
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		boolean result=false;
		
		int count=bookJdbcDao.insertBook(book);
		int count1=bookJdbcDao.insertAuthor(book.getAuthors());
		
		result=(count==0 || count1==0) ? false : true;
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}

		return result;
	}

	@Override
	public boolean modifyBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}


}
