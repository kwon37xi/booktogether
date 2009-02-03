package com.google.code.booktogether.web.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 책 도메인
 * @author ParkHaeCheol
 *
 */

@Scope("prototype")
@Component("bookDomain")
public class Book extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;					//id
	private String name;			//책이름
	private Author[] authors;		//책지은이들
	private int page;				//페이지수
	private String ISBN10;			//ISBN 10자리
	private String ISBN13;			//ISBN 13자리
	private String publish_comp;	//출판사
	private String publish_date;	//출판일
	private int price;				//책가격
	private String category;		//분류
	private String size;			//책사이즈
	private String corver;			//책표지이미지
	private StringBuilder content;	//책 설명
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCorver() {
		return corver;
	}
	public void setCorver(String corver) {
		this.corver = corver;
	}
	public StringBuilder getContent() {
		return content;
	}
	public void setContent(StringBuilder content) {
		this.content = content;
	}
	
	public Author[] getAuthors() {
		return authors;
	}
	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}
	

}
