package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;

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
	
	
	/**
	 * 내서재에 있는 책 목록
	 * @param libraryBook
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook, Integer startPage, Integer endPage);	
	
	/**
	 * 내서재에 해당 이미 있는지 검사
	 * @param libraryIdNum
	 * @param bookIdNum
	 * @return
	 */
	public int duplicateLibraryBook(Integer libraryIdNum, Integer bookIdNum);
	
	
	/**
	 * 내서재에 있는 책 수정하기
	 * @param libraryBook
	 * @return
	 */
	public int modifyLibraryBook(LibraryBook libraryBook);
	
	
	/**
	 * 내서재 책 정보 조회
	 * @param librarBookIdNum
	 * @return
	 */
	public LibraryBook getLibraryBook(Integer librarBookIdNum);
	
	
	/**
	 * 내서재에 있는 책 삭제
	 * @param libraryBookIdNum
	 * @return
	 */
	public int deleteLibraryBook(Integer libraryBookIdNum);
	
	
	/**
	 * 내가 소유한 책 정보 등록
	 * @param possessBook
	 * @return
	 */
	public int insertPossessBook(PossessBook possessBook);
	
	/**
	 * 내가 소유한 책 목록
	 * @param userId
	 * @param startPage
	 * @param endpage
	 * @return
	 */
	public List<PossessBook> getListPossessBook(String userId, Integer startPage, Integer endPage);
	
	
	/**
	 * 내가 소유한 책 수정
	 * @param possessBook
	 * @return
	 */
	public int modifyPossessBook(PossessBook possessBook);
	
	
	/**
	 * 내가 소유한 책 조회
	 * @param possessBookIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer possessBookIdNum);
	
	
	/**
	 * 내가 소유한 책 삭제
	 * @param possessBookIdNum
	 * @return
	 */
	public int deletePossessBook(Integer possessBookIdNum);
	
	/**
	 * 서재에 책 소유 정보 수정
	 * @param userIdNum
	 * @param possessBookIdNum
	 * @return
	 */
	public int modifyLibraryBookIsPossess(Integer userIdNum, Integer possessBookIdNum);
}
