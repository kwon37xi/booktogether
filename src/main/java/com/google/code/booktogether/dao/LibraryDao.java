package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.domain.User;

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
	 * 
	 * @param bookIdNum
	 * @return
	 */
	public int getDbCountPossessBook(Integer bookIdNum);

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
	 * 서재 검색
	 * 
	 * @param userId
	 * @param name
	 * @param nickname
	 * @return
	 */
	public List<User> getListSearchLibrary(String query);

	/**
	 * 서재내 책 검색(읽고 싶은책, 읽은책, 읽고 있는책)
	 * 
	 * @param libraryIdNum
	 * @param bookName
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<LibraryBook> getListLibraryBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow);

	/**
	 * 서재내 책 검색(소유책)
	 * 
	 * @param libraryIdNum
	 * @param bookName
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<PossessBook> getListPossessBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow);

	/**
	 * 서재내 책 검색(소유책)
	 * 
	 * @param libraryIdNum
	 * @param bookName
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<PossessBook> getListPossessBook(Integer bookIdNum,
			Integer startRow, Integer endRow);

	/**
	 * 서재내 책 검색(읽고 싶은책, 읽은책, 읽고 있는책) - 갯수!
	 * 
	 * @param libraryIdNum
	 * @param bookName
	 * @return
	 */
	public int getDbCountListLibraryBook(Integer libraryIdNum, String bookName);

	/**
	 * 서재내 책 검색(소유책) - 갯수!
	 * 
	 * @param libraryIdNum
	 * @param bookName
	 * @return
	 */
	public int getDbCountListPossessBook(Integer libraryIdNum, String bookName);

	/**
	 * 개인서재 순위 목록
	 * 
	 * @return
	 */
	public List<User> getLibraryRank();

	/**
	 * 관심서재 등록
	 * 
	 * @param target
	 * @param userIdNum
	 * @return
	 */
	public int insertInterestLibrary(Integer target, Integer userIdNum);

	/**
	 * 관심서재 목록
	 * 
	 * @param userIdNum
	 * @return
	 */
	public List<User> getListInterestLibrary(Integer userIdNum);

	/**
	 * 관심서재 삭제
	 * 
	 * @param userIdNum
	 * @return
	 */
	public int deleteInterestLibrary(Integer idNum, Integer userIdNum);

	/**
	 * 관심 서재에 이미 등록이 되어있는지 체크
	 * 
	 * @param target
	 * @param userIdNum
	 * @return
	 */
	public int duplicateInterestLibrary(Integer target, Integer userIdNum);

	/**
	 * 서재 순위 테이블 삭제
	 * 
	 * @return
	 */
	public int deleteLibraryRank();

	/**
	 * 서재 순위 새로고침
	 * 
	 * @return
	 */
	public int refeshLibraryRank();
	
	
	public int getDbcountInterestLibrary(Integer userIdNum);
	
	
	public List<User> getListInterestLibrary(Integer userIdNum,Integer startRow, Integer endRow);

}
