package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.page.PageBean;

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
	 * @param bookIdNum
	 * @param pageBean
	 * @return
	 */
	public List<BookGrade> getListBookGrade(Integer bookIdNum, PageBean pageBean);
	
	/**
	 * 내가 입력한 별점 목록
	 * @param userIdNum
	 * @param pageBean
	 * @return
	 */
	public List<BookGrade> getListMyBookGrade(Integer userIdNum, PageBean pageBean);

	/**
	 * 사용자가 입력한 별점이 있는지 체크
	 * @param bookIdNum
	 * @param userIdNum
	 * @return true : 별점이 있다. false : 별점이 없다.
	 */
	public boolean isExistGrade(Integer bookIdNum,Integer userIdNum);
}
