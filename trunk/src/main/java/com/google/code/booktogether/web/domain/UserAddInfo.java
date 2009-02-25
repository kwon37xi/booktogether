package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class UserAddInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

	/**
	 * 사용자 일련번호
	 */
	private Integer userIdNum;

	/**
	 * 블로그 주소
	 */
	private String blog;

	/**
	 * 썸네일 이미지 파일명
	 */
	private String thumnail;

	@Override
	public boolean equals(Object o) {

		if (o instanceof UserAddInfo == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		UserAddInfo rhs = (UserAddInfo) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(userIdNum, rhs.getUserIdNum());
		equb.append(blog, rhs.getBlog());
		equb.append(thumnail, rhs.getThumnail());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(7, 37);
		hashcode.append(idNum);
		hashcode.append(userIdNum);
		hashcode.append(blog);
		hashcode.append(thumnail);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		// 비밀번호 정보는 외부에 나타나거나
		// Log로 남기지 않아야 한다.

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("userIdNum", userIdNum);
		tob.append("blog", blog);
		tob.append("thumnail", thumnail);

		return tob.toString();

	}

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

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getThumnail() {
		return thumnail;
	}

	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}

}
