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
	 * 페이지 
	 */
	private int page=0;
	
	/**
	 * 공감수
	 */
	private int vibeNum=0;
	

	/**
	 * 입력날짜 
	 */
	private Date input_date=null;
	
	
	/**
	 * 인용구
	 */
	private String content;
	
	
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


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public Date getInput_date() {
		return input_date;
	}


	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getVibeNum() {
		return vibeNum;
	}

	public void setVibeNum(int vibeNum) {
		this.vibeNum = vibeNum;
	}

	
}
