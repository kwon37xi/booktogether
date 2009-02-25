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
import com.google.code.booktogether.dao.rowmapper.UserRowMapper;
import com.google.code.booktogether.dao.rowmapper.ZoneBookRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.domain.User;

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
	public Library getLibrary(Integer libraryIdNum) {

		String sql = sqlparser.getSQL("library", "GET_LIBRARY_IDNUM_SQL");

		return (Library) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new LibraryRowMapper(),
						new Object[] { libraryIdNum }));

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

		String sql = sqlparser.getSQL("libraryBook", "INSERT_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { libraryBook.getBook().getIdNum(),
						libraryBook.getLibrary().getIdNum(),
						libraryBook.getReadDate(), libraryBook.getState(),
						libraryBook.getIsPossess() });
	}

	@Override
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("libraryBook", "LIST_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate()
				.query(
						sql,
						new LibraryBookRowMapper(),
						new Object[] { libraryBook.getState(),
								libraryBook.getLibrary().getIdNum(), startRow,
								endRow });
	}

	@Override
	public int duplicateLibraryBook(Integer libraryIdNum, Integer bookIdNum) {

		String sql = sqlparser.getSQL("libraryBook",
				"GET_DUPLICATE_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { libraryIdNum, bookIdNum });
	}

	@Override
	public int modifyLibraryBook(LibraryBook libraryBook) {

		String sql = sqlparser.getSQL("libraryBook", "MODIFY_LIBRARYBOOK_SQL");

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

		String sql = sqlparser.getSQL("libraryBook", "GET_LIBRARYBOOK_SQL");

		return (LibraryBook) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new LibraryBookRowMapper(),
						new Object[] { librarBookIdNum }));
	}

	@Override
	public int deleteLibraryBook(Integer libraryBookIdNum) {

		String sql = sqlparser.getSQL("libraryBook", "DELETE_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { libraryBookIdNum });
	}

	@Override
	public int insertPossessBook(PossessBook possessBook) {

		String sql = sqlparser.getSQL("possessBook", "INSERT_POSSESSBOOK_SQL");

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
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("possessBook", "LIST_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().query(sql, new PossessBookRowMapper(),
				new Object[] { userId, startRow, endRow });
	}

	@Override
	public int modifyPossessBook(PossessBook possessBook) {

		String sql = sqlparser.getSQL("possessBook", "MODIFY_POSSESSBOOK_SQL");

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

		String sql = sqlparser.getSQL("possessBook", "GET_POSSESSBOOK_SQL");

		return (PossessBook) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new PossessBookRowMapper(),
						new Object[] { possessBookIdNum }));
	}

	@Override
	public int deletePossessBook(Integer possessBookIdNum) {

		String sql = sqlparser.getSQL("possessBook", "DELETE_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { possessBookIdNum });
	}

	@Override
	public int modifyLibraryBookIsPossess(Integer userIdNum,
			Integer possessBookIdNum) {

		String sql = sqlparser.getSQL("possessBook",
				"MODIFY_ISPOSSESS_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { possessBookIdNum, userIdNum });
	}

	@Override
	public int getDbCountLibraryBook(LibraryBook libraryBook) {

		String sql = sqlparser.getSQL("possessBook",
				"GET_DBCOUNT_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { libraryBook.getState(),
						libraryBook.getLibrary().getIdNum() });
	}

	@Override
	public int getDbCountPossessBook(String userId) {

		String sql = sqlparser.getSQL("possessBook",
				"GET_DBCOUNT_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate()
				.queryForInt(sql, new Object[] { userId });
	}

	@Override
	public int getDbCountListZoneBook(String userId) {

		String sql = sqlparser
				.getSQL("possessBook", "GET_DBCOUNT_ZONEBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { userId, userId });
	}

	@Override
	public List<PossessBook> getListZoneBook(String userId, Integer startRow,
			Integer endRow) {

		String sql = sqlparser.getSQL("possessBook", "GET_LIST_ZONEBOOK_SQL");

		return getSimpleJdbcTemplate().query(sql, new ZoneBookRowMapper(),
				new Object[] { userId, userId, startRow, endRow });

	}

	@Override
	public PossessBook getPossessBook(Integer bookIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("possessBook", "GET_B_U_POSSESSBOOK_SQL");

		return (PossessBook) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new PossessBookRowMapper(),
						new Object[] { userIdNum, bookIdNum }));
	}

	@Override
	public List<User> getListSearchLibrary(String userId, String name,
			String nickname) {

		String sql = sqlparser.getSQL("library", "LIST_SEARCH_LIBRARY_SQL");

		return getSimpleJdbcTemplate().query(sql, new UserRowMapper(),
				new Object[] { userId, name, nickname });
	}

	@Override
	public List<LibraryBook> getListLibraryBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("libraryBook",
				"LIST_SEARCH_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().query(
				sql,
				new LibraryBookRowMapper(),
				new Object[] { "%" + bookName + "%", libraryIdNum, startRow,
						endRow });
	}

	@Override
	public List<PossessBook> getListPossessBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("possessBook",
				"LIST_SEARCH_POSSESSBOOK_SQL");

		return getSimpleJdbcTemplate().query(
				sql,
				new PossessBookRowMapper(),
				new Object[] { "%" + bookName + "%", libraryIdNum, startRow,
						endRow });
	}

	@Override
	public int getDbCountListLibraryBook(Integer libraryIdNum, String bookName) {

		String sql = sqlparser.getSQL("libraryBook",
				"LIST_DBCOUNT_SEARCH_LIBRARYBOOK_SQL");

		System.out.println(sql);

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { "%" + bookName + "%", libraryIdNum });
	}

	@Override
	public int getDbCountListPossessBook(Integer libraryIdNum, String bookName) {

		String sql = sqlparser.getSQL("libraryBook",
				"LIST_DBCOUNT_SEARCH_LIBRARYBOOK_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { "%" + bookName + "%", libraryIdNum });
	}

}
