package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookGrade;

/**
 * 별점 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
@Scope("prototype")
@Component("myBookGradeRowMapper")
public class MyBookGradeRowMapper implements ParameterizedRowMapper<BookGrade>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookGrade mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BookGrade bookGrade=new BookGrade();

		bookGrade.setId(rs.getInt("ID"));
		bookGrade.setGrade(rs.getInt("GRADE"));
		
		bookGrade.setBook(new Book());
		bookGrade.getBook().setId(rs.getInt("UID"));
		bookGrade.getBook().setName(rs.getString("BOOK_NAME"));
		bookGrade.getBook().setISBN10(rs.getString("ISBN"));
		
		return bookGrade;
	}

	

}
