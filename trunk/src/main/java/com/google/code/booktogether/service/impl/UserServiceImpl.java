package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	//사용자 JDBC DAO DI
	@Resource(name="userJdbcDao")
	private UserDao userJdbcDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteUser(int id) {
		
		boolean result=false;
		
		int count=userJdbcDao.deleteUser(id);

		result=(count==0) ? false : true;

		return result;
	}

	@Override
	public List<User> getListUser(PageBean pageBean) {
		
		int dbcount=userJdbcDao.getDbcount();
		
		pageBean.setDbcount(dbcount);
		
		List<User> userlist=userJdbcDao.getListUser(pageBean.getStartRow()-1, pageBean.getPagesize());
		
		return userlist;
	}

	@Override
	public User getUser(int id) {

		User user=userJdbcDao.getUser(id);

		return user;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertUser(User user) {

		boolean result=false;

		int count=userJdbcDao.insertUser(user);

		result=(count==0) ? false : true;

		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean modifyUser(User user) {
		
		boolean result=false;

		int count=userJdbcDao.modifyUser(user);

		result=(count==0) ? false : true;
		
		return result;
	}

	@Override
	public User valiadIdPwUser(String user_id, String pw) {
		
		User user=userJdbcDao.valiadIdPwUser(user_id, pw);
		
		return user;
	}


}
