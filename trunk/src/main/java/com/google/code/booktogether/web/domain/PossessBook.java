package com.google.code.booktogether.web.domain;

import java.util.Date;

/**
 * 소유책 도메인
 * @author ParkHaeCheol
 *
 */
public class PossessBook  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 소유책 일련번호
	 */
	private Integer idNum;
	
	/**
	 * 소유한 책 정보
	 */
	private Book book=new Book();
	
	/**
	 * 소유자 정보
	 */
	private User user=new User();
	
	/**
	 * 구입 날짜
	 */
	private Date purchaseDate;
	
	/**
	 * 구입 가격
	 */
	private Integer purchasePrice;
	
	/**
	 * 독서 시작일
	 */
	private Date beginRead;
	
	/**
	 * 독서 종료일
	 */
	private Date endRead;
	
	/**
	 * 책 품질
	 */
	private Integer quality;
	
	
	/**
	 * 책 상태
	 */
	private Integer state;
	
	

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getBeginRead() {
		return beginRead;
	}

	public void setBeginRead(Date beginRead) {
		this.beginRead = beginRead;
	}

	public Date getEndRead() {
		return endRead;
	}

	public void setEndRead(Date endRead) {
		this.endRead = endRead;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	
	
}
