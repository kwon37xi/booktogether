package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class Library extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 내서재 일련번호
	 */
	private Integer idNum;

	/**
	 * 알림글
	 */
	private String notice;

	/**
	 * 사용자 정보
	 */
	private User user = new User();

	/**
	 * 공개/비공개
	 */
	private Integer isOpen;

	@Override
	public boolean equals(Object o) {

		if (o instanceof Library == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Library rhs = (Library) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(notice, rhs.getNotice());
		equb.append(isOpen, rhs.getIsOpen());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(23, 37);
		hashcode.append(idNum);
		hashcode.append(notice);
		hashcode.append(isOpen);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("notice", notice);
		tob.append("isOpen", isOpen);

		tob.append("USER", user.toString());

		return tob.toString();

	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

}
