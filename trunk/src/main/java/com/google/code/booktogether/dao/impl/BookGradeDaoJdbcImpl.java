package com.google.code.booktogether.dao.impl;



import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.dao.rowmapper.BookGradeRowMapper;
import com.google.code.booktogether.dao.rowmapper.MyBookGradeRowMapper;
import com.google.code.booktogether.service.util.XmlUtil;
import com.google.code.booktogether.web.domain.BookGrade;

@Repository("bookGradeJdbcDao")
public class BookGradeDaoJdbcImpl extends SimpleJdbcDaoSupport implements BookGradeDao{

	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}

	//별점 RowMapper
	@Resource(name="bookGradeRowMapper")
	BookGradeRowMapper bookGradeRowMapper;
	
	
	//내가 매긴 별점 RowMapper
	@Resource(name="mybookGradeRowMapper")
	MyBookGradeRowMapper myBookGradeRowMapper;


	@Override
	public int insertGrade(BookGrade bookGrade) {
		String sql=XmlUtil.getInstance().getSQL("bookgrade","INSERT_BOOKGRADE_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{bookGrade.getBook().getId(),bookGrade.getUser().getId(),bookGrade.getGrade()});

		return count;
	}


	@Override
	public List<BookGrade> getListBookGrade(int book_id,int startPage, int endPage) {

		String sql=XmlUtil.getInstance().getSQL("bookgrade","LIST_BOOKGRADE_SQL");

		List<BookGrade> bookgradelist=getSimpleJdbcTemplate().query(sql, bookGradeRowMapper, new Object[]{book_id,startPage, endPage});

		return bookgradelist;
	}


	@Override
	public List<BookGrade> getListMyBookGrade(int user_id, int startPage, int endPage) {
		String sql=XmlUtil.getInstance().getSQL("bookgrade","LIST_MYBOOKGRADE_SQL");

		List<BookGrade> mybookgradelist=getSimpleJdbcTemplate().query(sql, myBookGradeRowMapper, new Object[]{user_id,startPage, endPage});

		return mybookgradelist;
	}



}
