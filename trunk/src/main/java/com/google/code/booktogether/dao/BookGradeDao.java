package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.BookGrade;

public interface BookGradeDao {

	/**
	 * 별점 등록
	 * @param bookGrade
	 * @return
	 */
	public int insertGrade(BookGrade bookGrade);
	
	/**
	 * 별점 수정
	 * @param bookGrade
	 * @return
	 */
	public int modifyGrade(BookGrade bookGrade);
	
	/**
	 * 별점 삭제
	 * @param bookGrade
	 * @return
	 */
	public int deleteGrade(BookGrade bookGrade);
	
	/**
	 * 별점 목록(책 조회시)
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookGrade> getListBookGrade(Integer bookIdNum,Integer startPage, Integer endPage);
	
	/**
	 * 내가 매긴 별점 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookGrade> getListMyBookGrade(Integer userIdNum,Integer startPage, Integer endPage);
	
	/**
	 * 내가 매긴 별점이 있는지 체크
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	public int isExistGrade(Integer bookIdNum,Integer userIdNum);
	
	/**
	 * 내가 매긴 별점 목록 갯수
	 * @param userIdNum
	 * @return
	 */
	public int getDbCountMyBookGrade(Integer userIdNum);
	
	/**
	 * 해당 책에 별점 목록 갯수
	 * @param bookIdNum
	 * @return
	 */
	public int getDbCountBookGrade(Integer bookIdNum);
	
}
