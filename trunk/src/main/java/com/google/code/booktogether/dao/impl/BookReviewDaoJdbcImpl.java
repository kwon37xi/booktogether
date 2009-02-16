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

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getBook().getIdNum(),
						bookReview.getUser().getIdNum(),
						bookReview.getRecommend(), bookReview.getTitle(),
						bookReview.getReview() });

		return count;
	}

	@Override
	public int modifyReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "MODIFY_BOOKREVIEW_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getTitle(), bookReview.getReview(),
						bookReview.getId(), bookReview.getUser().getIdNum(),
						bookReview.getBook().getIdNum() });

		return count;
	}

	@Override
	public int deleteReview(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "DELETE_BOOKREVIEW_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getBook().getIdNum(),
						bookReview.getUser().getIdNum() });

		return count;
	}

	@Override
	public List<BookReview> getListBookReview(int book_id, int startPage,
			int endPage) {

		BookReviewRowMapper bookReviewRowMapper = new BookReviewRowMapper();

		String sql = sqlparser.getSQL("bookReview", "LIST_BOOKREVIEW_SQL");

		List<BookReview> bookReviewlist = getSimpleJdbcTemplate().query(sql,
				bookReviewRowMapper,
				new Object[] { book_id, startPage, endPage });

		return bookReviewlist;
	}

	@Override
	public List<BookReview> getListMyBookReview(int user_id, int startPage,
			int endPage) {

		MyBookReviewRowMapper myBookReviewRowMapper = new MyBookReviewRowMapper();

		String sql = sqlparser.getSQL("bookReview", "LIST_MYBOOKREVIEW_SQL");

		List<BookReview> mybookReviewlist = getSimpleJdbcTemplate().query(sql,
				myBookReviewRowMapper,
				new Object[] { user_id, startPage, endPage });

		return mybookReviewlist;
	}

	@Override
	public int isExistReview(int book_id, int user_id) {

		String sql = sqlparser.getSQL("bookReview", "EXIST_MYBOOKREVIEW_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { book_id, user_id });

		return count;
	}

	@Override
	public BookReview getReview(BookReview bookReview) {

		BookReviewDetailRowMapper bookReviewDetailRowMapper = new BookReviewDetailRowMapper();

		String sql = sqlparser.getSQL("bookReview", "GET_MY_BOOKREVIEW_SQL");

		bookReview = (BookReview) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(
						sql,
						bookReviewDetailRowMapper,
						new Object[] { bookReview.getBook().getIdNum(),
								bookReview.getUser().getIdNum() }));

		return bookReview;

	}

	@Override
	public BookReview getReview(int id) {

		BookReviewDetailRowMapper bookReviewDetailRowMapper = new BookReviewDetailRowMapper();

		String sql = sqlparser.getSQL("bookReview", "GET_BOOKREVIEW_SQL");

		BookReview bookReview = (BookReview) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						bookReviewDetailRowMapper, new Object[] { id }));

		return bookReview;

	}

	@Override
	public int insertRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "INSERT_RECOMMEND_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getUser().getIdNum(),
						bookReview.getId() });
		return count;
	}

	@Override
	public int modifyReviewRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview",
				"MODIFY_REVIEWRECOMMEND_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookReview.getId(),
						bookReview.getBook().getIdNum() });
		return count;
	}

	@Override
	public int isExistRecommend(BookReview bookReview) {

		String sql = sqlparser.getSQL("bookReview", "EXIST_RECOMMEND_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(
				sql,
				new Object[] { bookReview.getId(),
						bookReview.getUser().getIdNum() });
		return count;
	}

}
