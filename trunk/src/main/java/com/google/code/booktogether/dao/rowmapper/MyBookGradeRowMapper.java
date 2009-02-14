package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;

/**
 * 별점 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class MyBookGradeRowMapper implements ParameterizedRowMapper<BookGrade>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookGrade mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BookGrade bookGrade=new BookGrade();

		bookGrade.setId(rs.getString("ID"));
		bookGrade.setGrade(rs.getInt("GRADE"));
		
		bookGrade.setBook(new Book());
		bookGrade.getBook().setId(rs.getString("UID"));
		bookGrade.getBook().setName(rs.getString("BOOK_NAME"));
		bookGrade.getBook().setISBN10(rs.getString("ISBN"));
		
		return bookGrade;
	}

	

}
