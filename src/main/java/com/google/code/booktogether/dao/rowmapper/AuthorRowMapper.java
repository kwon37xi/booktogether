package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.web.domain.Author;

/**
 * 지은이 정보 목록화 할때 사용
 * @author ParkHaeCheol
 *
 */
@Scope("prototype")
@Component("authorRowMapper")
public class AuthorRowMapper implements ParameterizedRowMapper<Author>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Author author=new Author();
		
		author.setId(rs.getInt("ID"));
		author.setName(rs.getString("NAME"));
		author.setAuthor_div(rs.getInt("AUTHOR_DIV"));
		
		return author;
	}

	

}
