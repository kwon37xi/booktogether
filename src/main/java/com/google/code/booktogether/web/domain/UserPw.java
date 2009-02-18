package com.google.code.booktogether.web.domain;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 사용자 비밀번호 도메인
 * @author ParkHaeCheol
 */

public class UserPw extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 비밀번호 일련번호
	 */
	private Integer idNum;
	
	/**
	 * 사용자 일련번호
	 */
	private Integer userIdNum;
	
	/**
	 * 비밀번호
	 */
	private String pw;
	
	/**
	 * 비밀번호 MD5
	 */
	private byte[] digest;
	
	/**
	 * SALT 인증키
	 */
	private byte[] salt;
	
	
	
	
	
	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}
	
	
	public byte[] getDigest() {
		return digest;
	}

	public void setDigest(byte[] digest) {
		this.digest = digest;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	
	public Integer getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(Integer userIdNum) {
		this.userIdNum = userIdNum;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	
}
