package com.google.code.booktogether.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BestSellersDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BestSellersService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.web.domain.Book;

@Service("bestSellersService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BestSellersServiceImpl implements BestSellersService {

	/**
	 * 사용자 JDBC DAO DI
	 */
	@Resource(name = "bestSellersJdbcDao")
	private BestSellersDao bestSellersDao;

	/**
	 * 사용자 JDBC DAO DI
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	@Override
	public List<Book> getListBestSellers() {

		List<Integer> bestSellersBookIdNumList = bestSellersDao
				.getListBestSellers();

		List<Book> bestSellerList = new ArrayList<Book>();

		for (int i = 0; i < bestSellersBookIdNumList.size(); i++) {

			Integer bookIdNum = bestSellersBookIdNumList.get(i);

			bestSellerList.add(bookService.getBook(bookIdNum));

		}

		return bestSellerList;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertBestSeller(Integer bookIdNum) {

		int count = bestSellersDao.insertBestSellers(bookIdNum);

		if (count != 1) {
			throw new BooktogetherException("베스트 셀러 등록");
		}

		return true;
	}

}
