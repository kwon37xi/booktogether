package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.web.domain.BookGrade;

@Service("bookGradeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookGradeServiceImpl implements BookGradeService {

	@Resource(name = "bookGradeJdbcDao")
	private BookGradeDao bookGradeJdbcDao;
	
	
	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());
	

	@Override
	@Transactional(readOnly = false)
	public boolean insertGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.insertGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("인용구 등록 실패");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.modifyGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("인용구 등록 실패");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.deleteGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("인용구 삭제 실패");
		} else {
			return true;
		}

	}

	@Override
	public List<BookGrade> getListBookGrade(Integer bookIdNum,
			Integer startPage, Integer endPage) {

		return bookGradeJdbcDao.getListBookGrade(bookIdNum, startPage, endPage);
	}

	@Override
	public List<BookGrade> getListMyBookGrade(Integer userIdNum,
			Integer startPage, Integer endPage) {

		return bookGradeJdbcDao.getListMyBookGrade(userIdNum, startPage,
				endPage);

	}

	@Override
	public boolean isExistGrade(Integer bookIdNum, Integer userIdNum) {

		int count = bookGradeJdbcDao.isExistGrade(bookIdNum, userIdNum);

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

}
