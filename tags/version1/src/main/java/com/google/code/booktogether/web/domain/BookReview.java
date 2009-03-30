package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 리뷰 도메인
 * 
 * @author ParkHaeCheol
 */

public class BookReview extends BaseObject{

	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
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
	 * 추천수
	 */
	private Integer recommend;

	/**
	 * 리뷰 제목
	 * 
	 * @return
	 */
	private String title;

	/**
	 * 리뷰 내용
	 * 
	 * @return
	 */
	private String review;

	@Override
	public boolean equals(Object o) {

		if (o instanceof BookReview == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		BookReview rhs = (BookReview) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(recommend, rhs.getRecommend());
		equb.append(title, rhs.getTitle());
		equb.append(review, rhs.getReview());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(3, 37);
		hashcode.append(idNum);
		hashcode.append(recommend);
		hashcode.append(title);
		hashcode.append(review);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("recommend", recommend);
		tob.append("title", title);
		tob.append("review", review);

		tob.append("USER", user.toString());
		tob.append("BOOK", book.toString());

		return tob.toString();

	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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

}
