package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.BlogDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BlogService;
import com.google.code.booktogether.service.blogpost.impl.MetaWeBlogPostImpl;
import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;

@Service("blogService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BlogServiceImpl implements BlogService {

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * blog DAO DI
	 */
	@Resource(name = "blogJdbcDao")
	private BlogDao blogDao;

	@Override
	@Transactional(readOnly = false)
	public boolean deleteUserBlog(Integer blogIdNum) {
		return false;
	}

	@Override
	public List<UserBlog> getListUserBlog(Integer userIdNum) {
		return null;
	}

	@Override
	public boolean validUserBlog(UserBlog userBlog) {

		MetaWeBlogPostImpl metaWeblogPost = new MetaWeBlogPostImpl();

		boolean result = metaWeblogPost.validBlog(userBlog);

		log.info(result);

		return result;
	}

	@Override
	public UserBlog getUserBlog(Integer userIdNum) {

		UserBlog userBlog = blogDao.getUserBlog(userIdNum);

		return userBlog;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertReviewBlogPost(ReviewBlogPost reviewBlogPost) {

		int count = blogDao.insertReviewBlogPost(reviewBlogPost);

		if (count != 1) {
			throw new BooktogetherException("블로그에 등록한 포스트 일련번호 등록 실패");
		}

		return true;

	}

	@Override
	public ReviewBlogPost getReviewBlogPost(ReviewBlogPost reviewBlogPost) {
		
		log.info(reviewBlogPost);
		
		reviewBlogPost = blogDao.getReviewBlogPost(reviewBlogPost);

		return reviewBlogPost;
	}


}
