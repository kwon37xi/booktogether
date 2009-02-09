package com.google.code.booktogether.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookGradeDao;
import com.google.code.booktogether.service.BookGradeService;
import com.google.code.booktogether.web.domain.BookGrade;

@Service("bookGradeService")
public class BookGradeServiceImpl implements BookGradeService {


	@Resource(name="bookGradeJdbcDao")
	private BookGradeDao bookGradeJdbcDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insertGrade(BookGrade bookGrade) {

		boolean result=false;

		try{

			int count=bookGradeJdbcDao.insertGrade(bookGrade);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;

	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean modifyGrade(BookGrade bookGrade) {

		boolean result=false;

		try{

			int count=bookGradeJdbcDao.modifyGrade(bookGrade);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean deleteGrade(BookGrade bookGrade) {

		boolean result=false;

		try{

			int count=bookGradeJdbcDao.deleteGrade(bookGrade);

			if(count==0){
				throw new Exception();
			}else if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return result;

	}


	@Override
	public List<BookGrade> getListBookGrade(int book_id, int startPage, int endPage) {

		List<BookGrade> bookgradelist=bookGradeJdbcDao.getListBookGrade(book_id,startPage, endPage);

		return bookgradelist;
	}


	@Override
	public List<BookGrade> getListMyBookGrade(int user_id, int startPage,int endPage) {

		List<BookGrade> mybookgradelist=bookGradeJdbcDao.getListMyBookGrade(user_id, startPage, endPage);

		return mybookgradelist;

	}


	@Override
	public boolean isExistGrade(int book_id, int user_id) {

		int count=bookGradeJdbcDao.isExistGrade(book_id,user_id);

		if(count==0){
			return false;
		}else {
			return true;
		}
		
	}

}
