package com.google.code.booktogether.web.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 서재안의 등록된 책 도메인
 */
public class LibraryBook extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

	/**
	 * 책 정보
	 */
	private Book book = new Book();

	/**
	 * 서재 정보
	 */
	private Library library = new Library();

	/**
	 * 읽기 시작한 날짜/ 읽은 날짜 상태에 따라 달라진다.
	 */
	private Date readDate;

	/**
	 * 책 상태 : 책고 있는 책:0 / 읽고 싶은책:1 / 읽은 책:2
	 */
	private Integer State;

	@Override
	public boolean equals(Object o) {

		if (o instanceof LibraryBook == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		LibraryBook rhs = (LibraryBook) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(readDate, rhs.getReadDate());
		equb.append(State, rhs.getState());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 37);
		hashcode.append(idNum);
		hashcode.append(readDate);
		hashcode.append(State);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("readDate", readDate);
		tob.append("State", State);

		tob.append("BOOK", book.toString());
		tob.append("LIBRARY", library.toString());

		return tob.toString();

	}

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

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

}