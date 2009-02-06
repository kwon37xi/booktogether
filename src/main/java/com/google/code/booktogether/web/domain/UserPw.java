package com.google.code.booktogether.web.domain;

/**
 * 사용자 비밀번호 도메인
 * @author ParkHaeCheol
 */

public class UserPw extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 사용자 비밀번호 일련번호
	 */
	private int id;
	
	/**
	 * 사용자 일련번호
	 */
	private int user_id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
