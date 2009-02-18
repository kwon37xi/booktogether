package com.google.code.booktogether.web.domain;

import java.util.Date;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 사용자 도메인
 * @author ParkHaeCheol
 */

public class User extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 사용자 일련번호
	 */
	private Integer idNum;
	
	/**
	 * 사용자 ID
	 */
	private String userId;
	
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
	private Date inputDate;
	
	/**
	 * 탈퇴유무
	 */
	private Integer isDelete;
	
	/**
	 * 사용자 추가정보 클래스
	 */
	private UserAddInfo userAddInfo;
	
	/**
	 * 내 생활 반경
	 */
	private Zone[] zones;
	
	
	
	
	
	
	
	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public UserAddInfo getUserAddInfo() {
		return userAddInfo;
	}

	public void setUserAddInfo(UserAddInfo userAddInfo) {
		this.userAddInfo = userAddInfo;
	}

	public Zone[] getZones() {
		return zones;
	}

	public void setZones(Zone[] zones) {
		this.zones = zones;
	}


	
	
}
