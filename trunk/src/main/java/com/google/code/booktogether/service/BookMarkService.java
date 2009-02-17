package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookMark;

public interface BookMarkService {

	/**
	 * 인용구 등록
	 * @param bookMark
	 * @return
	 */
	public boolean insertBookMark(BookMark bookMark);
	
	
	/**
	 * 인용구 수정
	 * @param bookMark
	 * @return
	 */
	public boolean modifyBookMark(BookMark bookMark);
	
	/**
	 * 인용구 삭제
	 * @param bookMark
	 * @return
	 */
	public boolean deleteBookMark(BookMark bookMark);
	
	/**
	 * 인용구 목록(책 조회시)
	 * @param bookIdNum
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListBookMark(Integer bookIdNum, Integer startPage, Integer endPage);
	
	/**
	 * 내가 입력한 인용구 목록
	 * @param userIdNum
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookMark> getListMyBookMark(Integer userIdNum, Integer startPage, Integer endPage);

	/**
	 * 인용구 수정
	 * @param bookMark
	 * @return 수정결과 메세지
	 */
	public String modifyVibe(BookMark bookMark);
	
	
}
