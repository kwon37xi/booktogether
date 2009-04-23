package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.dao.rowmapper.BookGradeRowMapper;
import com.google.code.booktogether.dao.rowmapper.MyBookGradeRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.BookGrade;

@Repository("bookGradeJdbcDao")
public class BookGradeDaoHibernateImpl extends SimpleJdbcDaoSupport implements
		BookGradeDao {

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Override
	public int insertGrade(BookGrade bookGrade) {

		String sql = sqlparser.getSQL("bookGrade", "INSERT_BOOKGRADE_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookGrade.getBook().getIdNum(),
						bookGrade.getUser().getIdNum(), bookGrade.getGrade() });
	}

	@Override
	public int modifyGrade(BookGrade bookGrade) {

		String sql = sqlparser.getSQL("bookgGade", "MODIFY_BOOKGRADE_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { bookGrade.getGrade(), bookGrade.getIdNum() });
	}

	@Override
	public int deleteGrade(BookGrade bookGrade) {

		String sql = sqlparser.getSQL("bookGrade", "DELETE_BOOKGRADE_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { bookGrade.getIdNum(),
						bookGrade.getBook().getIdNum(),
						bookGrade.getUser().getIdNum() });
	}

	@Override
	public List<BookGrade> getListBookGrade(Integer bookIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("bookGrade", "LIST_BOOKGRADE_SQL");

		return getSimpleJdbcTemplate().query(sql, new BookGradeRowMapper(),
				new Object[] { bookIdNum, startRow, endRow });
	}

	@Override
	public List<BookGrade> getListMyBookGrade(Integer userIdNum,
			Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("bookGrade", "LIST_MYBOOKGRADE_SQL");

		return getSimpleJdbcTemplate().query(sql, new MyBookGradeRowMapper(),
				new Object[] { userIdNum, startRow, endRow });
	}

	@Override
	public int isExistGrade(Integer bookIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("bookGrade", "EXIST_MYBOOKGRADE_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { bookIdNum, userIdNum });
	}

	@Override
	public int getDbCountBookGrade(Integer bookIdNum) {
		
		String sql = sqlparser.getSQL("bookGrade", "GET_DBCOUNT_BOOKGRADE_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { bookIdNum });
	}

	@Override
	public int getDbCountMyBookGrade(Integer userIdNum) {
		
		String sql = sqlparser.getSQL("bookGrade",
				"GET_DBCOUNT_MYBOOKGRADE_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { userIdNum });
	}

}
