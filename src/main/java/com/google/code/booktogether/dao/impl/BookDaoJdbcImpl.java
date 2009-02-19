package com.google.code.booktogether.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.dao.rowmapper.AuthorRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookSimpleRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

@Repository("bookJdbcDao")
public class BookDaoJdbcImpl extends SimpleJdbcDaoSupport implements BookDao {

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	// 책 조회
	@Override
	public Book getBook(Integer bookIdNum) {

		BookRowMapper bookRowMapper = new BookRowMapper();

		String sql = sqlparser.getSQL("book", "GET_BOOK_ID_SQL");

		return (Book) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, bookRowMapper, new Object[] { bookIdNum }));
	}

	// 책 등록
	@Override
	public int insertBook(Book book) {

		String sql = sqlparser.getSQL("book", "INSERT_BOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { book.getName(), book.getISBN10(),
						book.getISBN13(), book.getPublishComp(),
						book.getPublishDate(), book.getPrice(),
						book.getBookCover(), book.getCategory(),
						book.getDescription() });
	}

	// 지은이 등록
	@Override
	public int insertAuthor(Author author, Integer userIdNum) {

		String sql = sqlparser.getSQL("book", "INSERT_AUTHOR_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { author.getName(), author.getAuthorDiv(),
						userIdNum });
	}

	// 해당책에 관련된 지은이 목록
	@Override
	public List<Author> getAuthor(Book book) {

		AuthorRowMapper authorRowMapper = new AuthorRowMapper();

		String sql = sqlparser.getSQL("book", "GET_AUTHORS_SQL");

		return getSimpleJdbcTemplate().query(sql, authorRowMapper,
				new Object[] { book.getIdNum() });
	}

	// 책조회 isbn
	@Override
	public Book getBook(String isbn) {

		BookRowMapper bookRowMapper = new BookRowMapper();

		String sql = sqlparser.getSQL("book", "GET_BOOK_ISBN_SQL");

		return (Book) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, bookRowMapper, new Object[] { isbn, isbn }));
	}

	@Override
	public Author getAuthor(Integer authorIdNum) {

		AuthorRowMapper authorRowMapper = new AuthorRowMapper();

		String sql = sqlparser.getSQL("book", "GET_AUTHOR_SQL");

		return (Author) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, authorRowMapper, new Object[] { authorIdNum }));

	}

	@Override
	public int getLastNumIncrement() {

		String sql = sqlparser.getSQL("book", "GET_LAST_NUM");

		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	
	@Override
	public List<Book> getListBookRefBookMark(Integer userIdNum, Integer startPage,
			Integer endPage) {

		BookSimpleRowMapper rowMapper = new BookSimpleRowMapper();

		String sql = sqlparser.getSQL("book", "LIST_BOOK_REF_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().query(sql, rowMapper,
				new Object[] { userIdNum, startPage, endPage });
	}

}
