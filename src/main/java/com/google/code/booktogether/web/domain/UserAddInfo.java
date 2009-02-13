package com.google.code.booktogether.web.domain;

public class UserAddInfo extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 일련번호
	 */
	private int id;
	
	/**
	 * 사용자 일련번호
	 */
	private int user_id;
	
	/**
	 * 블로그 주소
	 */
	private String blog;
	
	/**
	 * 썸네일 이미지 파일명
	 */
	private String thumnail;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	

}
