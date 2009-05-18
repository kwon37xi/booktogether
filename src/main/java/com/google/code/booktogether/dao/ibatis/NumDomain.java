package com.google.code.booktogether.dao.ibatis;



import java.io.Serializable;

public class NumDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer num  = new Integer("0");

	public NumDomain(Integer num) {this.num = num;}
	public NumDomain(int num) {this.num = new Integer(num);}
	public NumDomain() {}

	public String toString() {return num.toString();}

	public Integer getNum () {return this.num;}

	public void setNum(Integer num) {this.num = num;}

}