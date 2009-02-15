package com.google.code.booktogether.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.service.util.ImageResize;
import com.google.code.booktogether.service.util.PasswordAuthenticator;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService {

	//사용자 JDBC DAO DI
	@Resource(name="userJdbcDao")
	private UserDao userJdbcDao;


	@Override
	@Transactional(readOnly=false)
	public boolean deleteUser(int id) {

		boolean result=false;

		int count=userJdbcDao.deleteUser(id);

		if(count!=1){
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}else{
			result=true;
		}
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
	public User getUserDetail(int id) {

		User user=userJdbcDao.getUser(id);

		List<Zone> zonelist=userJdbcDao.getZones(id);

		Zone[] zones=new Zone[zonelist.size()];

		int i=0;

		for(Zone zone:zonelist){
			zones[i++]=zone;
		}

		user.setZones(zones);

		return user;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean insertUser(User user,UserPw userPw) {

		boolean result=false;

		byte[] salt=PasswordAuthenticator.generatorSalt();
		byte[] digest=null;

		try {
			digest=PasswordAuthenticator.createPasswordDigest(userPw.getPw(), salt);
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

		int count=userJdbcDao.insertUser(user);


		if(count!=0){

			try {

				int id=userJdbcDao.getLastNumIncrement();

				userPw.setUser_id(id);
				userPw.setDigest(digest);
				userPw.setSalt(salt);

				count=userJdbcDao.insertUserPw(userPw);

				if(count!=1){
					throw new Exception();
				}

				user.getUserAddInfo().setUser_id(id);

				count=userJdbcDao.insertUserAddInfo(user.getUserAddInfo());

				if(count!=1){
					throw new Exception();
				}

				for(Zone zone: user.getZones()){

					zone.setUser_id(id);
					count=userJdbcDao.insertZone(zone);

					if(count!=1){
						throw new Exception();
					}

				}

				result=true;

			} catch (Exception e) {
				return false;
			}
		}



		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean modifyUser(User user) {

		boolean result=false;

		try{
			int count=userJdbcDao.modifyUser(user);

			if(count!=1){
				throw new Exception();
			}

			count=userJdbcDao.modifyUserAddInfo(user.getUserAddInfo());

			if(count!=1){
				throw new Exception();
			}

			for(Zone zone: user.getZones()){

				count=userJdbcDao.insertZone(zone);

				if(count!=1){
					throw new Exception();
				}

			}

			result=true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return result;
	}

	@Override
	public User validIdPwUser(String user_id, String pw) {

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
				return null;
			}

			if(Arrays.equals(digest, hashedDigest)){
				System.out.println("일치한다.");
			}else{
				System.out.println("일치하지 않는다.");
				user=null;
			}

		}else{
			System.out.println("아이디가 없다.");
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

			//임시 비밀번호 맘들기
			String temp_pw=RandomStringUtils.random(8,true,true);

			byte[] salt=PasswordAuthenticator.generatorSalt();
			byte[] digest=null;

			try {
				digest = PasswordAuthenticator.createPasswordDigest(temp_pw, salt);
			} catch (Exception e) {
				e.printStackTrace();
				message="시스템에 문제가 발생하여 비밀번호 전송이 실패 하였습니다.";	
				return message;
			}

			UserPw userPw=new UserPw();
			userPw.setDigest(digest);
			userPw.setSalt(salt);
			userPw.setUser_id(user.getId());

			try{

				int count=userJdbcDao.modifyUserPw(userPw);

				if(count!=1){
					throw new Exception();
				}

			}catch(Exception e){
				e.printStackTrace();
				message="시스템에 문제가 발생하여 비밀번호 전송이 실패 하였습니다.";	
				return message;
			}

			//이메일로 전송 알고리즘 구현 해야됨

			message="성공적으로 전송되었습니다.";

		}else{
			message="해당 사용자가 존재하지 않습니다.";
		}

		return message;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean modifyPW(User user, String newPw) {

		boolean result=false;

		byte[] salt=PasswordAuthenticator.generatorSalt();
		byte[] digest=null;

		try {
			digest = PasswordAuthenticator.createPasswordDigest(newPw, salt);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		UserPw userPw=new UserPw();
		userPw.setDigest(digest);
		userPw.setSalt(salt);
		userPw.setUser_id(user.getId());

		try{

			int count=userJdbcDao.modifyUserPw(userPw);

			if(count!=1){
				throw new Exception();
			}else{
				result=true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean deleteZone(int zone_id, int user_id) {

		int count=userJdbcDao.deleteZone(zone_id,user_id);

		try{
			if(count!=1){
				throw new Exception();
			}else{
				return true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createImageResize(MultipartFile file, String realPath, String filename) {

		boolean result=false;

		try {

			ImageResize.createImageResize(file.getInputStream(),realPath+File.separatorChar+filename,100);

			result=true;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result;
	}

	@Override
	public boolean deleteThumnail(String realPath, String filename) {

		boolean result=false;

		try{
			File file=new File(realPath+File.separatorChar+filename);

			file.delete();

			result=true;

		}catch(Exception e){

			e.printStackTrace();

		}

		return result;
	}

	@Override
	public boolean duplicateUser_id(String user_id) {

		boolean result=false;

		try{
			int count=userJdbcDao.duplicateUser_id(user_id);

			result=(count==0) ?  true : false;

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Zipcode> getListAddr(String addr) {

		List<Zipcode> zipcodelist=userJdbcDao.getLisZipcode(addr);

		return zipcodelist;
	}


}
