package com.google.code.booktogether.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.code.booktogether.dao.TestDao;
import com.google.code.booktogether.service.TestService;
import com.google.code.booktogether.web.domain.Test;

@Service("testService")
public class TestServiceImpl implements TestService {

	private Logger log=Logger.getLogger(this.getClass());
	
	private TestDao testJdbcDao;
	
	
	@Resource(name="testJdbcDao")
	public void setTestJdbcDao(TestDao testJdbcDao) {
		this.testJdbcDao = testJdbcDao;
	}


	@Override
	public boolean insertTest(Test test) {
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		int count=testJdbcDao.insertTest(test);
		
		boolean result=(count!=1) ? false : true;
		
		if(log.isInfoEnabled()){
			log.info("끝");
		}
		
		return result;
	}



}
