package com.google.code.booktogether.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.google.code.booktogether.web.domain.Author;

@Scope("prototype")
@Component("authorRowMapper")
public class AuthorRowMapper implements ParameterizedRowMapper<Author>{

	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Author author=new Author();
		
		author.setId(rs.getInt("ID"));
		author.setName(rs.getString("NAME"));
		author.setAuthor_div(rs.getInt("AUTHOR_DIV"));
		
		return author;
	}

	

}
