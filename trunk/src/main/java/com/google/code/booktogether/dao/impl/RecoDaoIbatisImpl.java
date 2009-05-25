package com.google.code.booktogether.dao.impl;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.RecoBookDao;

public class RecoDaoIbatisImpl extends SqlMapClientDaoSupport implements
		RecoBookDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getListRecoBook() {

		return (List<Integer>)getSqlMapClientTemplate().queryForList(
				"RECOBOOKDAO.LIST_RECOBOOK_SQL");
	}

	@Override
	public int insertRecoBook(Integer bookIdNum) {

		return getSqlMapClientTemplate().update(
				"RECOBOOKDAO.INSERT_RECOBOOK_SQL", bookIdNum);
	}

}
