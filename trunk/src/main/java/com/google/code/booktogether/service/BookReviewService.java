package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookReview;

public interface BookReviewService {

	/**
	 * 리뷰 등록
	 * @param bookReview
	 * @return
	 */
	public boolean insertReview(BookReview bookReview);
	
	
	/**
	 * 리뷰 조회
	 * @param bookReview
	 * @return
	 */
	public BookReview getReview(BookReview bookReview);
	
	
	/**
	 * 리뷰 수정
	 * @param bookReview
	 * @return
	 */
	public boolean modifyReview(BookReview bookReview);
	
	/**
	 * 리뷰 삭제
	 * @param bookReview
	 * @return
	 */
	public boolean deleteReview(BookReview bookReview);
	
	/**
	 * 리뷰 목록(책 조회시)
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookReview> getListBookReview(int book_id, int startPage, int endPage);
	
	/**
	 * 내가 입력한 리뷰 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookReview> getListMyBookReview(int user_id, int startPage, int endPage);
	

	 /**
	  * 사용자가 입력한 리뷰가 있는지 체크
	  * @param book_id
	  * @param user_id
	  * @return  true : 리뷰가 있다. false : 리뷰가 없다.
	  */
	 public boolean isExistReview(int book_id,int user_id);
}
