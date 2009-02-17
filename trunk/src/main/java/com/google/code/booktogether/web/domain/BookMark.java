package com.google.code.booktogether.web.domain;

import java.util.Date;

/**
 * 인용구 도메인
 * @author ParkHaeCheol
 */

public class BookMark extends BaseObject{
	
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
	 * 페이지 
	 */
	private Integer page;
	
	/**
	 * 공감수
	 */
	private Integer vibeNum;
	

	/**
	 * 입력날짜 
	 */
	private Date inputDate;
	
	
	/**
	 * 인용구
	 */
	private String content;


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


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getVibeNum() {
		return vibeNum;
	}


	public void setVibeNum(Integer vibeNum) {
		this.vibeNum = vibeNum;
	}


	public Date getInputDate() {
		return inputDate;
	}


	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
}
