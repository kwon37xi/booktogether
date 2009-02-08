package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserPw;

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
	 * @param startpage	보여줄 시작 줄번호
	 * @param pageSize	시작줄번호로 부터 몇개
	 * @return
	 */
	public List<User> getListUser(int startpage, int pageSize);	
	
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
	 * 사용자 존재여부(탈퇴된사람 제외)
	 * @return 사용자 도메인
	 */
	public User isExistUser(String user_id);
	
	
	/**
	 * 사용자 인증번호 조회
	 * @return 사용자 id
	 */
	public UserPw getUserPw(int id);	
	
	/**
	 * 사용자 인증 번호 등록
	 * @param userPw
	 * @return
	 */
	public int insertUserPw(UserPw userPw);
	
	/**
	 * 사용자 인증 번호 수정
	 * @param userPw
	 * @return
	 */
	public int modifyUserPw(UserPw userPw);
	
	/**
	 * 사용자 ID찾기
	 * @param user 찾고자 하는 사용자 정보
	 * @return 사용자 ID
	 */
	public String findID(User user);
	
	/**
	 * 사용자 비밀번호 찾기
	 * @param user 찾고자 하는 사용자 정보
	 * @return 사용자 정보
	 */
	public User findPW(User user);
}
