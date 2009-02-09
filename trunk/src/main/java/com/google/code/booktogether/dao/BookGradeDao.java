package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.BookGrade;

public interface BookGradeDao {

	public int insertGrade(BookGrade bookGrade);
	
	public List<BookGrade> getListBookGrade(int book_id,int startPage, int endPage);
	
	public List<BookGrade> getListMyBookGrade(int user_id,int startPage, int endPage);
	
}
