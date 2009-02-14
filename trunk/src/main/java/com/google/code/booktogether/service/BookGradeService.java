package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookGrade;

public interface BookGradeService {

	/**
	 * 별점 등록
	 * @param bookGrade
	 * @return
	 */
	public boolean insertGrade(BookGrade bookGrade);
	
	
	/**
	 * 별점 수정
	 * @param bookGrade
	 * @return
	 */
	public boolean modifyGrade(BookGrade bookGrade);
	
	/**
	 * 별점 삭제
	 * @param bookGrade
	 * @return
	 */
	public boolean deleteGrade(BookGrade bookGrade);
	
	/**
	 * 별점 목록(책 조회시)
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookGrade> getListBookGrade(String book_id, int startPage, int endPage);
	
	/**
	 * 내가 입력한 별점 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookGrade> getListMyBookGrade(int user_id, int startPage, int endPage);

	/**
	 * 사용자가 입력한 별점이 있는지 체크
	 * @param book_id
	 * @param user_id
	 * @return true : 별점이 있다. false : 별점이 없다.
	 */
	public boolean isExistGrade(String book_id, int user_id);
}
