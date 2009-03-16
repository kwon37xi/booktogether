package com.google.code.booktogether.service;

import java.util.List;

import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;

public interface BlogService {
	
	public List<UserBlog> getListUserBlog(Integer userIdNum);
	
	public boolean deleteUserBlog(Integer blogIdNum);
	
	public boolean validUserBlog(UserBlog userBlog);
	
	public UserBlog getUserBlog(Integer userIdNum);
	
	public boolean insertReviewBlogPost(ReviewBlogPost reviewBlogPost);
	
	public ReviewBlogPost getReviewBlogPost(ReviewBlogPost reviewBlogPost);
	

}
