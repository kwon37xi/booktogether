package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookMark;

/**
 * 인용구 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class MyBookMarkRowMapper implements ParameterizedRowMapper<BookMark>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookMark mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BookMark bookMark=new BookMark();
 	    
		bookMark.setId(rs.getInt("ID"));
		bookMark.setPage(rs.getInt("PAGE"));
		bookMark.setVibeNum(rs.getInt("VIBE_NUM"));
		bookMark.setInput_date(rs.getDate("INPUT_DATE"));
		bookMark.setContent(rs.getString("CONTENT"));
		
		bookMark.setBook(new Book());
		bookMark.getBook().setId(rs.getInt("BID"));
		bookMark.getBook().setName(rs.getString("BOOK_NAME"));
		bookMark.getBook().setPublish_comp(rs.getString("BOOK_COMP"));
		
		return bookMark;
	}

	

}
