package com.google.code.booktogether.web.domain;

/**
 * 저자 도메인
 * @author ParkHaeCheol
 *
 */
public class Author extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer idNum;
	
	/**
	 * 이름
	 */
	private String name;
	
	/**
	 * 구분, 1: 지은이, 2: 옮김, 3: 원저자
	 */
	
	private Integer authorDiv;

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAuthorDiv() {
		return authorDiv;
	}

	public void setAuthorDiv(Integer authorDiv) {
		this.authorDiv = authorDiv;
	}	
	
	
	

}
