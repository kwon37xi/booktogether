package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 블로그 포스트 정보
 * 
 * @author ParkHaeCheol
 * 
 */
public class ReviewBlogPost extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

	private Integer userIdNum;

	private String postNum;

	private Integer bookIdNum;

	
	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Integer getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(Integer userIdNum) {
		this.userIdNum = userIdNum;
	}

	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	public Integer getBookIdNum() {
		return bookIdNum;
	}

	public void setBookIdNum(Integer bookIdNum) {
		this.bookIdNum = bookIdNum;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof ReviewBlogPost == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		ReviewBlogPost rhs = (ReviewBlogPost) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(userIdNum, rhs.getUserIdNum());
		equb.append(postNum, rhs.getPostNum());
		equb.append(bookIdNum, rhs.getBookIdNum());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(17, 37);
		hashcode.append(idNum);
		hashcode.append(userIdNum);
		hashcode.append(postNum);
		hashcode.append(bookIdNum);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("userIdNum", userIdNum);
		tob.append("postNum", postNum);
		tob.append("bookIdNum", bookIdNum);

		return tob.toString();

	}

}
