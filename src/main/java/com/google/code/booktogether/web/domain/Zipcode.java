package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;


public class Zipcode extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;
	
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
	
	
	@Override
	public boolean equals(Object o) {

		if (o instanceof Zipcode == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		Zipcode rhs = (Zipcode) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum,rhs.getIdNum());
		equb.append(zipcode, rhs.getZipcode());
		equb.append(sido, rhs.getSido());
		equb.append(gugun, rhs.getGugun());
		equb.append(dong, rhs.getDong());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(19, 43);
		hashcode.append(idNum);
		hashcode.append(zipcode);
		hashcode.append(sido);
		hashcode.append(gugun);
		hashcode.append(dong);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("zipcode", zipcode);
		tob.append("sido", sido);
		tob.append("gugun", gugun);
		tob.append("dong", dong);

		return tob.toString();

	}
	
	
	
	public Integer getIdNum() {
		return idNum;
	}
	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
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
