package com.google.code.booktogether.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.dao.rowmapper.AuthorRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookSimpleRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

@Repository("bookJdbcDao")
public class BookDaoIbatisImpl extends SqlMapClientDaoSupport implements BookDao {

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	// 책 조회
	@Override
	public Book getBook(Integer bookIdNum) {

		String sql = sqlparser.getSQL("book", "GET_BOOK_ID_SQL");

		return (Book) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new BookRowMapper(), new Object[] { bookIdNum }));
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

		String sql = sqlparser.getSQL("book", "GET_AUTHORS_SQL");

		return getSimpleJdbcTemplate().query(sql, new AuthorRowMapper(),
				new Object[] { book.getIdNum() });
	}

	// 책조회 isbn
	@Override
	public Book getBook(String isbn) {

		String sql = sqlparser.getSQL("book", "GET_BOOK_ISBN_SQL");

		return (Book) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new BookRowMapper(), new Object[] { isbn, isbn }));
	}

	@Override
	public Author getAuthor(Integer authorIdNum) {

		String sql = sqlparser.getSQL("book", "GET_AUTHOR_SQL");

		return (Author) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new AuthorRowMapper(), new Object[] { authorIdNum }));

	}

	@Override
	public int getLastNumIncrement() {

		String sql = sqlparser.getSQL("book", "GET_LAST_NUM");

		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	@Override
	public List<Book> getListBookRefBookMark(Integer userIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("book", "LIST_BOOK_REF_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().query(sql, new BookSimpleRowMapper(),
				new Object[] { userIdNum, startRow, endRow });
	}

	@Override
	public int getDbCountBookRefBookMark(Integer userIdNum) {

		String sql = sqlparser.getSQL("book",
				"GET_DBCOUNT_BOOK_REF_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { userIdNum });

	}

	@Override
	public List<String> getListSearchRankQuery() {
		String sql = sqlparser.getSQL("book", "LIST_SEARCH_QUERY_RANK_SQL");

		List<String> libraryBoardList = getSimpleJdbcTemplate().query(sql,
				new ParameterizedRowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("QUERY");
					}
				}, new Object[] {});

		return libraryBoardList;
	}

	@Override
	public int insertSearchRankQuery(String query) {

		String sql = sqlparser.getSQL("book", "INSERT_QUERY_RANK_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { query });
	}

	@Override
	public int modifySearchRankQuery(String query) {

		String sql = sqlparser.getSQL("book", "MODIFY_SEARCH_QUERY_RANK_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { query });
	}

	@Override
	public int insertBookHits(Integer bookIdNum) {

		String sql = sqlparser.getSQL("book", "INSERT_BOOKHITS_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { bookIdNum });

	}

	@Override
	public int modifyBookHits(Integer bookIdNum) {
		
		String sql = sqlparser.getSQL("book", "MODIFY_BOOKHITS_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { bookIdNum });

	}

	@Override
	public List<Book> getListTopBookHits() {

		String sql = sqlparser.getSQL("book", "LIST_TOPBOOKHITS_SQL");

		return getSimpleJdbcTemplate().query(sql,
				new ParameterizedRowMapper<Book>() {

					@Override
					public Book mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						
						Book book=new Book();
						
						book.setIdNum(rs.getInt("IDNUM"));
						book.setISBN10(rs.getString("ISBN10"));
						book.setISBN13(rs.getString("ISBN13"));
						book.setName(rs.getString("NAME"));
						
						return book;
					}
					
				}, new Object[] {});

	}

}
