package com.google.code.booktogether.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.GoodWriterDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.GoodWriterService;
import com.google.code.booktogether.web.domain.GoodWriter;

@Service("goodWriterService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GoodWriterServiceImpl implements GoodWriterService {

	/**
	 * 사용자 JDBC DAO DI
	 */
	@Resource(name = "goodWriterJdbcDao")
	private GoodWriterDao goodWriterJdbcDao;

	
	@Override
	public GoodWriter getGoodWriter() {
		GoodWriter goodWriter=goodWriterJdbcDao.getGoodWriter();
		return goodWriter;
	}

	
	@Override
	@Transactional(readOnly = false)
	public boolean insertGoodWriter(GoodWriter goodWriter) {

		int count=goodWriterJdbcDao.insertGoodWriter(goodWriter);
		
		if(count!=1){
			throw new BooktogetherException("좋은 글 등록시 에러 발생");
		}
		
		return true;
	}



}
