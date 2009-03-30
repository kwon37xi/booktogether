package com.google.code.booktogether.web.domain;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 좋은말 도메인
 * 
 * @author ParkHaeCheol
 * 
 */
public class GoodWriter extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer idNum;
	
	/**
	 * 입력날짜
	 */
	private Date inputDate;
	
	/**
	 * 내용
	 */
	private String content;
	


	@Override
	public boolean equals(Object o) {

		if (o instanceof GoodWriter == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		GoodWriter rhs = (GoodWriter) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(inputDate, rhs.getInputDate());
		equb.append(content, rhs.getContent());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(17, 37);
		hashcode.append(idNum);
		hashcode.append(inputDate);
		hashcode.append(content);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {
		
		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("inputDate", inputDate);
		tob.append("content", content);

		return tob.toString();
		
	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
