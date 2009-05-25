package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.BookReviewDao;
import com.google.code.booktogether.web.domain.BookReview;

public class BookReviewDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BookReviewDao {

	@Override
	public int insertReview(BookReview bookReview) {

		return getSqlMapClientTemplate().update(
				"BOOKREVIEWDAO.INSERT_BOOKREVIEW_SQL", bookReview.getBook());
	}

	@Override
	public int modifyReview(BookReview bookReview) {

		return getSqlMapClientTemplate().update(
				"BOOKREVIEWDAO.MODIFY_BOOKREVIEW_SQL", bookReview);
	}

	@Override
	public int deleteReview(BookReview bookReview) {

		return getSqlMapClientTemplate().update(
				"BOOKREVIEWDAO.DELETE_BOOKREVIEW_SQL", bookReview);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookReview> getListBookReview(Integer bookIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookReview>) getSqlMapClientTemplate().queryForList(

		"BOOKREVIEWDAO.LIST_BOOKREVIEW_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookReview> getListMyBookReview(Integer userIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookReview>) getSqlMapClientTemplate().queryForList(
				"BOOKREVIEWDAO.LIST_MYBOOKREVIEW_SQL", map);
	}

	@Override
	public int isExistReview(Integer bookIdNum, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("userIdNum", userIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.EXIST_MYBOOKREVIEW_SQL", map);

	}

	@Override
	public BookReview getReview(BookReview bookReview) {

		return (BookReview) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.GET_MY_BOOKREVIEW_SQL", bookReview);

	}

	@Override
	public BookReview getReview(Integer bookReviewIdNum) {

		return (BookReview) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.GET_BOOKREVIEW_SQL", bookReviewIdNum);

	}

	@Override
	public int insertRecommend(BookReview bookReview) {

		return getSqlMapClientTemplate().update(
				"BOOKREVIEWDAO.INSERT_RECOMMEND_SQL", bookReview);
	}

	@Override
	public int modifyReviewRecommend(BookReview bookReview) {

		return getSqlMapClientTemplate().update(
				"BOOKREVIEWDAO.MODIFY_REVIEWRECOMMEND_SQL", bookReview);
	}

	@Override
	public int isExistRecommend(BookReview bookReview) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.EXIST_RECOMMEND_SQL", bookReview);
	}

	@Override
	public int getDbcountMyBookReview(Integer userIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.GET_DBCOUNT_MYBOOKREVIEW_SQL", userIdNum);
	}

	@Override
	public int getDbcountBookReview(Integer bookIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKREVIEWDAO.GET_DBCOUNT_BOOKREVIEW_SQL", bookIdNum);
	}

}
