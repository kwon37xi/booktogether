package com.google.code.booktogether.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.RecoBookDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.service.RecoBookService;
import com.google.code.booktogether.web.domain.Book;

@Service("recoBookService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class RecoBookServiceImpl implements RecoBookService {

	/**
	 * recoBookDao JDBC DAO DI
	 */
	@Resource(name = "recoBookJdbcDao")
	private RecoBookDao recoBookDao;

	/**
	 * 사용자 JDBC DAO DI
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	@Override
	public List<Book> getListRecoBook() {

		List<Integer> recoBookIdNumList = recoBookDao.getListRecoBook();

		List<Book> recoBookList = new ArrayList<Book>();

		for (int i = 0; i < recoBookIdNumList.size(); i++) {

			Integer bookIdNum = recoBookIdNumList.get(i);

			recoBookList.add(bookService.getBook(bookIdNum));

		}

		return recoBookList;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertRecoBook(Integer bookIdNum) {

		int count = recoBookDao.insertRecoBook(bookIdNum);

		if (count != 1) {
			throw new BooktogetherException("추천책  등록 실패");
		}

		return true;
	}

}
