package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.BookMark;

public interface BookMarkDao {

	/**
	 * 인용구 등록
	 * @param bookMark
	 * @return
	 */
	public int insertBookMark(BookMark bookMark);

	/**
	 * 인용구 수정
	 * @param bookMark
	 * @return
	 */
	public int modifyBookMark(BookMark bookMark);

	/**
	 * 인용구 삭제
	 * @param bookMark
	 * @return
	 */
	public int deleteBookMark(BookMark bookMark);

	/**
	 * 인용구 목록(책 조회시)
	 * @param bookIdNum
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListBookMark(Integer bookIdNum,Integer startPage, Integer endPage);

	/**
	 * 내가 작성한 인용구 목록
	 * @param userIdNum
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListMyBookMark(Integer userIdNum,Integer startPage, Integer endPage);

	/**
	 * 내가 매긴 공감이 있는지 체크
	 * @param vibeIdNum
	 * @param userIdNum
	 * @return
	 */
	public int isExistVibe(Integer vibeIdNum,Integer userIdNum);

	/**
	 * 공감등록
	 * @param bookMarkIdNum
	 * @param userIdNum
	 * @return
	 */
	public int insertVibe(Integer bookMarkIdNum, Integer userIdNum);


	/**
	 * 공감 올리기
	 * @param bookMark
	 * @return
	 */
	public int modifyVibeBookMark(BookMark bookMark);
	
	
	
}
