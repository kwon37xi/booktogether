package com.google.code.booktogether.web.domain;

public class Zipcode {
	
	/**
	 * 일련번호
	 */
	private int seq;
	
	/**
	 * 우편번호
	 */
	private String zipcode;
	
	
	/**
	 * 특별시,광역시,도 
	 */
	private String sido;
	
	/**
	 * 시,군,구 
	 */
	private String gugun;
	
	/**
	 * 동,읍,면
	 */
	private String dong;
	
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
