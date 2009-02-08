package com.google.code.booktogether.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.service.crypto.PasswordAuthenticator;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserPw;

@Service("userService")
public class UserServiceImpl implements UserService {

	//사용자 JDBC DAO DI
	@Resource(name="userJdbcDao")
	private UserDao userJdbcDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
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

		List<User> userlist=userJdbcDao.getListUser(pageBean.getStartPage()-1, pageBean.getPagesize());

		return userlist;
	}

	@Override
	public User getUser(int id) {

		User user=userJdbcDao.getUser(id);

		return user;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insertUser(User user,UserPw userPw) {

		boolean result=false;

		byte[] salt=PasswordAuthenticator.generatorSalt();
		byte[] digest=null;

		try {
			digest=PasswordAuthenticator.createPasswordDigest(userPw.getPw(), salt);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int count=userJdbcDao.insertUser(user);

		if(count!=0){

			try {

				int id=userJdbcDao.getLastNumIncrement();
				
				userPw.setUser_id(id);
				userPw.setDigest(digest);
				userPw.setSalt(salt);
				
				count=userJdbcDao.insertUserPw(userPw);

				result=(count==0) ? false : true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean modifyUser(User user,UserPw userPw) {

		boolean result=false;

		int count=userJdbcDao.modifyUser(user);
		
		if(count!=0){

			if(userPw!=null){
				
				byte[] salt=PasswordAuthenticator.generatorSalt();
				byte[] digest=null;

				try {
					digest=PasswordAuthenticator.createPasswordDigest(userPw.getPw(), salt);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				try {
					
					userPw.setUser_id(user.getId());
					userPw.setDigest(digest);
					userPw.setSalt(salt);
					
					count=userJdbcDao.modifyUserPw(userPw);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			result=(count==0) ? false : true;
			
		}

		return result;
	}

	@Override
	public User valiadIdPwUser(String user_id, String pw) {

		User user=userJdbcDao.isExistUser(user_id);
		
		if(user!=null){
			
			UserPw userPw=userJdbcDao.getUserPw(user.getId());
			
			byte[] salt=userPw.getSalt();
			byte[] hashedDigest=userPw.getDigest();
			byte[] digest=null;
			
			try {
				digest=PasswordAuthenticator.createPasswordDigest(pw, salt);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			if(Arrays.equals(digest, hashedDigest)){
				System.out.println("일치한다.");
			}else{
				System.out.println("일치하지 않는다.");
				user=null;
			}
			
		}

		return user;
	}

	
	@Override
	public String findID(User user) {
		String id=userJdbcDao.findID(user);
		return id;
	}

	
	@Override
	public String findPW(User user) {
		
		String message="";
		
		user=userJdbcDao.findPW(user);
		
		if(user!=null){
			//임시 비번 발급
			//발급 알고리즘 구현해야 되는데 아직 안함
			String temp_pw="ABJJ@*#";
			
			
			byte[] salt=PasswordAuthenticator.generatorSalt();
			byte[] digest=null;
			
			try {
				digest = PasswordAuthenticator.createPasswordDigest(temp_pw, salt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UserPw userPw=new UserPw();
			userPw.setDigest(digest);
			userPw.setSalt(salt);
			userPw.setUser_id(user.getId());
			
			userJdbcDao.modifyUserPw(userPw);
			
			//이메일로 전송 알고리즘 구현 해야됨
			
			message="성공적으로 전송되었습니다.";
			
		}else{
			
			message="해당 사용자가 존재하지 않습니다.";
		}
		
		return message;
	}


}
