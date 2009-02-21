package com.google.code.booktogether.web.openapi;


import java.util.List;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.openapi.header.BookOpenApiHeader;


/**
 * @author ParkHaeCheol
 *
 */
public interface BookOpenApi {

	/**
	 * 책조회 
	 */
	public Book viewBook(String isbn);
	
	
	/**
	 * 책 검색
	 * @param query
	 * @param searchType
	 * @param pageno
	 * @return
	 */
	public List<Book> searchBook(String query, String searchType,int pageno);
	
	
	/**
	 * 파싱한 해더 가지고 오기
	 * @return
	 */
	public BookOpenApiHeader getHeader();
	

}