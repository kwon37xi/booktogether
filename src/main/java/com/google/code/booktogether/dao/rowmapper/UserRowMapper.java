package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;

/**
 * 사용자 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class UserRowMapper implements ParameterizedRowMapper<User>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user=new User();
		user.setUserAddInfo(new UserAddInfo());
		
		user.setIdNum(rs.getInt("ID"));
		user.setUserId(rs.getString("USER_ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setNickname(rs.getString("NICKNAME"));
		user.setName(rs.getString("NAME"));
		user.setIsDelete(rs.getInt("DELETE_Y_N"));
		user.setInputDate(rs.getDate("INPUT_DATE"));
		
		user.getUserAddInfo().setIdNum(rs.getInt("UAI_ID"));
		user.getUserAddInfo().setBlog(rs.getString("BLOG"));
		user.getUserAddInfo().setThumnail(rs.getString("THUMNAIL"));
		
		return user;
	}

	

}
