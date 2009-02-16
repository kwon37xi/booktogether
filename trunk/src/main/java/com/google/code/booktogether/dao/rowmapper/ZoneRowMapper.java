package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.google.code.booktogether.web.domain.Zone;

/**
 * 지역명 정보 목록화할때 사용
 * @author ParkHaeCheol
 *
 */
public class ZoneRowMapper implements ParameterizedRowMapper<Zone>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Zone mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Zone zone=new Zone();
		
		zone.setId(rs.getInt("ID"));
		zone.setUserIdNum(rs.getInt("USER_ID_NUM"));
		zone.setZone(rs.getInt("ZONE"));
		zone.setZoneName(rs.getString("ZONE_NAME"));
		
		return zone;
	}

	

}
