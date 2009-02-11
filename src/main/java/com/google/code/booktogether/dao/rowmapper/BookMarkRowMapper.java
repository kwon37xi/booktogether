package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.domain.User;

/**
 * 인용구 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class BookMarkRowMapper implements ParameterizedRowMapper<BookMark>,Serializable{

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
		
		bookMark.setUser(new User());
		bookMark.getUser().setId(rs.getInt("UID"));
		bookMark.getUser().setUser_id(rs.getString("USER_ID"));
		bookMark.getUser().setName(rs.getString("NAME"));
		bookMark.getUser().setNickname(rs.getString("USER_NICKNAME"));
		
		return bookMark;
	}

	

}
