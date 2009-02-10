package com.google.code.booktogether.web.domain;

/**
 * 리뷰 도메인
 * @author ParkHaeCheol
 */

public class BookReview extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 일련번호
	 */
	private int id;
	
	/**
	 * 사용자 정보
	 */
	private User user=null;
	

	/**
	 * 책 정보
	 */
	private Book book=null;
	
	/**
	 * 추천수
	 */
	private int recommend=0;
	
	
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
	
	
	
	
	
	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
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

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
