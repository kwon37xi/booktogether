package com.google.code.booktogether.dao.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.LibraryDao;
import com.google.code.booktogether.dao.rowmapper.LibraryRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.Library;

@Repository("userJdbcDao")
public class LibraryDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		LibraryDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Library getLibrary(String userId) {

		LibraryRowMapper rowMapper = new LibraryRowMapper();

		String sql = sqlparser.getSQL("library", "GET_LIBRARY_SQL");

		Library library = (Library) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql, rowMapper,
						new Object[] { userId }));

		return library;
	}

	@Override
	public int modifyLibrary(Library library) {

		String sql = sqlparser.getSQL("library", "MODIFY_LIBRARY_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { library.getNotice(), library.getIsOpen(),
						library.getUser().getIdNum(), library.getIdNum() });

		return count;
	}

	@Override
	public int insertLibrary(Library library) {

		String sql = sqlparser.getSQL("library", "INSERT_LIBRARY_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { library.getUser().getIdNum(),
						library.getNotice(), library.getIsOpen()});

		return count;
	}

}
