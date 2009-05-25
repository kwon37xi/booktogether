package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.LibraryDao;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.domain.User;

public class LibraryDaoIbatisImpl extends SqlMapClientDaoSupport implements
		LibraryDao {

	@Override
	public Library getLibrary(String userId) {

		return (Library) getSqlMapClientTemplate().queryForObject(
				"LIBRARYDAO.GET_LIBRARY_SQL", userId);
	}

	@Override
	public Library getLibrary(Integer libraryIdNum) {

		return (Library) getSqlMapClientTemplate().queryForObject(
				"LIBRARYDAO.GET_LIBRARY_IDNUM_SQL", libraryIdNum);

	}

	@Override
	public int modifyLibrary(Library library) {

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.MODIFY_LIBRARY_SQL", library);
	}

	@Override
	public int insertLibrary(Library library) {

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.INSERT_LIBRARY_SQL", library);
	}

	@Override
	public int insertLibraryBook(LibraryBook libraryBook) {

		return getSqlMapClientTemplate().update(
				"LIBRARYBOOKDAO.INSERT_LIBRARYBOOK_SQL", libraryBook);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", libraryBook.getState());
		map.put("libraryIdNum", libraryBook.getLibrary().getIdNum());
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<LibraryBook>) getSqlMapClientTemplate().queryForList(
				"LIBRARYBOOKDAO.LIST_LIBRARYBOOK_SQL", map);
	}

	@Override
	public int duplicateLibraryBook(Integer libraryIdNum, Integer bookIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("libraryIdNum", libraryIdNum);
		map.put("bookIdNum", bookIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYBOOKDAO.GET_DUPLICATE_LIBRARYBOOK_SQL", map);
	}

	@Override
	public int modifyLibraryBook(LibraryBook libraryBook) {

		return getSqlMapClientTemplate().update(
				"LIBRARYBOOKDAO.MODIFY_LIBRARYBOOK_SQL", libraryBook);

	}

	@Override
	public LibraryBook getLibraryBook(Integer librarBookIdNum) {

		return (LibraryBook) getSqlMapClientTemplate().queryForObject(
				"LIBRARYBOOKDAO.GET_LIBRARYBOOK_SQL", librarBookIdNum);
	}

	@Override
	public int deleteLibraryBook(Integer libraryBookIdNum) {

		return getSqlMapClientTemplate().update(
				"LIBRARYBOOKDAO.DELETE_LIBRARYBOOK_SQL");
	}

	@Override
	public int insertPossessBook(PossessBook possessBook) {

		return getSqlMapClientTemplate().update(
				"POSSESSBOOKDAO.INSERT_POSSESSBOOK_SQL", possessBook);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PossessBook> getListPossessBook(String userId,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<PossessBook>) getSqlMapClientTemplate().queryForList(
				"POSSESSBOOKDAO.LIST_POSSESSBOOK_SQL", map);
	}

	@Override
	public int modifyPossessBook(PossessBook possessBook) {

		return getSqlMapClientTemplate().update(
				"POSSESSBOOKDAO.MODIFY_POSSESSBOOK_SQL", possessBook);
	}

	@Override
	public PossessBook getPossessBook(Integer possessBookIdNum) {

		return (PossessBook) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.GET_POSSESSBOOK_SQL", possessBookIdNum);
	}

	@Override
	public int deletePossessBook(Integer possessBookIdNum) {

		return getSqlMapClientTemplate().update(
				"POSSESSBOOKDAO.DELETE_POSSESSBOOK_SQL", possessBookIdNum);
	}

	@Override
	public int getDbCountLibraryBook(LibraryBook libraryBook) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYBOOKDAO.GET_DBCOUNT_LIBRARYBOOK_SQL", libraryBook);
	}

	@Override
	public int getDbCountPossessBook(String userId) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.GET_DBCOUNT_POSSESSBOOK_SQL", userId);
	}

	@Override
	public int getDbCountListZoneBook(String userId) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.GET_DBCOUNT_ZONEBOOK_SQL", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PossessBook> getListZoneBook(String userId, Integer startRow,
			Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<PossessBook>) getSqlMapClientTemplate().queryForList(
				"POSSESSBOOKDAO.GET_LIST_ZONEBOOK_SQL", map);

	}

	@Override
	public PossessBook getPossessBook(Integer bookIdNum, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("bookIdNum", bookIdNum);

		return (PossessBook) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.GET_B_U_POSSESSBOOK_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getListSearchLibrary(String query) {

		return (List<User>) getSqlMapClientTemplate().queryForList(
				"LIBRARYDAO.LIST_SEARCH_LIBRARY_SQL", query);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryBook> getListLibraryBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", "%" + bookName + "%");
		map.put("libraryIdNum", libraryIdNum);
		map.put("startRow", libraryIdNum);
		map.put("endRow", endRow);

		return (List<LibraryBook>) getSqlMapClientTemplate().queryForList(
				"LIBRARYBOOKDAO.LIST_SEARCH_LIBRARYBOOK_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PossessBook> getListPossessBook(Integer libraryIdNum,
			String bookName, Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", "%" + bookName + "%");
		map.put("libraryIdNum", libraryIdNum);
		map.put("startRow", libraryIdNum);
		map.put("endRow", endRow);

		return (List<PossessBook>) getSqlMapClientTemplate().queryForList(
				"POSSESSBOOKDAO.LIST_SEARCH_POSSESSBOOK_SQL", map);
	}

	@Override
	public int getDbCountListLibraryBook(Integer libraryIdNum, String bookName) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", "%" + bookName + "%");
		map.put("libraryIdNum", libraryIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.LIST_DBCOUNT_SEARCH_LIBRARYBOOK_SQL", map);
	}

	@Override
	public int getDbCountListPossessBook(Integer libraryIdNum, String bookName) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", "%" + bookName + "%");
		map.put("libraryIdNum", libraryIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYBOOKDAO.LIST_DBCOUNT_SEARCH_LIBRARYBOOK_SQL", map);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getLibraryRank() {

		List<User> libraryRankList = getSqlMapClientTemplate().queryForList(
				"LIBRARYDAO.LIST_RANK_LIBRARY_SQL");

		return libraryRankList;
	}

	@Override
	public int deleteInterestLibrary(Integer target, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("userIdNum", userIdNum);

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.DELETE_INTEREST_LIBRARY_SQL",
				new Object[] { target, userIdNum });

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getListInterestLibrary(Integer userIdNum) {

		List<User> interestLibraryList = (List<User>) getSqlMapClientTemplate()
				.queryForList("LIBRARYDAO.LIST_INTEREST_LIBRARY_SQL", userIdNum);

		return interestLibraryList;
	}

	@Override
	public int insertInterestLibrary(Integer target, Integer userIdNum) {

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.INSERT_INTEREST_LIBRARY_SQL",
				new Object[] { target, userIdNum });
	}

	@Override
	public int duplicateInterestLibrary(Integer target, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("userIdNum", userIdNum);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYDAO.DUPLICATE_INTEREST_LIBRARY_SQL", map);
	}

	@Override
	public int deleteLibraryRank() {

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.DELETE_LIBRARYRANK_SQL");
	}

	@Override
	public int refeshLibraryRank() {

		return getSqlMapClientTemplate().update(
				"LIBRARYDAO.INSERT_RANK_LIBRARY_SQL");
	}

	@Override
	public int getDbCountPossessBook(Integer bookIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"POSSESSBOOKDAO.DBCOUNT_POSSESSBOOK_BOOKIDNUM_SQL", bookIdNum);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PossessBook> getListPossessBook(Integer bookIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookIdNum", bookIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<PossessBook>) getSqlMapClientTemplate().queryForList(
				"POSSESSBOOKDAO.LIST_POSSESSBOOK_BOOKIDNUM_SQL", map);
	}

	@Override
	public int getDbcountInterestLibrary(Integer userIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"LIBRARYDAO.DBCOUNT_INTEREST_LIBRARY_SQL",
				new Object[] { userIdNum });

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListInterestLibrary(Integer userIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return (List<User>) getSqlMapClientTemplate().queryForList(
				"LIBRARYDAO.LIST_LIMIT_INTEREST_LIBRARY_SQL", map);

	}
}
