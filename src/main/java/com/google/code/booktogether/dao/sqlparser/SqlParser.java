package com.google.code.booktogether.dao.sqlparser;

public interface SqlParser {
	
	/**
	 * SQL가지고 오기
	 * @param xmlname SQL 빈 이름
	 * @param sqlKey SQL 키값
	 * @return
	 */
	public String getSQL(String xmlname,String sqlKey);	
	
}
