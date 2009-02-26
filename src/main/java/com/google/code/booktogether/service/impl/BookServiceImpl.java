package com.google.code.booktogether.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Author;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.BookOpenApiHeader;
import com.google.code.booktogether.web.openapi.impl.BookOpenApiDaumImpl;
import com.google.code.booktogether.web.page.PageBean;

@Service("bookService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookServiceImpl implements BookService {

	// 책 JDBC DAO DI
	@Resource(name = "bookJdbcDao")
	private BookDao bookJdbcDao;

	// 책 등록
	@Override
	@Transactional(readOnly = false)
	public boolean insertBook(Book book) {

		// 책정보 등록
		int count = bookJdbcDao.insertBook(book);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}

		int bookIdNum = bookJdbcDao.getLastNumIncrement();

		// 지은이 정보등록
		for (Author author : book.getAuthors()) {

			count = bookJdbcDao.insertAuthor(author, bookIdNum);

			if (count != 1) {
				throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
			}
		}

		return true;

	}

	// 책 조회
	@Override
	public Book getBook(Integer bookIdNum) {

		// 책 정보 가지고 오기
		Book book = bookJdbcDao.getBook(bookIdNum);

		if (book != null) {

			// 해당 책관련 지은이 가지고오기
			List<Author> authorList = bookJdbcDao.getAuthor(book);

			// List<Author> -> array로 변환
			book.setAuthors((Author[]) authorList.toArray(new Author[authorList
					.size()]));
		}

		return book;
	}

	@Override
	@Transactional(readOnly = false)
	public Book checkBook(String isbn) {

		// 해당 책 있는지 체크
		Book book = bookJdbcDao.getBook(isbn);

		// 해당책이 없을시
		if (book == null) {

			// OPEN API로 Book값 세팅
			book = new BookOpenApiDaumImpl().viewBook(isbn);

			// DB에 넣기
			this.insertBook(book);

			// ISBN으로 값가지고 오기
			book = bookJdbcDao.getBook(isbn);

		}

		// 지은이 정보 가지고 오기
		List<Author> authorList = bookJdbcDao.getAuthor(book);

		book.setAuthors((Author[]) authorList.toArray(new Author[authorList
				.size()]));

		return book;
	}

	@Override
	public List<Book> searchBook(String query, String searchType,
			PageBean pageBean) {

		BookOpenApiDaumImpl boadi = new BookOpenApiDaumImpl();

		List<Book> bookList = boadi.searchBook(query, searchType, pageBean
				.getPageNo());

		BookOpenApiHeader header = boadi.getHeader();

		pageBean.setDbCount(Integer.parseInt(header.getTotalCount()));

		return bookList;
	}

	@Override
	public List<Book> getListBookRefBookMark(Integer userIdNum,
			PageBean pageBean) {

		int dbCount = bookJdbcDao.getDbCountBookRefBookMark(userIdNum);

		pageBean.setDbCount(dbCount);

		List<Book> bookList = bookJdbcDao.getListBookRefBookMark(userIdNum,
				pageBean.getStartRow() - 1, pageBean.getEndRow());

		if (bookList != null) {

			for (int i = 0; i < bookList.size(); i++) {

				// 해당 책관련 지은이 가지고오기
				List<Author> authorList = bookJdbcDao
						.getAuthor(bookList.get(i));

				// List<Author> -> array로 변환
				bookList.get(i).setAuthors(
						(Author[]) authorList.toArray(new Author[authorList
								.size()]));
			}

		}

		return bookList;
	}

	@Override
	public List<String> getListSearchRankQuery() {

		List<String> searchRankQueryList = bookJdbcDao.getListSearchRankQuery();

		return searchRankQueryList;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertSearchRankQuery(String query) {

		int count = bookJdbcDao.modifySearchRankQuery(query);

		if (count == 0) {
			count = bookJdbcDao.insertSearchRankQuery(query);
		}

		if (count == 0) {
			throw new BooktogetherException("검색 단어 등록 실패");
		}

		return true;
	}
}
