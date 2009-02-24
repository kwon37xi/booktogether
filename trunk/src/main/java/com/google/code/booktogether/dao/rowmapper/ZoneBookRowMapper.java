package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.PossessBook;

/**
 * 내가 소유한 책 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class ZoneBookRowMapper implements
		ParameterizedRowMapper<PossessBook>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PossessBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		PossessBook possessBook = new PossessBook();
		
		possessBook.setIdNum(rs.getInt("IDNUM"));
		possessBook.setState(rs.getInt("STATE"));
		
		possessBook.getBook().setIdNum(rs.getInt("BIDNUM"));
		possessBook.getBook().setBookCover(rs.getString("BCOVER"));
		possessBook.getBook().setName(rs.getString("BNAME"));
		
		possessBook.getUser().setIdNum(rs.getInt("UIDNUM"));
		possessBook.getUser().setName(rs.getString("UNAME"));
		possessBook.getUser().setUserId(rs.getString("UID"));
		
		return possessBook;
	}

}
