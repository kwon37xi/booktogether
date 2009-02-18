package com.google.code.booktogether.service;

import com.google.code.booktogether.web.domain.Library;

public interface LibraryService {

	/**
	 * 내서재 수정
	 * @param library
	 * @return
	 */
	public boolean modifyLibrary(Library library);
	
	
	/**
	 * 서재 조회하기
	 * @param userId
	 * @return
	 */
	public Library getLibrary(String userId);
	
	
	/**
	 * 서재 등록하기
	 * @param library
	 * @return
	 */
	public boolean insertLibrary(Library library);
	
}
