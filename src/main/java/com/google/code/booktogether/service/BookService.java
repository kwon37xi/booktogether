package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;

public interface BookService {


	public boolean insertBook(Book book);		//책 등록

	public boolean modifyBook(Book book);		//책 수정
	
	public boolean deleteBook(int id);			//책 삭제
	
	public Book getBook(int id);				//책 조회
	
	public List<Book> getListBook(PageBean pageBean);	//책 목록
	
}
