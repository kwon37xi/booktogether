package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.page.PageBean;

public interface BookService {

	/**
	 * 책 등록
	 * @param book
	 * @return
	 */
	public boolean insertBook(Book book);

	
	/**
	 * 책 조회
	 * @param id
	 * @return
	 */
	public Book getBook(Integer bookIdNum);		
	
	
	/**
	 * 해당 책이 DB에 있는지 체크
	 * @param isbn
	 * @return
	 */
	public Book checkBook(String isbn);
	
	
	/**
	 * 책 검색
	 * @param query : 검색 단어
	 * @param searchType : 검색 방법
	 * @param pageBean : 페이지 정보
	 * @return 책목록
	 */
	public List<Book> searchBook(String query, String searchType, PageBean pageBean);
	
	
	/**
	 * 내가 작성한 인용구의 책 정보
	 * @param userIdNum
	 * @param pageBean
	 * @return
	 */
	public List<Book> getListBookRefBookMark(Integer userIdNum, PageBean pageBean);
	
}
