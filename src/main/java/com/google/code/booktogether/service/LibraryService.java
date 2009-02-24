package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.page.PageBean;

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
	 * 소유한 책 / 삭제 소유책 삭제되었을때 서재에 있는 책 정보 수정하기 /소유책이 사려졌으니 소유 정보를 바꿔야 함
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public boolean deletePossessBook(Integer userIdNum, Integer possessBookIdNum);

	/**
	 * 소유하고 있는 책 목록
	 * 
	 * @param userIdNum
	 * @param pageBean
	 * @return
	 */
	public List<PossessBook> getListPossessBook(String userId, PageBean pageBean);

	/**
	 * 소유 책 정보 조회
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer possessBookIdNum);
	
	
	/**
	 * 소유 책 정보 조회
	 * 
	 * @param bookIdNum
	 * @param userIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer bookIdNum, Integer userIdNum);

	/**
	 * 내소유책 수정
	 * 
	 * @param possessBook
	 * @return
	 */
	public boolean modifyPossessBook(PossessBook possessBook);

	/**
	 * 서재안에 책 등록
	 * 
	 * @param library
	 * @return
	 */
	public boolean insertLibraryBook(LibraryBook libraryBook);

	/**
	 * 서재안의 책 목록
	 * 
	 * @param libraryBook
	 * @param pageBean
	 * @return
	 */
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			PageBean pageBean);

	/**
	 * 서재안의 책 수정
	 * 
	 * @param libraryBook
	 * @return
	 */
	public boolean modifyLibraryBook(LibraryBook libraryBook);
	
	/**
	 * 서재안의 책 수정
	 * 
	 * @param libraryBook
	 * @param possessBook
	 * @param serviceDiv
	 * @return
	 */
	public boolean modifyLibraryBook(LibraryBook libraryBook, PossessBook possessBook, Integer serviceDiv);

	/**
	 * 서재안의 책 삭제
	 * 
	 * @param libraryBookIdNum
	 * @return
	 */
	public boolean deleteLibraryBook(Integer libraryBookIdNum);

	/**
	 * 서재안의 책 조회
	 * 
	 * @param libraryBookIdNum
	 * @return
	 */
	public LibraryBook getLibraryBook(Integer libraryBookIdNum);

	/**
	 * 내서재에 등록되어있는 책인지 검사
	 * 
	 * @param libraryIdNum
	 * @param boolIdNum
	 * @return
	 */
	public boolean duplicateLibraryBook(Integer libraryIdNum, Integer boolIdNum);
	
	
	/**
	 * 내 생활반경의 책 목록 가지고 오기
	 * @param userId
	 * @param pageBean
	 * @return
	 */
	public List<PossessBook> getListZoneBook(String userId, PageBean pageBean);

}
