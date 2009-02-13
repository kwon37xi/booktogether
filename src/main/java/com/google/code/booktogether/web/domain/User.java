package com.google.code.booktogether.web.domain;

import java.util.Date;

/**
 * 사용자 도메인
 * @author ParkHaeCheol
 */

public class User extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 사용자 일련번호
	 */
	private int id;
	
	/**
	 * 사용자 ID
	 */
	private String user_id;
	
	/**
	 * 이메일
	 */
	private String email;
	
	
	/**
	 * 별명 
	 */
	private String nickname;
	
	/**
	 * 이름
	 */
	private String name;
	
	/**
	 * 등록날짜
	 */
	private Date input_date;
	
	/**
	 * 삭제유무
	 */
	private int delete_y_n;
	
	/**
	 * 사용자 추가정보 클래스
	 */
	private UserAddInfo userAddInfo;
	
	/**
	 * 내 생활 반경
	 */
	private Zone[] zones;
	
	
	public Zone[] getZones() {
		return zones;
	}
	public void setZones(Zone[] zones) {
		this.zones = zones;
	}
	public UserAddInfo getUserAddInfo() {
		return userAddInfo;
	}
	public void setUserAddInfo(UserAddInfo userAddInfo) {
		this.userAddInfo = userAddInfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	public int getDelete_y_n() {
		return delete_y_n;
	}
	public void setDelete_y_n(int delete_y_n) {
		this.delete_y_n = delete_y_n;
	}
	

}
