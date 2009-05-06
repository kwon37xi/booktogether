package com.google.code.booktogether.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.LibraryBoardDao;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.LibraryBoard;

@Repository("libraryBoardJdbcDao")
public class LibraryBoardDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		LibraryBoardDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int deleteLibraryBook(LibraryBoard libraryBoard) {

		String sql = sqlparser
				.getSQL("libraryBoard", "DELETE_LIBRARYBOARD_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { libraryBoard.getLibraryIdNum(),
						libraryBoard.getIdNum(), libraryBoard.getWriter() });
	}

	@Override
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum) {

		String sql = sqlparser.getSQL("libraryBoard", "LIST_LIBRARYBOARD_SQL");

		List<LibraryBoard> libraryBoardList = getSimpleJdbcTemplate().query(
				sql, new ParameterizedRowMapper<LibraryBoard>() {

					@Override
					public LibraryBoard mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						LibraryBoard libraryBoard = new LibraryBoard();

						libraryBoard.setIdNum(rs.getInt("IDNUM"));
						libraryBoard.setContent(rs.getString("CONTENT"));
						libraryBoard.setInputDate(rs.getDate("INPUTDATE"));
						libraryBoard.setLibraryIdNum(rs.getInt("LIDNUM"));
						libraryBoard.setWriter(rs.getInt("WRITER"));
						libraryBoard.setWriterName(rs.getString("WNAME"));
						libraryBoard.setWriterUserId(rs.getString("WUSERID"));

						return libraryBoard;
					}
				}, new Object[] { libraryIdNum });

		return libraryBoardList;
	}

	@Override
	public int insertLibraryBook(LibraryBoard libraryBoard) {

		String sql = sqlparser
				.getSQL("libraryBoard", "INSERT_LIBRARYBOARD_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { libraryBoard.getLibraryIdNum(),
						libraryBoard.getWriter(), libraryBoard.getContent() });
	}

	@Override
	public int getDbCountLibraryBook(Integer libraryIdNum) {

		String sql = sqlparser.getSQL("libraryBoard",
				"DBCOUNT_LIBRARYBOARD_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { libraryIdNum });
	}

	@Override
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("libraryBoard",
				"LIST_LIMIT_LIBRARYBOARD_SQL");

		List<LibraryBoard> libraryBoardList = getSimpleJdbcTemplate().query(
				sql, new ParameterizedRowMapper<LibraryBoard>() {

					@Override
					public LibraryBoard mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						LibraryBoard libraryBoard = new LibraryBoard();

						libraryBoard.setIdNum(rs.getInt("IDNUM"));
						libraryBoard.setContent(rs.getString("CONTENT"));
						libraryBoard.setInputDate(rs.getDate("INPUTDATE"));
						libraryBoard.setLibraryIdNum(rs.getInt("LIDNUM"));
						libraryBoard.setWriter(rs.getInt("WRITER"));
						libraryBoard.setWriterName(rs.getString("WNAME"));
						libraryBoard.setWriterUserId(rs.getString("WUSERID"));

						return libraryBoard;
					}
				}, new Object[] { libraryIdNum, startRow, endRow });

		return libraryBoardList;
	}
}
