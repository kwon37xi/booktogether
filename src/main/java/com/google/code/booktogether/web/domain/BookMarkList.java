package com.google.code.booktogether.web.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class BookMarkList extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Book book;

	private List<BookMark> bookMarkList;

	@Override
	public boolean equals(Object o) {

		if (o instanceof BookMarkList == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		BookMarkList rhs = (BookMarkList) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(book, rhs.getBook());
		equb.append(bookMarkList, rhs.getBookMarkList());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 109);
		hashcode.append(book);
		hashcode.append(bookMarkList);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		if (book != null) {
			tob.append("BOOK", book.toString());
		}

		if (bookMarkList != null) {
			
			for(BookMark bookMark : bookMarkList){
				tob.append("BOOKMARK", bookMark.toString());
			}

		}

		return tob.toString();

	}

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
