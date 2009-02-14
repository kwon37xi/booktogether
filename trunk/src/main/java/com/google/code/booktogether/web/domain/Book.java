package com.google.code.booktogether.web.domain;


/**
 * 책 도메인
 * @author ParkHaeCheol
 * @modified 02.13 YoonYoung
 *  - int 타입을 String 타입으로 수정
 */

public class Book extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;					
	
	/**
	 * 책이름
	 */
	private String name="";
	
	/**
	 * 책지은이들
	 */
	private Author[] authors=null;
	
	private String[] author_name;
	
	private String[] author_div;
	
	/**
	 * ISBN 10자리
	 */
	private String ISBN10="";
	
	/**
	 * ISBN 13자리
	 */
	private String ISBN13="";	
	
	/**
	 * 출판사
	 */
	private String publish_comp="";
	
	/**
	 * 출판일
	 */
	private String publish_date="";
	
	/**
	 * 책가격
	 */
	private String price;	
	
	/**
	 * 분류
	 */
	private String category="";
	
	/**
	 * 책표지이미지
	 */
	private String corver="";
	
	/**
	 * 책 설명
	 */
	private String description="";
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String[] author_name) {
		this.author_name = author_name;
	}
	public String[] getAuthor_div() {
		return author_div;
	}
	public void setAuthor_div(String[] author_div) {
		this.author_div = author_div;
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
	public String getPublish_comp() {
		return publish_comp;
	}
	public void setPublish_comp(String publish_comp) {
		this.publish_comp = publish_comp;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCorver() {
		return corver;
	}
	public void setCorver(String corver) {
		this.corver = corver;
	}
	public Author[] getAuthors() {
		return authors;
	}
	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}
	

}
