package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

public interface BookDao {

	/**
	 * 책 등록
	 * @param book 책 도메인
	 * @return
	 */
	public int insertBook(Book book);
	
	/**
	 * 책 수정
	 * @param book 수정할 책 도메인
	 * @return
	 */
	public int modifyBook(Book book);
	
	/**
	 * 지은이 수정
	 * @param author 수정할 지은이 도메인
	 * @return
	 */
	public int modifyAuthor(Author author);
	
	
	/**
	 * 책 삭제
	 * @param id	책 ID값
	 * @return
	 */
	public int deleteBook(int id);
	
	/**
	 * 책 관련 지은이 삭제
	 * @param book_ref	책 ID값
	 * @return
	 */
	public int deleteAuthorBook(int book_ref);
	
	/**
	 * 지은이 삭제
	 * @param id	지은이 ID값
	 * @return
	 */
	public int deleteAuthor(int id);
	
	/**
	 * 책조회
	 * @param id	책 ID값
	 * @return
	 */
	public Book getBook(int id);
	
	
	/**
	 * 책조회
	 * @param id	책 ISBN값
	 * @return
	 */
	public Book getBook(String isbn);
	
	/**
	 * 책관련 지은이 목록	
	 * @param book	책 도메인
	 * @return
	 */
	public List<Author> getAuthor(Book book);
	
	/**
	 * 책관련 지은이 목록	
	 * @param book	책 도메인
	 * @return
	 */
	public Author getAuthor(int id);
	
	/**
	 * 책 목록
	 * @param startRow	보여줄 시작 줄번호
	 * @param pageSize	시작줄번호로 부터 몇개
	 * @return
	 */
	public List<Book> getListBook(int startRow, int pageSize);	
	
	/**
	 * 책 전체 갯수
	 * @return
	 */
	public int getDbcount();	
	
	/**
	 * 지은이 등록
	 * @param authors	지은이 도메인
	 * @return
	 */
	public int insertAuthor(Author[] authors,int id);
	
	
	/**
	 * AutoIncrement 값 가지고 오기
	 * @return
	 */
	public int getLastNumIncrement();	
}
