package com.google.code.booktogether.dao.rowmapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.LibraryBook;

/**
 * 사용자 정보 목록화할때 사용
 * 
 * @author ParkHaeCheol
 * 
 */
public class LibraryBookRowMapper implements
		ParameterizedRowMapper<LibraryBook>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public LibraryBook mapRow(ResultSet rs, int rowNum) throws SQLException {

		LibraryBook libraryBook = new LibraryBook();

		libraryBook.setIdNum(rs.getInt("IDNUM"));
		libraryBook.setReadDate(rs.getDate("READ_DATE"));
		libraryBook.setState(rs.getInt("STATE"));
		libraryBook.setIsPossess(rs.getInt("ISPOSSESS"));

		libraryBook.getLibrary().setIdNum(rs.getInt("LIDNUM"));

		libraryBook.getBook().setIdNum(rs.getInt("BIDNUM"));
		libraryBook.getBook().setName(rs.getString("BNAME"));
		libraryBook.getBook().setISBN10(rs.getString("ISBN10"));
		libraryBook.getBook().setISBN13(rs.getString("ISBN13"));
		libraryBook.getBook().setPublishComp(rs.getString("BCOMP"));
		libraryBook.getBook().setPublishDate(rs.getString("BDATE"));
		libraryBook.getBook().setPrice(rs.getInt("BPRICE"));
		libraryBook.getBook().setBookCover(rs.getString("BCOVER"));
		libraryBook.getBook().setCategory(rs.getString("CATEGORY"));

		libraryBook.getBook().setAuthors(new Author[1]);

		Author author = new Author();
		author.setName(rs.getString("AUTHOR"));

		libraryBook.getBook().getAuthors()[0] = author;

		libraryBook.getLibrary().getUser().setIdNum(rs.getInt("UIDNUM"));

		return libraryBook;
	}

}
