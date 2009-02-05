package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.User;

public interface UserDao {

	/**
	 * 사용자 등록
	 * @param user 사용자 도메인
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 사용자 수정
	 * @param user 수정할 사용자 도메인
	 * @return
	 */
	public int modifyUser(User user);
	
	
	/**
	 * 사용자 삭제
	 * @param id	사용자 ID값
	 * @return
	 */
	public int deleteUser(int id);
	
	
	/**
	 * 사용자조회
	 * @param id	사용자 ID값
	 * @return
	 */
	public User getUser(int id);
	
	
	/**
	 * 사용자 목록
	 * @param startRow	보여줄 시작 줄번호
	 * @param pageSize	시작줄번호로 부터 몇개
	 * @return
	 */
	public List<User> getListUser(int startRow, int pageSize);	
	
	/**
	 * 사용자 전체 갯수
	 * @return
	 */
	public int getDbcount();	
	
	/**
	 * AutoIncrement 값 가지고 오기
	 * @return
	 */
	public int getLastNumIncrement();
	
	
	/**
	 * 사용자 정보(로그인)
	 * @return 사용자 정보
	 */
	public User valiadIdPwUser(String user_id, String pw);	
}
