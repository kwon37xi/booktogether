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
	 * 별점 
	 */
	private Integer grade;


	public Integer getIdNum() {
		return idNum;
	}


	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
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


	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	
	

}
