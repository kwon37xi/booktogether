package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.web.domain.User;

/**
 * 사용자 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
@Scope("prototype")
@Component("userRowMapper")
public class UserRowMapper implements ParameterizedRowMapper<User>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user=new User();
		
		user.setId(rs.getInt("ID"));
		user.setUser_id(rs.getString("USER_ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setNickname(rs.getString("NICKNAME"));
		user.setName(rs.getString("NAME"));
		user.setPw(rs.getString("PW"));
		user.setDelete_y_n(rs.getInt("DELETE_Y_N"));
		user.setInput_date(rs.getDate("INPUT_DATE"));
		
		return user;
	}

	

}
