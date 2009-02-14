package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Book;

/**
 * 책정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class BookRowMapper implements ParameterizedRowMapper<Book>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Book book=new Book();
		
		book.setId(rs.getString("ID"));
		book.setName(rs.getString("NAME"));
		book.setISBN10(rs.getString("ISBN10"));
		book.setISBN13(rs.getString("ISBN13"));
		book.setPublish_comp(rs.getString("PUBLISH_COMP"));
		book.setPublish_date(rs.getString("PUBLISH_DATE"));
		book.setPrice(rs.getString("PRICE"));
		book.setCorver(rs.getString("Corver"));
		book.setCategory(rs.getString("CATEGORY"));
		book.setDescription(rs.getString("DESCRIPTION"));
		
		return book;
	}

	

}
