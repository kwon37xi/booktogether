package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.domain.User;

/**
 * 별점 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class BookGradeRowMapper implements ParameterizedRowMapper<BookGrade>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookGrade mapRow(ResultSet rs, int rowNum) throws SQLException {

		BookGrade bookGrade = new BookGrade();

		bookGrade.setId(rs.getInt("ID"));
		bookGrade.setGrade(rs.getInt("GRADE"));

		bookGrade.setUser(new User());
		bookGrade.getUser().setIdNum(rs.getInt("UID"));
		bookGrade.getUser().setUserId(rs.getString("USER_ID"));
		bookGrade.getUser().setNickname(rs.getString("USER_NICKNAME"));

		return bookGrade;
	}

}
