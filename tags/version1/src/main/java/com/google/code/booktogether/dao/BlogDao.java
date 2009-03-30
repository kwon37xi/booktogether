package com.google.code.booktogether.dao;

import java.util.List;

import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;

public interface BlogDao {

	public int insertBlog(UserBlog userBlog);

	public int modifyBlog(UserBlog userBlog);

	public int deleteBlog(UserBlog userBlog);

	public List<UserBlog> getListUserBlog(Integer userIdNum);

	public int isExistPostBlog(Integer userIdNum);

	public UserBlog getUserBlog(Integer userIdNum);

	public int insertReviewBlogPost(ReviewBlogPost reviewBlogPost);
	
	public ReviewBlogPost getReviewBlogPost(ReviewBlogPost reviewBlogPost);

}
