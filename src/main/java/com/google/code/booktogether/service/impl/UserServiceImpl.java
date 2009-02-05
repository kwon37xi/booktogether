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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getListUser(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
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


}
