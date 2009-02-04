package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.PageBean;

public interface BookService {

	/**
	 * 책 등록
	 * @param book
	 * @return
	 */
	public boolean insertBook(Book book);

	/**
	 * 책 수정
	 * @param book
	 * @return
	 */
	public boolean modifyBook(Book book);
	
	/**
	 * 책 삭제
	 * @param id
	 * @return
	 */
	public boolean deleteBook(int id);	
	
	
	/**
	 * 책 조회
	 * @param id
	 * @return
	 */
	public Book getBook(int id);		
	
	
	/**
	 * 책 목록
	 * @param pageBean
	 * @return
	 */
	public List<Book> getListBook(PageBean pageBean);
	
}
