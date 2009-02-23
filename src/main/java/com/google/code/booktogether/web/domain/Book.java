package com.google.code.booktogether.web.domain;

/**
 * 책 도메인
 * 
 * @author ParkHaeCheol
 * 
 */
public class Book {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer idNum;

	/**
	 * 책이름
	 */
	private String name;

	/**
	 * 책지은이들
	 */
	private Author[] authors;

	/**
	 * ISBN 10자리
	 */
	private String ISBN10;

	/**
	 * ISBN 13자리
	 */
	private String ISBN13;

	/**
	 * 출판사
	 */
	private String publishComp;

	/**
	 * 출판일
	 */
	private String publishDate;

	/**
	 * 책가격
	 */
	private Integer price;

	/**
	 * 분류
	 */
	private String category;

	/**
	 * 책표지이미지
	 */
	private String bookCover;

	/**
	 * 책 설명
	 */
	private String description;

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author[] getAuthors() {
		return authors;
	}

	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}

	public String getISBN10() {
		return ISBN10;
	}

	public void setISBN10(String isbn10) {
		ISBN10 = isbn10;
	}

	public String getISBN13() {
		return ISBN13;
	}

	public void setISBN13(String isbn13) {
		ISBN13 = isbn13;
	}

	public String getPublishComp() {
		return publishComp;
	}

	public void setPublishComp(String publishComp) {
		this.publishComp = publishComp;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
