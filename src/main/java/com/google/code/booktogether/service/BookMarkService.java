package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookMark;

public interface BookMarkService {

	/**
	 * 인용구 등록
	 * @param bookGrade
	 * @return
	 */
	public boolean insertBookMark(BookMark bookMark);
	
	
	/**
	 * 인용구 수정
	 * @param bookGrade
	 * @return
	 */
	public boolean modifyBookMark(BookMark bookMark);
	
	/**
	 * 인용구 삭제
	 * @param bookGrade
	 * @return
	 */
	public boolean deleteBookMark(BookMark bookMark);
	
	/**
	 * 인용구 목록(책 조회시)
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListBookMark(String book_id, int startPage, int endPage);
	
	/**
	 * 내가 입력한 인용구 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListMyBookMark(int user_id, int startPage, int endPage);

	/**
	 * 사용자가 입력한 공감이 있는지 체크
	 * @param id
	 * @param user_id
	 * @return true : 공감이 있다. false : 공감이 없다.
	 */
	public String modifyVibe(BookMark bookMark);
	
	
}
