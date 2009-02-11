package com.google.code.booktogether.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookMarkDao;
import com.google.code.booktogether.service.BookMarkService;
import com.google.code.booktogether.web.domain.BookMark;

@Service("bookMarkService")
public class BookMarkServiceImpl implements BookMarkService {


	@Resource(name="bookMarkJdbcDao")
	private BookMarkDao bookMarkJdbcDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insertBookMark(BookMark bookMark) {

		boolean result=false;

		try{

			int count=bookMarkJdbcDao.insertBookMark(bookMark);

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
	public boolean modifyBookMark(BookMark bookMark) {

		boolean result=false;

		try{

			int count=bookMarkJdbcDao.modifyBookMark(bookMark);

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
	public boolean deleteBookMark(BookMark bookMark) {

		boolean result=false;

		try{

			int count=bookMarkJdbcDao.deleteBookMark(bookMark);

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
	public List<BookMark> getListBookMark(int book_id, int startPage, int endPage) {

		List<BookMark> bookmarklist=bookMarkJdbcDao.getListBookMark(book_id,startPage, endPage);

		return bookmarklist;
	}


	@Override
	public List<BookMark> getListMyBookMark(int user_id, int startPage,int endPage) {

		List<BookMark> mybookmarklist=bookMarkJdbcDao.getListMyBookMark(user_id, startPage, endPage);

		return mybookmarklist;

	}


	@Override
	public String modifyVibe(BookMark bookMark) {

		int count=bookMarkJdbcDao.isExistVibe(bookMark.getId(),bookMark.getUser().getId());

		if(count==0){
			return "";
		}else {
			return "";
		}
		
	}

}
