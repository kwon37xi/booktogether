package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookReviewDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.web.domain.BookReview;

@Service("bookReviewService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookReviewServiceImpl implements BookReviewService {

	@Resource(name = "bookReviewJdbcDao")
	private BookReviewDao bookReviewJdbcDao;

	@Override
	@Transactional(readOnly = false)
	public boolean insertReview(BookReview bookReview) {

		int count = bookReviewJdbcDao.insertReview(bookReview);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyReview(BookReview bookReview) {

		int count = bookReviewJdbcDao.modifyReview(bookReview);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteReview(BookReview bookReview) {

		int count = bookReviewJdbcDao.deleteReview(bookReview);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		} else {
			return true;
		}

	}

	@Override
	public List<BookReview> getListBookReview(Integer bookIdNum,
			Integer startPage, Integer endPage) {

		return bookReviewJdbcDao.getListBookReview(bookIdNum, startPage,
				endPage);
	}

	@Override
	public List<BookReview> getListMyBookReview(Integer userIdNum,
			Integer startPage, Integer endPage) {

		return bookReviewJdbcDao.getListMyBookReview(userIdNum, startPage,
				endPage);

	}

	@Override
	public boolean isExistReview(Integer bookIdNum, Integer userIdNum) {

		int count = bookReviewJdbcDao.isExistReview(bookIdNum, userIdNum);

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public BookReview getReview(BookReview bookReview) {

		return bookReviewJdbcDao.getReview(bookReview);

	}

	@Override
	public BookReview getReview(Integer reviewIdNum) {

		return bookReviewJdbcDao.getReview(reviewIdNum);

	}

	@Override
	public String modifyReviewRecommend(BookReview bookReview) {

		String message = "추천등록을 실패하였습니다.";

		int count = bookReviewJdbcDao.isExistRecommend(bookReview);

		if (count == 0) {

			count = bookReviewJdbcDao.modifyReviewRecommend(bookReview);

			if (count != 1) {
				throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
			}

			count = bookReviewJdbcDao.insertRecommend(bookReview);

			if (count != 1) {
				
				throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
				
			} else {
				
				message = "추천등록완료";
				
			}
			
		} else {
			message = "이미 추천하셨습니다.";
		}

		return message;
	}

}
