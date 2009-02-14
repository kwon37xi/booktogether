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
	 * 리뷰 조회(내가쓴 리뷰)-조회화면, 수정화면
	 * @param bookReview
	 * @return
	 */
	public BookReview getReview(BookReview bookReview);
	
	
	/**
	 * 리뷰 조회(모든 회원조회)
	 * @param id
	 * @return
	 */
	public BookReview getReview(int id);
	
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
	public List<BookReview> getListBookReview(String book_id, int startPage, int endPage);
	
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
	public int isExistReview(String book_id,int user_id);
	
	/**
	 * 추천수 올리기
	 * @param bookReview
	 * @return
	 */
	public int modifyReviewRecommend(BookReview bookReview);
	
	/**
	 * 추천인 등록
	 * @param bookReview
	 * @return
	 */
	public int insertRecommend(BookReview bookReview);
	
	/**
	 * 기존에 추천기록이 있는지
	 * @param bookReview
	 * @return
	 */
	public int isExistRecommend(BookReview bookReview);
	
}
