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
import com.google.code.booktogether.web.page.PageBean;

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
		}
		
		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyReview(BookReview bookReview) {

		int count = bookReviewJdbcDao.modifyReview(bookReview);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}
		
		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteReview(BookReview bookReview) {

		int count = bookReviewJdbcDao.deleteReview(bookReview);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}
		
		return true;

	}

	@Override
	public List<BookReview> getListBookReview(Integer bookIdNum,
			PageBean pageBean) {

		int dbCount = bookReviewJdbcDao.getDbcountBookReview(bookIdNum);

		pageBean.setDbCount(dbCount);

		return bookReviewJdbcDao.getListBookReview(bookIdNum, pageBean
				.getStartPage() - 1, pageBean.getEndPage());
	}

	@Override
	public List<BookReview> getListMyBookReview(Integer userIdNum,
			PageBean pageBean) {

		int dbCount = bookReviewJdbcDao.getDbcountMyBookReview(userIdNum);

		pageBean.setDbCount(dbCount);

		return bookReviewJdbcDao.getListMyBookReview(userIdNum, pageBean
				.getStartPage() - 1, pageBean.getEndPage());

	}

	@Override
	public boolean isExistReview(Integer bookIdNum, Integer userIdNum) {

		int count = bookReviewJdbcDao.isExistReview(bookIdNum, userIdNum);

		if (count == 0) {
			return false;
		}
		
		return true;

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
	@Transactional(readOnly = false)
	public String modifyReviewRecommend(BookReview bookReview) {

		String message = "추천등록을 실패하였습니다.";

		int count = bookReviewJdbcDao.isExistRecommend(bookReview);

		if (count == 0) {

			count = bookReviewJdbcDao.modifyReviewRecommend(bookReview);

			if (count != 1) {
				throw new BooktogetherException("추천수 올리기 실패");
			}

			count = bookReviewJdbcDao.insertRecommend(bookReview);

			if (count != 1) {

				throw new BooktogetherException("추천자 리스트 등록 실패");

			}

			message = "추천등록완료";

		} else {
			message = "이미 추천하셨습니다.";
		}

		return message;
	}

}
