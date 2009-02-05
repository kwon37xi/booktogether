package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Author;
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
	
	
	/**
	 * 해당 책이 DB에 있는지 체크
	 * @param isbn
	 * @return
	 */
	public Book checkBook(String isbn);
	
	
	/**
	 * 지은이 목록 List<Author> -> Author[]로 변환
	 * @param authorlist
	 * @return
	 */
	public Author[] listToArray(List<Author> authorlist);
	
}