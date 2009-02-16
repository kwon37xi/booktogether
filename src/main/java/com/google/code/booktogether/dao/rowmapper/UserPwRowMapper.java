package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.UserPw;

/**
 * 사용자 인증 정보
 * @author ParkHaeCheol
 *
 */
public class UserPwRowMapper implements ParameterizedRowMapper<UserPw>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UserPw mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserPw userPw=new UserPw();
		
		userPw.setUserIdNum(rs.getInt("USER_ID"));
		userPw.setDigest(rs.getBytes("PW"));
		userPw.setSalt(rs.getBytes("SALT"));
		
		return userPw;
	}

	

}
