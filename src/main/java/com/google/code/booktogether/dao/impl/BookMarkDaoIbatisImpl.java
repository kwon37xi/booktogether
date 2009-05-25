package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.BookMarkDao;
import com.google.code.booktogether.web.domain.BookMark;

public class BookMarkDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BookMarkDao {

	@Override
	public int insertBookMark(BookMark bookMark) {

		return getSqlMapClientTemplate().update(
				"BOOKMARKDAO.INSERT_BOOKMARK_SQL", bookMark);
	}

	@Override
	public int modifyBookMark(BookMark bookMark) {

		return getSqlMapClientTemplate().update(
				"BOOKMARKDAO.MODIFY_BOOKMARK_SQL", bookMark);
	}

	@Override
	public int deleteBookMark(BookMark bookMark) {

		return getSqlMapClientTemplate().update(
				"BOOKMARKDAO.DELETE_BOOKMARK_SQL", bookMark);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookMark> getListBookMark(Integer bookIdNum, Integer startRow,
			Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookMark>) getSqlMapClientTemplate().queryForList(
				"BOOKMARKDAO.LIST_BOOKMARK_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookMark> getListMyBookMark(Integer userIdNum, Integer bookIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("bookIdNum", bookIdNum);

		return (List<BookMark>) getSqlMapClientTemplate().queryForList(
				"BOOKMARKDAO.LIST_MYBOOKMARK_SQL", map);
	}

	@Override
	public int isExistVibe(Integer vibeIdNum, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vibeIdNum", vibeIdNum);
		map.put("userIdNum", userIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKMARKDAO.EXIST_MYVIBE_SQL", map);

	}

	@Override
	public int insertVibe(Integer bookMarkIdNum, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookMarkIdNum", bookMarkIdNum);
		map.put("userIdNum", userIdNum);

		return getSqlMapClientTemplate().update("BOOKMARKDAO.INSERT_VIBE_SQL",
				map);
	}

	@Override
	public int modifyVibeBookMark(BookMark bookMark) {

		return getSqlMapClientTemplate().update(
				"BOOKMARKDAO.MODIFY_VIBE_BOOKMARK_SQL", bookMark);
	}

	@Override
	public int getDbCountBookMark(Integer bookIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKMARKDAO.GET_DBCOUNT_BOOKMARK_SQL", bookIdNum);
	}

	@Override
	public int getDbCountMyBookMark(Integer userIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKMARKDAO.DBCOUNT_MYBOOKMARK_SQL", userIdNum);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BookMark> getListMyBookMark(Integer userIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<BookMark>) getSqlMapClientTemplate().queryForList(
				"BOOKMARKDAO.LIST_LIMIT_MYBOOKMARK_SQL", map);
	}

}
