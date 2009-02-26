package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.LibraryBoard;

public interface LibraryBoardService {

	/**
	 * 서재내 방명록 등록
	 * 
	 * @param libraryBoard
	 * @return
	 */
	public boolean insertLibraryBook(LibraryBoard libraryBoard);

	/**
	 * 서재내 방명록 삭제
	 * 
	 * @param libraryBoard
	 * @return
	 */
	public boolean deleteLibraryBook(LibraryBoard libraryBoard);

	/**
	 * 서재내 방명록 목록
	 * 
	 * @param libraryIdNum
	 * @return
	 */
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum);

}
