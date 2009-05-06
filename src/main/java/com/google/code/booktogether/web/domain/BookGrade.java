package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 별점 도메인
 * 
 * @author ParkHaeCheol
 */

public class BookGrade extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 별점 일련번호
	 */
	private Integer idNum;

	/**
	 * 사용자 정보
	 */
	private User user = new User();

	/**
	 * 책 정보
	 */
	private Book book = new Book();

	/**
	 * 별점
	 */
	private Integer grade;

	@Override
	public boolean equals(Object o) {

		if (o instanceof BookGrade == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		BookGrade rhs = (BookGrade) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(grade, rhs.getGrade());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(3, 37);
		hashcode.append(idNum);
		hashcode.append(grade);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("name", grade);
		tob.append("BOOK", book.toString());
		tob.append("USER", user.toString());

		return tob.toString();

	}

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
