package com.google.code.booktogether.dao;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;

public interface LibraryDao {

	
	/**
	 * 서재 정보 조회
	 * @param userId
	 * @return
	 */
	public Library getLibrary(String userId);
	
	
	/**
	 * 개인서재 정보 수정
	 * @param library
	 * @return
	 */
	public int modifyLibrary(Library library);
	
	
	/**
	 * 서재 등록하기
	 * @param library
	 * @return
	 */
	public int insertLibrary(Library library);
	
	
	/**
	 * 서재에 책 등록하기
	 * @param libraryBook
	 * @return
	 */
	public int insertLibraryBook(LibraryBook libraryBook);
	
}
