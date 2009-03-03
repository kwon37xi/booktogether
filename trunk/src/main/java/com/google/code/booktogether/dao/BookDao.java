package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

public interface BookDao {

	/**
	 * 책 등록
	 * 
	 * @param book
	 *            책 도메인
	 * @return
	 */
	public int insertBook(Book book);

	/**
	 * 책조회
	 * 
	 * @param bookIdNum
	 *            책 ID값
	 * @return
	 */
	public Book getBook(Integer bookIdNum);

	/**
	 * 책조회
	 * 
	 * @param isbn
	 *            책 ISBN값
	 * @return
	 */
	public Book getBook(String isbn);

	/**
	 * 책관련 지은이 목록
	 * 
	 * @param book
	 *            책 도메인
	 * @return
	 */
	public List<Author> getAuthor(Book book);

	/**
	 * 책관련 지은이 목록
	 * 
	 * @param bookIdNum
	 * @return
	 */
	public Author getAuthor(Integer bookIdNum);

	/**
	 * 지은이 등록
	 * 
	 * @param author
	 *            지은이 도메인
	 * @param bookIdNum
	 * @return
	 */
	public int insertAuthor(Author author, Integer bookIdNum);

	/**
	 * AutoIncrement 값 가지고 오기
	 * 
	 * @return
	 */
	public int getLastNumIncrement();

	/**
	 * 해당 책에 관련된 인용구 목록 
	 * @param userIdNum
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Book> getListBookRefBookMark(Integer userIdNum,
			Integer startRow, Integer endRow);
	
	/**
	 * 해당 책에 관련된 인용구 목록 갯수
	 * @param userIdNum
	 * @return
	 */
	public int getDbCountBookRefBookMark(Integer userIdNum);
	
	/**
	 * 검색 단어 순위 가지고 오기
	 * @return
	 */
	public List<String> getListSearchRankQuery();
	
	/**
	 * 새로운 검색 단어 등록
	 * @param query
	 * @return
	 */
	public int insertSearchRankQuery(String query);
	
	
	/**
	 * 새로운 검색 단어 검색수 수정
	 * @param query
	 * @return
	 */
	public int modifySearchRankQuery(String query);
	
	/**
	 * 책 조회수 수정
	 * @param bookIdNum
	 * @return
	 */
	public int modifyBookHits(Integer bookIdNum);
	
	/**
	 * 책 조회수 정보 등록
	 * @param bookIdNum
	 * @return
	 */
	public int insertBookHits(Integer bookIdNum);
	
	/**
	 * 책 인기 순위
	 * @return
	 */
	public List<Book> getListTopBookHits();

}
