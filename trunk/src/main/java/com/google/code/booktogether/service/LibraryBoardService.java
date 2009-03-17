package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.LibraryBoard;
import com.google.code.booktogether.web.page.PageBean;

public interface LibraryBoardService {

	/**
	 * 서재내 방명록 등록
	 * 
	 * @param libraryBoard
	 * @return
	 */
	public boolean insertLibraryBoard(LibraryBoard libraryBoard);

	/**
	 * 서재내 방명록 삭제
	 * 
	 * @param libraryBoard
	 * @return
	 */
	public boolean deleteLibraryBoard(LibraryBoard libraryBoard);

	/**
	 * 서재내 방명록 목록
	 * 
	 * @param libraryIdNum
	 * @return
	 */
	public List<LibraryBoard> getListLibraryBoard(Integer libraryIdNum);
	
	
	public List<LibraryBoard> getListLibraryBoard(Integer libraryIdNum,PageBean pageBean);

}
