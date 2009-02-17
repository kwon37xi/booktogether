package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.BookMark;

/**
 * 인용구 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class BookMarkRowMapper implements ParameterizedRowMapper<BookMark>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookMark mapRow(ResultSet rs, int rowNum) throws SQLException {

		BookMark bookMark = new BookMark();

		bookMark.setIdNum(rs.getInt("IDNUM"));
		bookMark.setPage(rs.getInt("PAGE"));
		bookMark.setVibeNum(rs.getInt("VIBE_NUM"));
		bookMark.setInputDate(rs.getDate("INPUT_DATE"));
		bookMark.setContent(rs.getString("CONTENT"));

		bookMark.getUser().setIdNum(rs.getInt("UIDNUM"));
		bookMark.getUser().setUserId(rs.getString("USER_ID"));
		bookMark.getUser().setName(rs.getString("USER_NAME"));
		bookMark.getUser().setNickname(rs.getString("USER_NICKNAME"));

		return bookMark;
	}

}
