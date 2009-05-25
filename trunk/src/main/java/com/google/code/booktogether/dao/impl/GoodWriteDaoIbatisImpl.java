package com.google.code.booktogether.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.GoodWriterDao;
import com.google.code.booktogether.web.domain.GoodWriter;

public class GoodWriteDaoIbatisImpl extends SqlMapClientDaoSupport implements
		GoodWriterDao {

	@Override
	public int deleteGoodWriter(GoodWriter goodwriter) {

		return getSqlMapClientTemplate().update(
				"GOODWRITERDAO.DELETE_GOODWRITER_SQL", goodwriter.getIdNum());
	}

	@Override
	public GoodWriter getGoodWriter() {

		return (GoodWriter) getSqlMapClientTemplate().queryForObject(
				"GOODWRITERDAO.GET_GOODWRITER_SQL");
	}

	@Override
	public int insertGoodWriter(GoodWriter goodwriter) {

		return getSqlMapClientTemplate().update(
				"GOODWRITERDAO.INSERT_GOODWRITER_SQL", goodwriter.getContent());
	}

}
