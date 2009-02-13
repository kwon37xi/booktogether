package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Zipcode;

/**
 * 주소찾기 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class ZipcodeRowMapper implements ParameterizedRowMapper<Zipcode>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Zipcode mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Zipcode zipcode=new Zipcode();

		zipcode.setSeq(rs.getInt("SEQ"));
		zipcode.setSido(rs.getString("SIDO"));
		zipcode.setGugun(rs.getString("GUGUN"));
		zipcode.setDong(rs.getString("DONG"));
		
		return zipcode;
	}

	

}
