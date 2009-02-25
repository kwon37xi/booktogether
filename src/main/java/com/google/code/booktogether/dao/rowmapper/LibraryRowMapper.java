package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.UserAddInfo;


/**
 * 서재 정보
 * @author ParkHaeCheol
 *
 */
public class LibraryRowMapper implements ParameterizedRowMapper<Library>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Library mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Library library=new Library();
		
		library.setIdNum(rs.getInt("IDNUM"));
		library.setNotice(rs.getString("NOTICE"));
		library.setIsOpen(rs.getInt("ISOPEN"));
		
		library.getUser().setIdNum(rs.getInt("UIDNUM"));
		library.getUser().setName(rs.getString("UNAME"));
		library.getUser().setNickname(rs.getString("UNICKNAME"));
		library.getUser().setUserId(rs.getString("UID"));
		library.getUser().setIdNum(rs.getInt("UIDNUM"));
		
		library.getUser().setUserAddInfo(new UserAddInfo());
		library.getUser().getUserAddInfo().setThumnail(rs.getString("thumnail"));
		
		return library;
	}

	

}
