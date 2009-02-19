package com.google.code.booktogether.web.domain;

import java.util.List;

public class BookMarkList {

	private Book book;
	
	private List<BookMark> bookMarkList;

	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookMark> getBookMarkList() {
		return bookMarkList;
	}

	public void setBookMarkList(List<BookMark> bookMarkList) {
		this.bookMarkList = bookMarkList;
	}
	
	
	
}
