package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookGrade;

public interface BookGradeService {

	/**
	 * 별점 등록
	 * @param gradeReview
	 * @return
	 */
	public boolean insertGrade(BookGrade bookGrade);
	
	/**
	 * 별점 목록(책 조회시)
	 * @param bookGrade
	 * @return
	 */
	public List<BookGrade> getListBookGrade(int book_id, int startPage, int endPage);
	
	/**
	 * 내가 입력한 별점 목록
	 * @param bookGrade
	 * @return
	 */
	public List<BookGrade> getListMyBookGrade(int user_id, int startPage, int endPage);


}
