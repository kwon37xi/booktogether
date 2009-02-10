package com.google.code.booktogether.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BookReviewDao;
import com.google.code.booktogether.service.BookReviewService;
import com.google.code.booktogether.web.domain.BookReview;

@Service("bookReviewService")
public class BookReviewServiceImpl implements BookReviewService {


	@Resource(name="bookReviewJdbcDao")
	private BookReviewDao bookReviewJdbcDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insertReview(BookReview bookReview) {

		boolean result=false;

		try{

			int count=bookReviewJdbcDao.insertReview(bookReview);

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
	public boolean modifyReview(BookReview bookReview) {

		boolean result=false;

		try{

			int count=bookReviewJdbcDao.modifyReview(bookReview);

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
	public boolean deleteReview(BookReview bookReview) {

		boolean result=false;

		try{

			int count=bookReviewJdbcDao.deleteReview(bookReview);

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
	public List<BookReview> getListBookReview(int book_id, int startPage, int endPage) {

		List<BookReview> bookReviewlist=bookReviewJdbcDao.getListBookReview(book_id,startPage, endPage);

		return bookReviewlist;
	}


	@Override
	public List<BookReview> getListMyBookReview(int user_id, int startPage,int endPage) {

		List<BookReview> mybookReviewlist=bookReviewJdbcDao.getListMyBookReview(user_id, startPage, endPage);

		return mybookReviewlist;

	}


	@Override
	public boolean isExistReview(int book_id, int user_id) {

		int count=bookReviewJdbcDao.isExistReview(book_id,user_id);

		if(count==0){
			return false;
		}else {
			return true;
		}

	}


	@Override
	public BookReview getReview(BookReview bookReview) {

		bookReview=bookReviewJdbcDao.getReview(bookReview);

		return bookReview;
	}


	@Override
	public BookReview getReview(int id) {

		BookReview bookReview=bookReviewJdbcDao.getReview(id);

		return bookReview;
	}


	@Override
	public String modifyReviewRecommend(BookReview bookReview) {
		
		System.out.println(bookReview);

		String message="추천등록을 실패하였습니다.";
		
		try{

			int count=bookReviewJdbcDao.isExistRecommend(bookReview);

			if(count==0){

				count=bookReviewJdbcDao.modifyReviewRecommend(bookReview);

				if(count==0){
					throw new Exception();
				}else if(count!=1){
					throw new Exception();
				}

				count=bookReviewJdbcDao.insertRecommend(bookReview);

				if(count==0){
					throw new Exception();
				}else if(count!=1){
					throw new Exception();
				}else{
					message="추천등록완료";
				}
			}else{
				message="이미 추천하셨습니다.";
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return message;
	}

}
