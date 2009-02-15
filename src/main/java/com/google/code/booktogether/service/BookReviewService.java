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
	 * 리뷰 조회(내가 쓴 리뷰)-수정화면,조회화면
	 * @param bookReview
	 * @return
	 */
	public BookReview getReview(BookReview bookReview);
	
	/**
	 * 리뷰 조회(모든 회원들이)
	 * @param id : 리뷰 일련번호
	 * @return
	 */
	public BookReview getReview(int id);
	
	
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
	 
	 
	 /**
	  * 추천수 올리기
	  * @param bookReview
	  * @return
	  */
	 public String modifyReviewRecommend(BookReview bookReview);
	 
	 
	 
	 
	 
	 
}
