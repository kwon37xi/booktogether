package com.google.code.booktogether.web.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class BaseObject implements Serializable{

	/**
	 * 해당 도메인 클래스의 정보를 출력하기
	 * 기초적인 도메인 클래스를 관리하는 클래스
	 */
	private static final long serialVersionUID = 1L;

	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public boolean equals(Object o){
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
