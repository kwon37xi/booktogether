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
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListBookMark(String book_id,int startPage, int endPage);

	/**
	 * 내가 작성한 인용구 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListMyBookMark(int user_id,int startPage, int endPage);

	/**
	 * 내가 매긴 공감이 있는지 체크
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	public int isExistVibe(int id,int user_id);

	/**
	 * 공감등록
	 * @param bookmark_id
	 * @param user_id
	 * @return
	 */
	public int insertVibe(int bookmark_id, int user_id);


	/**
	 * 공감 올리기
	 * @param bookMark
	 * @return
	 */
	public int modifyVibeBookMark(BookMark bookMark);
	
	
	
}
