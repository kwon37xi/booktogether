package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.dao.rowmapper.UserRowMapper;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.util.XmlUtil;

public class UserDaoJdbcImpl  extends SimpleJdbcDaoSupport implements UserDao{


	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}

	//책 목록에 쓰일 RowMapper
	@Resource(name="userRowMapper")
	UserRowMapper userRowMapper;
	
	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDbcount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLastNumIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getListUser(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) {
		
		String sql=XmlUtil.getInstance().getSQL("user","GET_USER_SQL");

		User user=(User)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, userRowMapper, new Object[]{id}));

		return user;
		
	}

	@Override
	public int insertUser(User user) {
		
		String sql=XmlUtil.getInstance().getSQL("user","INSERT_USER_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{user.getUser_id(),user.getEmail(),user.getNickname(),user.getName(),user.getPw()});

		return count;
		
	}

	@Override
	public int modifyUser(User user) {
		
		String sql=XmlUtil.getInstance().getSQL("user","MODIFY_USER_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{user.getEmail(),user.getNickname(),user.getName(),user.getPw(),user.getId()});

		return count;
		
	}

}
