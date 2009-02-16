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

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getUser().getIdNum(),
						bookMark.getBook().getIdNum(), bookMark.getVibeNum(),
						bookMark.getPage(), bookMark.getContent() });

		return count;
	}

	@Override
	public int modifyBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "MODIFY_BOOKMARK_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getPage(), bookMark.getContent(),
						bookMark.getId(), bookMark.getUser().getIdNum(),
						bookMark.getBook().getIdNum() });

		return count;
	}

	@Override
	public int deleteBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "DELETE_BOOKMARK_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookMark.getId(),
						bookMark.getBook().getIdNum(),
						bookMark.getUser().getIdNum() });

		return count;
	}

	@Override
	public List<BookMark> getListBookMark(int book_id, int startPage,
			int endPage) {

		BookMarkRowMapper bookMarkRowMapper = new BookMarkRowMapper();

		String sql = sqlparser.getSQL("bookMark", "LIST_BOOKMARK_SQL");

		List<BookMark> bookmarklist = getSimpleJdbcTemplate()
				.query(sql, bookMarkRowMapper,
						new Object[] { book_id, startPage, endPage });

		return bookmarklist;
	}

	@Override
	public List<BookMark> getListMyBookMark(int user_id, int startPage,
			int endPage) {

		MyBookMarkRowMapper myBookMarkRowMapper = new MyBookMarkRowMapper();

		String sql = sqlparser.getSQL("bookMark", "LIST_MYBOOKMARK_SQL");

		List<BookMark> mybookmarklist = getSimpleJdbcTemplate().query(sql,
				myBookMarkRowMapper,
				new Object[] { user_id, startPage, endPage });

		return mybookmarklist;
	}

	@Override
	public int isExistVibe(int id, int user_id) {

		String sql = sqlparser.getSQL("bookMark", "EXIST_MYVIBE_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { id, user_id });

		return count;
	}

	@Override
	public int insertVibe(int bookmark_id, int user_id) {

		String sql = sqlparser.getSQL("bookMark", "INSERT_VIBE_SQL");

		int count = getSimpleJdbcTemplate().update(sql,
				new Object[] { user_id, bookmark_id });

		return count;
	}

	@Override
	public int modifyVibeBookMark(BookMark bookMark) {

		String sql = sqlparser.getSQL("bookMark", "MODIFY_VIBE_BOOKMARK_SQL");

		int count = getSimpleJdbcTemplate()
				.update(
						sql,
						new Object[] { bookMark.getId(),
								bookMark.getBook().getIdNum() });

		return count;
	}

}
