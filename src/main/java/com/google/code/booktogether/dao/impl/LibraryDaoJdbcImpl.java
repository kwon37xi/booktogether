package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.LibraryDao;
import com.google.code.booktogether.dao.rowmapper.LibraryBookRowMapper;
import com.google.code.booktogether.dao.rowmapper.LibraryRowMapper;
import com.google.code.booktogether.dao.rowmapper.PossessBookRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;

@Repository("libraryJdbcDao")
public class LibraryDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		LibraryDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Library getLibrary(String userId) {

		String sql = sqlparser.getSQL("library", "GET_LIBRARY_SQL");

		return (Library) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new LibraryRowMapper(), new Object[] { userId }));
	}

	@Override
	public int modifyLibrary(Library library) {

		String sql = sqlparser.getSQL("library", "MODIFY_LIBRARY_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { library.getNotice(), library.getIsOpen(),
						library.getUser().getIdNum(), library.getIdNum() });
	}

	@Override
	public int insertLibrary(Library library) {

		String sql = sqlparser.getSQL("library", "INSERT_LIBRARY_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { library.getUser().getIdNum(),
						library.getNotice(), library.getIsOpen() });
	}

	@Override
	public int insertLibraryBook(LibraryBook libraryBook) {

		String sql = sqlparser.getSQL("library", "INSERT_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { libraryBook.getBook().getIdNum(),
						libraryBook.getLibrary().getIdNum(),
						libraryBook.getReadDate(), libraryBook.getState(),
						libraryBook.getIsPossess() });
	}

	@Override
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startPage, Integer endPage) {

		String sql = sqlparser.getSQL("library", "LIST_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate()
				.query(
						sql,
						new LibraryBookRowMapper(),
						new Object[] { libraryBook.getState(),
								libraryBook.getLibrary().getIdNum(), startPage,
								endPage });
	}

	@Override
	public int duplicateLibraryBook(Integer libraryIdNum, Integer bookIdNum) {

		String sql = sqlparser.getSQL("library",
				"GET_DUPLICATE_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { libraryIdNum, bookIdNum });
	}

	@Override
	public int modifyLibraryBook(LibraryBook libraryBook) {

		String sql = sqlparser.getSQL("library", "MODIFY_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { libraryBook.getState(),
						libraryBook.getReadDate(), libraryBook.getIsPossess(),
						libraryBook.getLibrary().getIdNum(),
						libraryBook.getBook().getIdNum(),
						libraryBook.getIdNum() });

	}

	@Override
	public LibraryBook getLibraryBook(Integer librarBookIdNum) {

		String sql = sqlparser.getSQL("library", "GET_LIBRARYBOOK_SQL");

		return (LibraryBook) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new LibraryBookRowMapper(),
						new Object[] { librarBookIdNum }));
	}

	@Override
	public int deleteLibraryBook(Integer libraryBookIdNum) {

		String sql = sqlparser.getSQL("library", "DELETE_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { libraryBookIdNum });
	}

	@Override
	public int insertPossessBook(PossessBook possessBook) {

		String sql = sqlparser.getSQL("library", "INSERT_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { possessBook.getBook().getIdNum(),
						possessBook.getUser().getIdNum(),
						possessBook.getPurchaseDate(),
						possessBook.getPurchasePrice(),
						possessBook.getBeginRead(), possessBook.getEndRead(),
						possessBook.getQuality(), possessBook.getState() });
	}

	@Override
	public List<PossessBook> getListPossessBook(String userId,
			Integer startPage, Integer endPage) {

		String sql = sqlparser.getSQL("library", "LIST_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().query(sql, new PossessBookRowMapper(),
				new Object[] { userId, startPage, endPage });
	}

	@Override
	public int modifyPossessBook(PossessBook possessBook) {

		String sql = sqlparser.getSQL("library", "MODIFY_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { possessBook.getPurchaseDate(),
						possessBook.getPurchasePrice(),
						possessBook.getBeginRead(), possessBook.getEndRead(),
						possessBook.getQuality(), possessBook.getState(),
						possessBook.getIdNum() });
	}

	@Override
	public PossessBook getPossessBook(Integer possessBookIdNum) {

		String sql = sqlparser.getSQL("library", "GET_POSSESSBOOK_SQL");

		return (PossessBook) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new PossessBookRowMapper(),
						new Object[] { possessBookIdNum }));
	}

	@Override
	public int deletePossessBook(Integer possessBookIdNum) {

		String sql = sqlparser.getSQL("library", "DELETE_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { possessBookIdNum });
	}

	@Override
	public int modifyLibraryBookIsPossess(Integer userIdNum,
			Integer possessBookIdNum) {

		String sql = sqlparser.getSQL("library",
				"MODIFY_ISPOSSESS_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { possessBookIdNum, userIdNum });
	}

	@Override
	public int getDbCountLibraryBook(LibraryBook libraryBook) {

		String sql = sqlparser.getSQL("library", "GET_DBCOUNT_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { libraryBook.getState(),
						libraryBook.getLibrary().getIdNum() });
	}

	@Override
	public int getDbCountPossessBook(String userId) {

		String sql = sqlparser.getSQL("library", "GET_DBCOUNT_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate()
				.queryForInt(sql, new Object[] { userId });
	}

}
