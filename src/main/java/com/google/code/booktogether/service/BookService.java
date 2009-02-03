package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;

public interface BookService {

	public boolean insertBook(Book book);
	
	public boolean modifyBook(Book book);
	
	public boolean deleteBook(Book book);
	
	public Book getBook(Book book);
	
	public List<Book> getListBook(PageBean pageBean);
	
}
