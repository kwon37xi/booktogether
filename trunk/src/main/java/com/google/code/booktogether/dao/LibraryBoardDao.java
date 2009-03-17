package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.LibraryBoard;

public interface LibraryBoardDao {

	/**
	 * 서재내 방명록 등록
	 * @param libraryBoard
	 * @return
	 */
	public int insertLibraryBook(LibraryBoard libraryBoard);
	
	/**
	 * 서재내 방명록 삭제
	 * @param libraryBoard
	 * @return
	 */
	public int deleteLibraryBook(LibraryBoard libraryBoard);
	
	/**
	 * 서재내 방명록 목록
	 * @param libraryIdNum
	 * @return
	 */
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum);
	
	
	public int getDbCountLibraryBook(Integer libraryIdNum);
	
	
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum,Integer startRow, Integer endRow);
	
}
