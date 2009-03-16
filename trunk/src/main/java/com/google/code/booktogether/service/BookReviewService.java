package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.UserBlog;
import com.google.code.booktogether.web.page.PageBean;

public interface BookReviewService {

	/**
	 * 리뷰 등록
	 * @param bookReview
	 * @return
	 */
	public boolean insertReview(BookReview bookReview, UserBlog userBlog);
	
	
	/**
	 * 리뷰 조회(내가 쓴 리뷰)-수정화면,조회화면
	 * @param bookReview
	 * @return
	 */
	public BookReview getReview(BookReview bookReview);
	
	/**
	 * 리뷰 조회(모든 회원들이)
	 * @param bookReviewIdNum : 리뷰 일련번호
	 * @return
	 */
	public BookReview getReview(Integer bookReviewIdNum);
	
	
	/**
	 * 리뷰 수정
	 * @param bookReview
	 * @return
	 */
	public boolean modifyReview(BookReview bookReview,UserBlog userBlog,String postNum);
	
	/**
	 * 리뷰 삭제
	 * @param bookReview
	 * @return
	 */
	public boolean deleteReview(BookReview bookReview);
	
	/**
	 * 리뷰 목록(책 조회시)
	 * @param bookIdNum
	 * @param pageBean
	 * @return
	 */
	public List<BookReview> getListBookReview(Integer bookIdNum, PageBean pageBean);
	
	/**
	 * 내가 입력한 리뷰 목록
	 * @param userIdNum
	 * @param pageBean
	 * @return
	 */
	public List<BookReview> getListMyBookReview(Integer userIdNum, PageBean pageBean);
	

	 /**
	  * 사용자가 입력한 리뷰가 있는지 체크
	  * @param bookIdNum
	  * @param userIdNum
	  * @return  true : 리뷰가 있다. false : 리뷰가 없다.
	  */
	 public boolean isExistReview(Integer bookIdNum,Integer userIdNum);
	 
	 
	 /**
	  * 추천수 올리기
	  * @param bookReview
	  * @return
	  */
	 public String modifyReviewRecommend(BookReview bookReview);
	 
	 
	 
	 
	 
	 
}
