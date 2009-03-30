package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookMarkDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.service.util.HTMLInputFilter;
import com.google.code.booktogether.web.domain.BookMark;
import com.google.code.booktogether.web.page.PageBean;

@Service("bookMarkService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookMarkServiceImpl implements BookMarkService {

	@Resource(name = "bookMarkJdbcDao")
	private BookMarkDao bookMarkJdbcDao;

	/**
	 * html 필터
	 */
	@Resource(name = "htmlInputFilter")
	private HTMLInputFilter htmlInputFilter;

	@Override
	@Transactional(readOnly = false)
	public boolean insertBookMark(BookMark bookMark) {

		// 스크립트 제거
		bookMark.setContent(htmlInputFilter.stripHTML(bookMark.getContent()));

		int count = bookMarkJdbcDao.insertBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("인용구 등록 실패");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyBookMark(BookMark bookMark) {

		// 스크립트 제거
		bookMark.setContent(htmlInputFilter.stripHTML(bookMark.getContent()));

		int count = bookMarkJdbcDao.modifyBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteBookMark(BookMark bookMark) {

		int count = bookMarkJdbcDao.deleteBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}

		return true;

	}

	@Override
	public List<BookMark> getListBookMark(Integer bookIdNum, PageBean pageBean) {

		int dbCount = bookMarkJdbcDao.getDbCountBookMark(bookIdNum);

		pageBean.setDbCount(dbCount);

		return bookMarkJdbcDao.getListBookMark(bookIdNum, pageBean
				.getStartRow() - 1, pageBean.getEndRow());
	}

	@Override
	public List<BookMark> getListMyBookMark(Integer userIdNum, Integer bookInNum) {

		return bookMarkJdbcDao.getListMyBookMark(userIdNum, bookInNum);

	}

	@Override
	@Transactional(readOnly = false)
	public String modifyVibe(BookMark bookMark) {

		int count = bookMarkJdbcDao.isExistVibe(bookMark.getIdNum(), bookMark
				.getUser().getIdNum());

		if (count == 0) {

			count = bookMarkJdbcDao.modifyVibeBookMark(bookMark);

			if (count == 1) {

				count = bookMarkJdbcDao.insertVibe(bookMark.getIdNum(),
						bookMark.getUser().getIdNum());

				if (count == 1) {
					return null;
				} else {
					throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
				}

			} else {
				throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
			}

		}

		return "이미 공감을 하셨습니다.";

	}

	@Override
	public List<BookMark> getListMyBookMark(Integer userIdNum, PageBean pageBean) {

		int dbCount = bookMarkJdbcDao.getDbCountMyBookMark(userIdNum);

		pageBean.setDbCount(dbCount);

		List<BookMark> bookMark = bookMarkJdbcDao.getListMyBookMark(userIdNum,
				pageBean.getStartRow() - 1, pageBean.getEndRow());

		return bookMark;
	}

}
