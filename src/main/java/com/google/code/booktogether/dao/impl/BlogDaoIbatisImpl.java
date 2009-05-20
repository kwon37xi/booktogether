package com.google.code.booktogether.dao.impl;

import java.util.List;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.google.code.booktogether.dao.BlogDao;
import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;

public class BlogDaoIbatisImpl extends SqlMapClientDaoSupport implements
		BlogDao {

	@Override
	public int insertBlog(UserBlog userBlog) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BLOGDAO.INSERT_BLOG_SQL", userBlog);
	}

	@Override
	public int deleteBlog(UserBlog userBlog) {
		return 0;
	}

	@Override
	public List<UserBlog> getListUserBlog(Integer userIdNum) {
		return null;
	}

	@Override
	public int isExistPostBlog(Integer userIdNum) {
		return 0;
	}

	@Override
	public int modifyBlog(UserBlog userBlog) {
		return 0;
	}

	@Override
	public UserBlog getUserBlog(Integer userIdNum) {

		return (UserBlog) getSqlMapClientTemplate().queryForObject(
				"BLOGDAO.GET_BLOG_SQL", userIdNum);
	}

	@Override
	public int insertReviewBlogPost(ReviewBlogPost reviewBlogPost) {

		return (Integer)getSqlMapClientTemplate().queryForObject(
				"BLOGDAO.INSERT_REVIEW_BLOG_POST_SQL", reviewBlogPost);
	}

	@Override
	public ReviewBlogPost getReviewBlogPost(ReviewBlogPost reviewBlogPost) {

		return (ReviewBlogPost) getSqlMapClientTemplate().queryForObject(
				"BLOGDAO.GET_REVIEW_BLOG_POST_SQL", reviewBlogPost);
	}

}
