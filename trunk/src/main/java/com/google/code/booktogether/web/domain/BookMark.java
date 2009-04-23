package com.google.code.booktogether.web.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 인용구 도메인
 * 
 * @author ParkHaeCheol
 */

public class BookMark extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

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
	
	/**
	 * 사용자 정보
	 */
	private User user;

	/**
	 * 책 정보
	 */
	private Book book;

	@Override
	public boolean equals(Object o) {

		if (o instanceof BookMark == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		BookMark rhs = (BookMark) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(page, rhs.getPage());
		equb.append(vibeNum, rhs.getVibeNum());
		equb.append(inputDate, rhs.getInputDate());
		equb.append(content, rhs.getContent());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 61);
		hashcode.append(idNum);
		hashcode.append(page);
		hashcode.append(vibeNum);
		hashcode.append(inputDate);
		hashcode.append(content);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("page", page);
		tob.append("vibeNum", vibeNum);
		tob.append("inputDate", inputDate);
		tob.append("content", content);
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
