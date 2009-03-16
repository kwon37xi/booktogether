package com.google.code.booktogether.service.blogpost;

import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.UserBlog;

public interface BlogPost {

	/**
	 * 블로그 포스팅하기
	 * 
	 * @param user
	 * @param userBlog
	 * @param bookReview
	 * @return 포스트 번호
	 */
	public Object newPostBlog(UserBlog userBlog, BookReview bookReview);

	/**
	 * 해당 블로그 정보가 유효한지 검사
	 * 
	 * @param userBlog
	 * @return
	 */
	public boolean validBlog(UserBlog userBlog);

	/**
	 * 블로그의 카테고리 정보 조회
	 * 
	 * @param userBlog
	 * @return
	 */
	public Object[] getCategories(UserBlog userBlog);

	/**
	 * 블로그 최근 포트스 가져오기
	 * 
	 * @param userBlog
	 * @return
	 */
	public Object[] getRecentPosts(UserBlog userBlog);
	
	/**
	 * 포스팅된 블로그 수정
	 * @param userBlog
	 * @param bookReview
	 * @param postNum
	 * @return
	 */
	public boolean editPostBlog(UserBlog userBlog, BookReview bookReview,String postNum);

}