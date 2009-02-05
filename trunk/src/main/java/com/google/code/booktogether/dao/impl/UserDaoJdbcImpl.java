package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.dao.rowmapper.UserRowMapper;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.util.XmlUtil;


@Repository("userJdbcDao")
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
		
		String sql=XmlUtil.getInstance().getSQL("user","DELETE_USER_SQL");

		int count=getSimpleJdbcTemplate().update(sql, new Object[]{id});

		return count;
	}

	@Override
	public int getDbcount() {
		
		String sql=XmlUtil.getInstance().getSQL("user","DBCOUNT_USER_SQL");

		int dbcount=getSimpleJdbcTemplate().queryForInt(sql);

		return dbcount;
		
	}

	@Override
	public int getLastNumIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getListUser(int startpage, int pageSize) {
		
		String sql=XmlUtil.getInstance().getSQL("user","LIST_USER_SQL");

		List<User> userlist=getSimpleJdbcTemplate().query(sql, userRowMapper, new Object[]{startpage,pageSize});

		return userlist;
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

	@Override
	public User valiadIdPwUser(String user_id, String pw) {
		
		String sql=XmlUtil.getInstance().getSQL("user","VALID_IDPW_USER_SQL");

		User user=(User)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, userRowMapper, new Object[]{user_id, pw}));

		return user;
	}

}
