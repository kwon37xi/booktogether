package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

public interface BookJdbcDao {

	
	public int insertBook(Book book);	//책 등록

	public int modifyBook(Book book);	//책 수정
	
	public int modifyAuthor(Author author);	//지은이 수정

	public int deleteBook(int id);	//책 삭제
	
	public int deleteAuthorBook(int book_ref);	//책 관련 지은이 삭제
	
	public int deleteAuthor(int id);	//책 관련 지은이 삭제

	public Book getBook(int id);	//책조회
	
	public List<Author> getAuthor(Book book);//책관련 지은이 목록	

	public List<Book> getListBook(int startRow, int pageSize);	//책 목록
	
	public int getDbcount();	//책 전체 갯수
	
	public int insertAuthor(Author[] authors);	//지은이 등록
}
