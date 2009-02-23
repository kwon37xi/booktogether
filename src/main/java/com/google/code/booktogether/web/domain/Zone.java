package com.google.code.booktogether.web.domain;


public class Zone {
	
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
	 * 지역명 일련번호
	 */
	private Integer zone;
	
	/**
	 * 지역명 주소
	 */
	private String zoneName;

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}


	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}
	
	public Integer getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(Integer userIdNum) {
		this.userIdNum = userIdNum;
	}
	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}
	

}
