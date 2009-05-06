package com.google.code.booktogether.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.GoodWriterDao;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.GoodWriter;

@Repository("goodWriterJdbcDao")
public class GoodWriteDaoJdbcImpl extends SimpleJdbcDaoSupport implements
		GoodWriterDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int deleteGoodWriter(GoodWriter goodwriter) {

		String sql = sqlparser.getSQL("goodwrite", "DELETE_GOODWRITER_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { goodwriter.getIdNum() });
	}

	@Override
	public GoodWriter getGoodWriter() {
		
		String sql = sqlparser.getSQL("goodwriter", "GET_GOODWRITER_SQL");

		return (GoodWriter) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						new ParameterizedRowMapper<GoodWriter>() {

							@Override
							public GoodWriter mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								GoodWriter goodWriter = new GoodWriter();
								goodWriter.setIdNum(rs.getInt("IDNUM"));
								goodWriter
										.setInputDate(rs.getDate("INPUT_DATE"));
								goodWriter.setContent(rs.getString("CONTENT"));

								return goodWriter;
							}
						}, new Object[] {}));
	}

	@Override
	public int insertGoodWriter(GoodWriter goodwriter) {
		String sql = sqlparser.getSQL("goodwriter", "INSERT_GOODWRITER_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { goodwriter.getContent() });
	}

}
