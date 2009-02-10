package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.BookReview;

public interface BookReviewDao {

	/**
	 * 리뷰 등록
	 * @param bookReview
	 * @return
	 */
	public int insertReview(BookReview bookReview);
	
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
	public int modifyReview(BookReview bookReview);
	
	/**
	 * 리뷰 삭제
	 * @param id
	 * @return
	 */
	public int deleteReview(BookReview bookReview);
	
	/**
	 * 리뷰 목록(책 조회시)
	 * @param book_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookReview> getListBookReview(int book_id,int startPage, int endPage);
	
	/**
	 * 내가 매긴 리뷰 목록
	 * @param user_id
	 * @param startPage
	 * @param endPage
	 * @return
	 */
	public List<BookReview> getListMyBookReview(int user_id,int startPage, int endPage);
	
	/**
	 * 내가 작성한 리뷰이 있는지 체크
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	public int isExistReview(int book_id,int user_id);
}
