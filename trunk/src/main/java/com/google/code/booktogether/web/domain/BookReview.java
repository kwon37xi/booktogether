package com.google.code.booktogether.web.domain;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 리뷰 도메인
 * @author ParkHaeCheol
 */

public class BookReview extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 일련번호
	 */
	private Integer idNum;
	
	/**
	 * 사용자 정보
	 */
	private User user=new User();
	
	/**
	 * 책 정보
	 */
	private Book book=new Book();
	
	/**
	 * 추천수
	 */
	private Integer recommend;
	
	/**
	 * 리뷰 제목
	 * @return
	 */
	private String title;
	
	/**
	 * 리뷰 내용
	 * @return
	 */
	private String review;
	
	
	
	
	
	public Integer getIdNum() {
		return idNum;
	}


	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}


	public Integer getRecommend() {
		return recommend;
	}


	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}

}
