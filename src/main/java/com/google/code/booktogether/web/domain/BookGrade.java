package com.google.code.booktogether.web.domain;

/**
 * 별점 도메인
 * @author ParkHaeCheol
 */

public class BookGrade extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 별점 일련번호
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
	 * 별점 
	 */
	private int grade=0;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
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
