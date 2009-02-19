package com.google.code.booktogether.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.code.booktogether.dao.BookMarkDao;
import com.google.code.booktogether.dao.rowmapper.BookMarkRowMapper;
import com.google.code.booktogether.dao.rowmapper.MyBookMarkRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.BookMark;

@Repository("bookMarkJdbcDao")
public class BookMarkDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		BookMarkDao {

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Override
	public int insertBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "INSERT_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getUser().getIdNum(),
						bookMark.getBook().getIdNum(), bookMark.getVibeNum(),
						bookMark.getPage(), bookMark.getContent() });
	}

	@Override
	public int modifyBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "MODIFY_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getPage(), bookMark.getContent(),
						bookMark.getIdNum(), bookMark.getUser().getIdNum(),
						bookMark.getBook().getIdNum() });
	}

	@Override
	public int deleteBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "DELETE_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getIdNum(),
						bookMark.getBook().getIdNum(),
						bookMark.getUser().getIdNum() });
	}

	@Override
	public List<BookMark> getListBookMark(Integer bookIdNum, Integer startPage,
			Integer endPage) {

		BookMarkRowMapper rowMapper = new BookMarkRowMapper();

		String sql = sqlparser.getSQL("bookMark", "LIST_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().query(sql, rowMapper,
				new Object[] { bookIdNum, startPage, endPage });
	}

	@Override
	public List<BookMark> getListMyBookMark(Integer userIdNum, Integer bookIdNum) {

		MyBookMarkRowMapper rowMapper = new MyBookMarkRowMapper();

		String sql = sqlparser.getSQL("bookMark", "LIST_MYBOOKMARK_SQL");

		return getSimpleJdbcTemplate().query(sql, rowMapper,
				new Object[] { userIdNum, bookIdNum });
	}

	@Override
	public int isExistVibe(Integer vibeIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("bookMark", "EXIST_MYVIBE_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { vibeIdNum, userIdNum });

	}

	@Override
	public int insertVibe(Integer bookMarkIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("bookMark", "INSERT_VIBE_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { userIdNum, bookMarkIdNum });
	}

	@Override
	public int modifyVibeBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "MODIFY_VIBE_BOOKMARK_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getIdNum(),
						bookMark.getBook().getIdNum() });
	}


}
