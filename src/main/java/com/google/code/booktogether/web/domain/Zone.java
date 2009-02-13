package com.google.code.booktogether.web.domain;

public class Zone extends BaseObject{
	
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
	 * 지역명
	 */
	private String zone;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	

}
