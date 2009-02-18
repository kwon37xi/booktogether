package com.google.code.booktogether.web.domain;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class Library extends BaseObject{
	
	
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
	private User user=new User();
	
	/**
	 * 공개/비공개
	 */
	private Integer isOpen;
	
	
	
	

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
