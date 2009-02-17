package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.BookReview;

/**
 * 리뷰 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class BookReviewDetailRowMapper implements
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
		bookReview.setReview(rs.getString("REVIEW"));
		bookReview.setTitle(rs.getString("TITLE"));

		bookReview.getUser().setIdNum(rs.getInt("UIDNUM"));
		bookReview.getUser().setUserId(rs.getString("USER_ID"));
		bookReview.getUser().setName(rs.getString("USER_NAME"));
		bookReview.getUser().setNickname(rs.getString("USER_NICKNAME"));

		bookReview.getBook().setIdNum(rs.getInt("BIDNUM"));

		return bookReview;
	}

}
