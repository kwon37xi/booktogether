package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Book;

/**
 * Simple인 이유는 Content를 세팅하지 않는다.
 * 
 * @author ParkHaeCheol
 * 
 */
public class BookSimpleRowMapper implements ParameterizedRowMapper<Book>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

		Book book = new Book();

		book.setIdNum(rs.getInt("IDNUM"));
		book.setName(rs.getString("NAME"));
		book.setISBN10(rs.getString("ISBN10"));
		book.setISBN13(rs.getString("ISBN13"));
		book.setPublishComp(rs.getString("PUBLISH_COMP"));
		book.setPublishDate(rs.getString("PUBLISH_DATE"));
		book.setPrice(rs.getInt("PRICE"));
		book.setBookCover(rs.getString("Corver"));
		book.setCategory(rs.getString("CATEGORY"));

		return book;
	}

}
