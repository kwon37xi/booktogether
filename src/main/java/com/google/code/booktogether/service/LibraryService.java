package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;

public interface LibraryService {

	/**
	 * 내서재 수정
	 * 
	 * @param library
	 * @return
	 */
	public boolean modifyLibrary(Library library);

	/**
	 * 서재 조회하기
	 * 
	 * @param userId
	 * @return
	 */
	public Library getLibrary(String userId);

	/**
	 * 서재 등록하기
	 * 
	 * @param library
	 * @return
	 */
	public boolean insertLibrary(Library library);

	/**
	 * 소유한 책 등록
	 * 
	 * @param possessBook
	 * @return
	 */
	public boolean insertPossessBook(PossessBook possessBook);

	/**
	 * 소유한 책 등록
	 * 
	 * @param possessBook
	 * @return
	 */
	public boolean deletePossessBook(PossessBook possessBook);

	/**
	 * 소유하고 있는 책 목록
	 * 
	 * @param userIdNum
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<PossessBook> getListPossessBook(String userId,
			Integer startPage, Integer endPage);

	/**
	 * 소유 책 정보 조회
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer possessBookIdNum);

	/**
	 * 서재안에 책 등록
	 * 
	 * @param library
	 * @return
	 */
	public boolean insertLibraryBook(LibraryBook libraryBook);

	/**
	 * 서재안의 책 목록 
	 * @param libraryBook
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startPage, Integer endPage);
	
	/**
	 * 서재안의 책 수정
	 * @param libraryBook
	 * @return
	 */
	public boolean modifyLibraryBook(LibraryBook libraryBook);
	
	/**
	 * 서재안의 책 삭제
	 * @param libraryBookIdNum
	 * @return
	 */
	public boolean deleteLibraryBook(Integer libraryBookIdNum);
	
	/**
	 * 서재안의 책 조회
	 * @param libraryBookIdNum
	 * @return
	 */
	public LibraryBook getLibraryBook(Integer libraryBookIdNum);
	
	
	/**
	 * 내서재에 등록되어있는 책인지 검사
	 * @param libraryIdNum
	 * @param boolIdNum
	 * @return
	 */
	public boolean duplicateLibraryBook(Integer libraryIdNum, Integer boolIdNum);
}
