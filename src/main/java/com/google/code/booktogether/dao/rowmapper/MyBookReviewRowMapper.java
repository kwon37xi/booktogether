package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.BookReview;

/**
 * 별점 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class MyBookReviewRowMapper implements
		ParameterizedRowMapper<BookReview>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BookReview mapRow(ResultSet rs, int rowNum) throws SQLException {

		BookReview bookReview = new BookReview();

		bookReview.setIdNum(rs.getInt("IDNUM"));
		bookReview.setRecommend(rs.getInt("RECOMMEND"));
		bookReview.setTitle(rs.getString("TITLE"));

		bookReview.getBook().setIdNum(rs.getInt("BIDNUM"));
		bookReview.getUser().setIdNum(rs.getInt("UIDNUM"));

		return bookReview;
	}

}
