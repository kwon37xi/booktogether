package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

public class Zone extends BaseObject {

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

	@Override
	public boolean equals(Object o) {

		if (o instanceof Zone == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Zone rhs = (Zone) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(userIdNum, rhs.getUserIdNum());
		equb.append(zone, rhs.getZone());
		equb.append(zoneName, rhs.getZoneName());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 73);
		hashcode.append(idNum);
		hashcode.append(userIdNum);
		hashcode.append(zone);
		hashcode.append(zoneName);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("userIdNum", userIdNum);
		tob.append("zone", zone);
		tob.append("zoneName", zoneName);

		return tob.toString();

	}

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
