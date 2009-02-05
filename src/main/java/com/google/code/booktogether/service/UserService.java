package com.google.code.booktogether.service;

import java.util.List;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.PageBean;

public interface UserService {

	/**
	 * 사용자 등록
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user);

	/**
	 * 사용자 수정
	 * @param user
	 * @return
	 */
	public boolean modifyUser(User user);
	
	/**
	 * 사용자 삭제
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);	
	
	
	/**
	 * 사용자 조회
	 * @param id
	 * @return
	 */
	public User getUser(int id);		
	
	
	/**
	 * 사용자 목록
	 * @param pageBean
	 * @return
	 */
	public List<User> getListUser(PageBean pageBean);
	
	
}
