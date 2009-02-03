package com.google.code.booktogether.web.domain;

/**
 * 저자 도메인
 * @author ParkHaeCheol
 *
 */
public class Author extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;		//이름
	private int author_div;		//구분, 1: 지은이, 2: 옮김, 3: 원저자
	
	public int getAuthor_div() {
		return author_div;
	}
	public void setAuthor_div(int author_div) {
		this.author_div = author_div;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

}
