package com.google.code.booktogether.web.domain;


public class UserAddInfo {

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
