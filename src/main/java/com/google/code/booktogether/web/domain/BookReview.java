package com.google.code.booktogether.web.domain;

/**
 * 리뷰 도메인
 * @author ParkHaeCheol
 */

public class BookReview extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 사용자 일련번호
	 */
	private int id;
	
	/**
	 * 사용자 일련번호
	 */
	private int user_id;
	
	/**
	 * 책 일련번호
	 */
	private int book_id;
	
	
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


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

}
