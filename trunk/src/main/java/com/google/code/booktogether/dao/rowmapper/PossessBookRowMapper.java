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
public class PossessBookRowMapper implements
		ParameterizedRowMapper<PossessBook>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PossessBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		PossessBook possessBook = new PossessBook();
		
		possessBook.setIdNum(rs.getInt("IDNUM"));
		possessBook.setPurchaseDate(rs.getDate("PDATE"));
		possessBook.setPurchasePrice(rs.getInt("PRICE"));
		possessBook.setBeginRead(rs.getDate("BREAD"));
		possessBook.setEndRead(rs.getDate("EREAD"));
		possessBook.setQuality(rs.getInt("QUALITY"));
		possessBook.setState(rs.getInt("STATE"));
		
		possessBook.getBook().setIdNum(rs.getInt("BIDNUM"));
		possessBook.getUser().setIdNum(rs.getInt("UIDNUM"));
		
		return possessBook;
	}

}
