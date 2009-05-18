package com.google.code.booktogether.dao.ibatis;



import java.io.Serializable;

public class StrDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  str  = "";

	public StrDomain(String str) {this.str = str;}
	public StrDomain() {}

	public String toString() {return this.str;}

	public String getStr() {return this.str;}

	public void setStr   (String   str) {this.str = str;}

}