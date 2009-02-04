package com.google.code.booktogether.dao.impl;



import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.TestDao;
import com.google.code.booktogether.web.domain.Test;

@Repository("testJdbcDao")
public class TestDaoJdbcImpl extends SimpleJdbcDaoSupport implements TestDao{

	
	private Logger log=Logger.getLogger(this.getClass());
	private static final String INSERT_TEST_SQL="INSERT INTO TEST(message) VALUES(?)";
	
	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	@Override
	public int insertTest(Test test) {
		
		if(log.isInfoEnabled()){
			log.info("시작");
		}
		
		int count=getSimpleJdbcTemplate().update(INSERT_TEST_SQL, new Object[]{test.getMessage()});
		
		if(log.isInfoEnabled()){
			log.info("종료");
		}
		
		return count;
		
	}

}
