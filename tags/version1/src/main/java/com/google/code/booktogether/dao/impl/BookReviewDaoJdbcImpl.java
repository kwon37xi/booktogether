package com.google.code.booktogether.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.code.booktogether.dao.BookReviewDao;
import com.google.code.booktogether.dao.rowmapper.BookReviewDetailRowMapper;
import com.google.code.booktogether.dao.rowmapper.BookReviewRowMapper;
import com.google.code.booktogether.dao.rowmapper.MyBookReviewRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.BookReview;

@Repository("bookReviewJdbcDao")
public class BookReviewDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		BookReviewDao {

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Override
	public int insertReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "INSERT_BOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getBook().getIdNum(),
						bookReview.getUser().getIdNum(),
						bookReview.getRecommend(), bookReview.getTitle(),
						bookReview.getReview() });
	}

	@Override
	public int modifyReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "MODIFY_BOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getTitle(), bookReview.getReview(),
						bookReview.getIdNum(), bookReview.getUser().getIdNum(),
						bookReview.getBook().getIdNum() });
	}

	@Override
	public int deleteReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "DELETE_BOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getBook().getIdNum(),
						bookReview.getUser().getIdNum() });

	}

	@Override
	public List<BookReview> getListBookReview(Integer bookIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("bookReview", "LIST_BOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().query(sql, new BookReviewRowMapper(),
				new Object[] { bookIdNum, startRow, endRow });
	}

	@Override
	public List<BookReview> getListMyBookReview(Integer userIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("bookReview", "LIST_MYBOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().query(sql, new MyBookReviewRowMapper(),
				new Object[] { userIdNum, startRow, endRow });
	}

	@Override
	public int isExistReview(Integer bookIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("bookReview", "EXIST_MYBOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { bookIdNum, userIdNum });

	}

	@Override
	public BookReview getReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "GET_MY_BOOKREVIEW_SQL");

		return (BookReview) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(
						sql,
						new BookReviewDetailRowMapper(),
						new Object[] { bookReview.getBook().getIdNum(),
								bookReview.getUser().getIdNum() }));

	}

	@Override
	public BookReview getReview(Integer bookReviewIdNum) {

		String sql = sqlparser.getSQL("bookReview", "GET_BOOKREVIEW_SQL");

		return (BookReview) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new BookReviewDetailRowMapper(),
						new Object[] { bookReviewIdNum }));

	}

	@Override
	public int insertRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "INSERT_RECOMMEND_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getUser().getIdNum(),
						bookReview.getIdNum() });
	}

	@Override
	public int modifyReviewRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview",
				"MODIFY_REVIEWRECOMMEND_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getIdNum(),
						bookReview.getBook().getIdNum() });
	}

	@Override
	public int isExistRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "EXIST_RECOMMEND_SQL");

		return getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { bookReview.getIdNum(),
						bookReview.getUser().getIdNum() });
	}

	@Override
	public int getDbcountMyBookReview(Integer userIdNum) {

		String sql = sqlparser.getSQL("bookReview",
				"GET_DBCOUNT_MYBOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { userIdNum });
	}

	@Override
	public int getDbcountBookReview(Integer bookIdNum) {

		String sql = sqlparser.getSQL("bookReview",
				"GET_DBCOUNT_BOOKREVIEW_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { bookIdNum });
	}

}
