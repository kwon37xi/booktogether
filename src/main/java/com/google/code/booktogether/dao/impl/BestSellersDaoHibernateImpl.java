package com.google.code.booktogether.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.BestSellersDao;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;

@Repository("bestSellersJdbcDao")
public class BestSellersDaoHibernateImpl extends SimpleJdbcDaoSupport implements
		BestSellersDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<Integer> getListBestSellers() {

		String sql = sqlparser.getSQL("bestsellers", "LIST_BESTSELLERS_SQL");

		return getSimpleJdbcTemplate().query(sql,
				new ParameterizedRowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Integer bidNum = rs.getInt("BIDNUM");
						return bidNum;
					}
				}, new Object[] {});
	}

	@Override
	public int insertBestSellers(Integer bookIdNum) {

		String sql = sqlparser.getSQL("bestsellers", "INSERT_BESTSELLERS_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { bookIdNum });
	}

}
