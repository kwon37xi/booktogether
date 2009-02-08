package com.google.code.booktogether.service;

import java.util.List;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.PageBean;
import com.google.code.booktogether.web.domain.UserPw;

public interface UserService {

	/**
	 * 사용자 등록
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user,UserPw userPw);

	/**
	 * 사용자 수정
	 * @param user
	 * @return
	 */
	public boolean modifyUser(User user,UserPw userPw);
	
	/**
	 * 사용자 삭제
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);	
	
	
	/**
	 * 사용자 조회(로그인)
	 * @param id
	 * @return 사용자 정보
	 */
	public User valiadIdPwUser(String user_id, String pw);
	
	
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
	
	
	/**
	 * 사용자 ID찾기
	 * @param user
	 * @return 아이디
	 */
	public String findID(User user);
	
	/**
	 * 사용자 PW찾기
	 * @param user
	 * @return 사용자 정보
	 */
	public String findPW(User user);
}
