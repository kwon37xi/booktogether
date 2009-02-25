package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;

public interface LibraryDao {

	/**
	 * 서재 정보 조회
	 * 
	 * @param userId
	 * @return
	 */
	public Library getLibrary(String userId);
	
	
	/**
	 * 서재 정보 조회
	 * 
	 * @param libraryIdNum
	 * @return
	 */
	public Library getLibrary(Integer libraryIdNum);

	/**
	 * 개인서재 정보 수정
	 * 
	 * @param library
	 * @return
	 */
	public int modifyLibrary(Library library);

	/**
	 * 서재 등록하기
	 * 
	 * @param library
	 * @return
	 */
	public int insertLibrary(Library library);

	/**
	 * 서재에 책 등록하기
	 * 
	 * @param libraryBook
	 * @return
	 */
	public int insertLibraryBook(LibraryBook libraryBook);

	/**
	 * 내서재에 있는 책 목록
	 * 
	 * @param libraryBook
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startRow, Integer endRow);

	/**
	 * 내서재에 해당 이미 있는지 검사
	 * 
	 * @param libraryIdNum
	 * @param bookIdNum
	 * @return
	 */
	public int duplicateLibraryBook(Integer libraryIdNum, Integer bookIdNum);

	/**
	 * 내서재에 있는 책 수정하기
	 * 
	 * @param libraryBook
	 * @return
	 */
	public int modifyLibraryBook(LibraryBook libraryBook);

	/**
	 * 내서재 책 정보 조회
	 * 
	 * @param librarBookIdNum
	 * @return
	 */
	public LibraryBook getLibraryBook(Integer librarBookIdNum);

	/**
	 * 내서재에 있는 책 삭제
	 * 
	 * @param libraryBookIdNum
	 * @return
	 */
	public int deleteLibraryBook(Integer libraryBookIdNum);

	/**
	 * 내가 소유한 책 정보 등록
	 * 
	 * @param possessBook
	 * @return
	 */
	public int insertPossessBook(PossessBook possessBook);

	/**
	 * 내가 소유한 책 목록
	 * 
	 * @param userId
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<PossessBook> getListPossessBook(String userId,
			Integer startRow, Integer endRow);

	/**
	 * 내가 소유한 책 수정
	 * 
	 * @param possessBook
	 * @return
	 */
	public int modifyPossessBook(PossessBook possessBook);

	/**
	 * 내가 소유한 책 조회
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer possessBookIdNum);

	/**
	 * 내가 소유한 책 조회
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public PossessBook getPossessBook(Integer bookIdNum, Integer userIdNum);

	/**
	 * 내가 소유한 책 삭제
	 * 
	 * @param possessBookIdNum
	 * @return
	 */
	public int deletePossessBook(Integer possessBookIdNum);

	/**
	 * 서재에 책 소유 정보 수정
	 * 
	 * @param userIdNum
	 * @param possessBookIdNum
	 * @return
	 */
	public int modifyLibraryBookIsPossess(Integer userIdNum,
			Integer possessBookIdNum);

	/**
	 * 검색 갯수가지고 오기
	 * 
	 * @param libraryBook
	 * @return
	 */
	public int getDbCountLibraryBook(LibraryBook libraryBook);

	/**
	 * 
	 * @param userIdNum
	 * @return
	 */
	public int getDbCountPossessBook(String userId);

	/**
	 * 내생활반경의 책목록 조회 갯수
	 * 
	 * @param userId
	 * @return
	 */
	public int getDbCountListZoneBook(String userId);

	/**
	 * 내 생활 반경의 책목록
	 * 
	 * @param userId
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<PossessBook> getListZoneBook(String userId, Integer startRow,
			Integer endRow);

	/**
	 * 비공개 여부 확인
	 * 
	 * @param libraryIdNum
	 * @return
	 */
	public int isOpenLibrary(Integer libraryIdNum);

	/**
	 * 비공개 여부 확인
	 * 
	 * @param userId
	 * @return
	 */
	public int isOpenLibrary(String userId);

}
