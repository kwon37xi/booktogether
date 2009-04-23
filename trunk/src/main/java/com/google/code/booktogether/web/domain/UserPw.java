package com.google.code.booktogether.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;


/**
 * 사용자 비밀번호 도메인
 * @author ParkHaeCheol
 */
@Entity
@Table(name="user")
public class UserPw extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 비밀번호 일련번호
	 */
	@Column(name="idNum")
	private Integer idNum;
	
	/**
	 * 사용자 일련번호
	 */
	@Column(name="idNum")
	private Integer userIdNum;
	
	/**
	 * 비밀번호
	 */
	@Column(name="idNum")
	private String pw;
	
	/**
	 * 비밀번호 MD5
	 */
	@Column(name="idNum")
	private byte[] digest;
	
	/**
	 * SALT 인증키
	 */
	@Column(name="idNum")
	private byte[] salt;
	
	
	
	@Override
	public boolean equals(Object o) {

		if (o instanceof UserPw == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		UserPw rhs = (UserPw) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum,rhs.getIdNum());
		equb.append(userIdNum, rhs.getUserIdNum());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(29, 37);
		hashcode.append(idNum);
		hashcode.append(userIdNum);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {
		
		//비밀번호 정보는 외부에 나타나거나
		//Log로 남기지 않아야 한다.

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("userIdNum", userIdNum);

		return tob.toString();

	}
	
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
