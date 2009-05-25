package com.google.code.booktogether.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;

public class BookDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BookDao {

	// 책 조회
	@Override
	public Book getBook(Integer bookIdNum) {

		return (Book) getSqlMapClientTemplate().queryForObject(
				"BOOKDAO.GET_BOOK_ID_SQL", bookIdNum);
	}

	// 책 등록
	@Override
	public int insertBook(Book book) {

		return getSqlMapClientTemplate()
				.update("BOOKDAO.INSERT_BOOK_SQL", book);
	}

	// 지은이 등록
	@Override
	public int insertAuthor(Author author, Integer userIdNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("author", author);
		map.put("userIdNum", userIdNum);

		return getSqlMapClientTemplate().update("BOOKDAO.INSERT_AUTHOR_SQL",
				map);
	}

	// 해당책에 관련된 지은이 목록
	@Override
	@SuppressWarnings("unchecked")
	public List<Author> getAuthor(Book book) {

		return (List<Author>) getSqlMapClientTemplate().queryForList(
				"BOOKDAO.GET_AUTHORS_SQL", book.getIdNum());

	}

	// 책조회 isbn
	@Override
	public Book getBook(String isbn) {

		return (Book) getSqlMapClientTemplate().queryForObject(
				"BOOKDAO.GET_BOOK_ISBN_SQL", isbn);
	}

	@Override
	public Author getAuthor(Integer authorIdNum) {

		return (Author) getSqlMapClientTemplate().queryForObject(
				"BOOKDAO.GET_AUTHOR_SQL", authorIdNum);

	}

	@Override
	public int getLastNumIncrement() {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKDAO.GET_LAST_NUM");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getListBookRefBookMark(Integer userIdNum,
			Integer startRow, Integer endRow) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdNum", userIdNum);
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		return getSqlMapClientTemplate().queryForList(
				"BOOKDAO.LIST_BOOK_REF_BOOKMARK_SQL", map);
	}

	@Override
	public int getDbCountBookRefBookMark(Integer userIdNum) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BOOKDAO.GET_DBCOUNT_BOOK_REF_BOOKMARK_SQL", userIdNum);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListSearchRankQuery() {

		return getSqlMapClientTemplate().queryForList(
				"BOOKDAO.LIST_SEARCH_QUERY_RANK_SQL");
	}

	@Override
	public int insertSearchRankQuery(String query) {

		return getSqlMapClientTemplate().update(
				"BOOKDAO.INSERT_QUERY_RANK_SQL", query);
	}

	@Override
	public int modifySearchRankQuery(String query) {

		return getSqlMapClientTemplate().update(
				"BOOKDAO.MODIFY_SEARCH_QUERY_RANK_SQL", query);
	}

	@Override
	public int insertBookHits(Integer bookIdNum) {

		return getSqlMapClientTemplate().update("BOOKDAO.INSERT_BOOKHITS_SQL",
				bookIdNum);

	}

	@Override
	public int modifyBookHits(Integer bookIdNum) {

		return getSqlMapClientTemplate().update("BOOKDAO.MODIFY_BOOKHITS_SQL",
				bookIdNum);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getListTopBookHits() {

		return getSqlMapClientTemplate().queryForList(
				"BOOKDAO.LIST_TOPBOOKHITS_SQL");

	}

}
