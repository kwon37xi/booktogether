package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.web.domain.BookGrade;
import com.google.code.booktogether.web.page.PageBean;

@Service("bookGradeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BookGradeServiceImpl implements BookGradeService {

	@Resource(name = "bookGradeJdbcDao")
	private BookGradeDao bookGradeJdbcDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean insertGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.insertGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("별점 등록 실패");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.modifyGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("별점 수정 실패");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteGrade(BookGrade bookGrade) {

		int count = bookGradeJdbcDao.deleteGrade(bookGrade);

		if (count != 1) {
			throw new BooktogetherException("별점 삭제 실패");
		}
		return true;

	}

	@Override
	public List<BookGrade> getListBookGrade(Integer bookIdNum, PageBean pageBean) {

		pageBean.setDbCount(bookGradeJdbcDao.getDbCountBookGrade(bookIdNum));

		return bookGradeJdbcDao.getListBookGrade(bookIdNum, pageBean
				.getStartRow() - 1, pageBean.getEndRow());
	}

	@Override
	public List<BookGrade> getListMyBookGrade(Integer userIdNum,
			PageBean pageBean) {

		pageBean.setDbCount(bookGradeJdbcDao.getDbCountMyBookGrade(userIdNum));

		return bookGradeJdbcDao.getListMyBookGrade(userIdNum, pageBean
				.getStartRow() - 1, pageBean.getEndRow());

	}

	@Override
	public boolean isExistGrade(Integer bookIdNum, Integer userIdNum) {

		int count = bookGradeJdbcDao.isExistGrade(bookIdNum, userIdNum);

		if (count == 0) {
			return false;
		}

		return true;

	}

}
