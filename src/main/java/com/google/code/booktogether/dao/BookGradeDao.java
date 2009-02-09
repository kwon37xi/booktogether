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
	 * @param id
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
	public List<BookGrade> getListBookGrade(int book_id,int startPage, int endPage);
	
	/**
	 * 내가 매긴 별점 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookGrade> getListMyBookGrade(int user_id,int startPage, int endPage);
	
	/**
	 * 내가 매긴 별점이 있는지 체크
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	public int isExistGrade(int book_id,int user_id);
}