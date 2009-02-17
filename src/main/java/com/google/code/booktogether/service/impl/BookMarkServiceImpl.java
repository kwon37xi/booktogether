package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookMarkDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.web.domain.BookMark;

@Service("bookMarkService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookMarkServiceImpl implements BookMarkService {

	@Resource(name = "bookMarkJdbcDao")
	private BookMarkDao bookMarkJdbcDao;

	
	@Override
	@Transactional(readOnly = false)
	public boolean insertBookMark(BookMark bookMark) {

		int count = bookMarkJdbcDao.insertBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("인용구 등록 실패");
		} else {
			return true;
		}

	}

	
	@Override
	@Transactional(readOnly = false)
	public boolean modifyBookMark(BookMark bookMark) {

		int count = bookMarkJdbcDao.modifyBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteBookMark(BookMark bookMark) {

		int count = bookMarkJdbcDao.deleteBookMark(bookMark);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		} else {
			return true;
		}

	}

	
	
	@Override
	public List<BookMark> getListBookMark(Integer bookIdNum, Integer startPage,
			Integer endPage) {

		return bookMarkJdbcDao.getListBookMark(bookIdNum, startPage, endPage);
	}

	
	
	@Override
	public List<BookMark> getListMyBookMark(Integer userIdNum,
			Integer startPage, Integer endPage) {

		return bookMarkJdbcDao.getListMyBookMark(userIdNum, startPage, endPage);

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
					return "공감등록 성공";
				} else {
					throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
				}
				
			} else {
				throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
			}

		} else {
			return "이미 공감을 하셨습니다.";
		}

	}

}
