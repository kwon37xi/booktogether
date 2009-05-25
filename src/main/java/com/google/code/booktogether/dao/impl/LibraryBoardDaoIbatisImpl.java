package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.google.code.booktogether.dao.LibraryBoardDao;
import com.google.code.booktogether.web.domain.LibraryBoard;

public class LibraryBoardDaoIbatisImpl extends SqlMapClientDaoSupport implements
		LibraryBoardDao {

	@Override
	public int deleteLibraryBook(LibraryBoard libraryBoard) {

		return getSqlMapClientTemplate().update(
				"LIBRARYBOARDDAO.DELETE_LIBRARYBOARD_SQL", libraryBoard);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum) {

		return (List<LibraryBoard>) getSqlMapClientTemplate().queryForList(
				"LIBRARYBOARDDAO.LIST_LIBRARYBOARD_SQL", libraryIdNum);
	}

	@Override
	public int insertLibraryBook(LibraryBoard libraryBoard) {

		return getSqlMapClientTemplate().update(
				"LIBRARYBOARDDAO.INSERT_LIBRARYBOARD_SQL", libraryBoard);
	}

	@Override
	public int getDbCountLibraryBook(Integer libraryIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYBOARDDAO.DBCOUNT_LIBRARYBOARD_SQL", libraryIdNum);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("libraryIdNum", libraryIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<LibraryBoard>) getSqlMapClientTemplate().queryForList(
				"LIBRARYBOARDDAO.LIST_LIMIT_LIBRARYBOARD_SQL", map);
	}
}
