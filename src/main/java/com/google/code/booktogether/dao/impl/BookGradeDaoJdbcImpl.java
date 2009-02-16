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
public class BookGradeDaoJdbcImpl extends SimpleJdbcDaoSupport implements BookGradeDao{

	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}

	@Resource(name="SqlParser")
	SqlParserXmlImpl sqlparser;


	@Override
	public int insertGrade(BookGrade bookGrade) {

		String sql=sqlparser.getSQL("bookGrade","INSERT_BOOKGRADE_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						bookGrade.getBook().getId()
						,bookGrade.getUser().getId()
						,bookGrade.getGrade()
				}
		);

		return count;
	}

	@Override
	public int modifyGrade(BookGrade bookGrade) {

		String sql=sqlparser.getSQL("bookgGade","MODIFY_BOOKGRADE_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						bookGrade.getGrade()
						,bookGrade.getId()
				}
		);

		return count;
	}

	@Override
	public int deleteGrade(BookGrade bookGrade) {

		String sql=sqlparser.getSQL("bookGrade","DELETE_BOOKGRADE_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						bookGrade.getId()
						,bookGrade.getBook().getId()
						,bookGrade.getUser().getId()
				}
		);

		return count;
	}


	@Override
	public List<BookGrade> getListBookGrade(int book_id,int startPage, int endPage) {

		BookGradeRowMapper bookGradeRowMapper=new BookGradeRowMapper();

		String sql=sqlparser.getSQL("bookGrade","LIST_BOOKGRADE_SQL");

		List<BookGrade> bookgradelist=getSimpleJdbcTemplate().query(
				sql
				, bookGradeRowMapper
				, new Object[]{
						book_id
						,startPage
						, endPage
				}
		);

		return bookgradelist;
	}


	@Override
	public List<BookGrade> getListMyBookGrade(int user_id, int startPage, int endPage) {

		MyBookGradeRowMapper myBookGradeRowMapper=new MyBookGradeRowMapper();

		String sql=sqlparser.getSQL("bookGrade","LIST_MYBOOKGRADE_SQL");

		List<BookGrade> mybookgradelist=getSimpleJdbcTemplate().query(
				sql
				, myBookGradeRowMapper
				, new Object[]{
						user_id
						,startPage
						, endPage
				}
		);

		return mybookgradelist;
	}

	@Override
	public int isExistGrade(int book_id, int user_id) {

		String sql=sqlparser.getSQL("bookGrade","EXIST_MYBOOKGRADE_SQL");

		int count=getSimpleJdbcTemplate().queryForInt(
				sql
				, new Object[]{
						book_id
						,user_id
				}
		);

		return count;
	}



}
