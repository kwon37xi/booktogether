package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;

/**
 * 사용자 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class LibraryBookRowMapper implements ParameterizedRowMapper<LibraryBook>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public LibraryBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		LibraryBook libraryBook=new LibraryBook();
		
		libraryBook.setIdNum(rs.getInt("IDNUM"));
		libraryBook.setReadDate(rs.getDate("READ_DATE"));
		libraryBook.setState(rs.getInt("STATE"));
		libraryBook.setIsPosssess(rs.getInt("ISPOSSESS"));
	
		libraryBook.getBook().setIdNum(rs.getInt("BIDNUM"));
		libraryBook.getLibrary().setIdNum(rs.getInt("LIDNUM"));
		
		return libraryBook;
	}

	

}
