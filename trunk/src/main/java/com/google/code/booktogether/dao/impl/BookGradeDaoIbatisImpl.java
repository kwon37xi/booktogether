package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.web.domain.BookGrade;

public class BookGradeDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BookGradeDao {

	@Override
	public int insertGrade(BookGrade bookGrade) {

		return getSqlMapClientTemplate().update(
				"BOOKGRADEDAO.INSERT_BOOKGRADE_SQL", bookGrade);
	}

	@Override
	public int modifyGrade(BookGrade bookGrade) {

		return getSqlMapClientTemplate().update(
				"BOOKGRADEDAO.MODIFY_BOOKGRADE_SQL", bookGrade);
	}

	@Override
	public int deleteGrade(BookGrade bookGrade) {

		return getSqlMapClientTemplate().update(
				"BOOKGRADEDAO.DELETE_BOOKGRADE_SQL", bookGrade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookGrade> getListBookGrade(Integer bookIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookGrade>) getSqlMapClientTemplate().queryForList(
				"BOOKGRADEDAO.LIST_BOOKGRADE_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookGrade> getListMyBookGrade(Integer userIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookGrade>) getSqlMapClientTemplate().queryForList(
				"BOOKGRADEDAO.LIST_MYBOOKGRADE_SQL", map);
	}

	@Override
	public int isExistGrade(Integer bookIdNum, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("userIdNum", userIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKGRADEDAO.EXIST_MYBOOKGRADE_SQL", map);
	}

	@Override
	public int getDbCountBookGrade(Integer bookIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKGRADEDAO.GET_DBCOUNT_BOOKGRADE_SQL", bookIdNum);
	}

	@Override
	public int getDbCountMyBookGrade(Integer userIdNum) {

		return (Integer)getSqlMapClientTemplate().queryForObject(
				"BOOKGRADEDAO.GET_DBCOUNT_MYBOOKGRADE_SQL", userIdNum);
	}

}
