package com.google.code.booktogether.dao.impl;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.BestSellersDao;

public class BestSellersDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BestSellersDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> getListBestSellers() {

		return getSqlMapClientTemplate().queryForList(
				"BESTSELLERSDAO.LIST_BESTSELLERS_SQL", "USERDAO.deleteUser");
	}

	@Override
	public int insertBestSellers(Integer bookIdNum) {

		return (Integer)getSqlMapClientTemplate()
				.queryForObject("BESTSELLERSDAO.INSERT_BESTSELLERS_SQL", bookIdNum);
	}

}
