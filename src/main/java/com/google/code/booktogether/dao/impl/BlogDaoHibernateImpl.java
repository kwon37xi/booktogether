package com.google.code.booktogether.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.BlogDao;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.ReviewBlogPost;
import com.google.code.booktogether.web.domain.UserBlog;

@Repository("blogJdbcDao")
public class BlogDaoHibernateImpl extends SimpleJdbcDaoSupport implements BlogDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int insertBlog(UserBlog userBlog) {

		String sql = sqlparser.getSQL("blog", "INSERT_BLOG_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userBlog.getWebServer(), userBlog.getBlog(),
						userBlog.getId(), userBlog.getPw(),
						userBlog.getBlogDefault(), userBlog.getUserIdNum(),
						userBlog.getEtcInfo() });
	}

	@Override
	public int deleteBlog(UserBlog userBlog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserBlog> getListUserBlog(Integer userIdNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isExistPostBlog(Integer userIdNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBlog(UserBlog userBlog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserBlog getUserBlog(Integer userIdNum) {

		String sql = sqlparser.getSQL("blog", "GET_BLOG_SQL");

		return (UserBlog) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new ParameterizedRowMapper<UserBlog>() {

					@Override
					public UserBlog mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						UserBlog userBlog = new UserBlog();
						userBlog.setId(rs.getString("id"));
						userBlog.setBlog(rs.getString("blog"));
						userBlog.setWebServer(rs.getString("webserver"));
						userBlog.setPw(rs.getString("pw"));
						userBlog.setEtcInfo(rs.getString("etc_info"));

						return userBlog;
					}
				}, new Object[] { userIdNum }));
	}

	@Override
	public int insertReviewBlogPost(ReviewBlogPost reviewBlogPost) {

		String sql = sqlparser.getSQL("blog", "INSERT_REVIEW_BLOG_POST_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { reviewBlogPost.getUserIdNum(),
						reviewBlogPost.getPostNum(),
						reviewBlogPost.getBookIdNum() });
	}

	@Override
	public ReviewBlogPost getReviewBlogPost(ReviewBlogPost reviewBlogPost) {
		String sql = sqlparser.getSQL("blog", "GET_REVIEW_BLOG_POST_SQL");

		return (ReviewBlogPost) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(
						sql,
						new ParameterizedRowMapper<ReviewBlogPost>() {

							@Override
							public ReviewBlogPost mapRow(ResultSet rs,
									int rowNum) throws SQLException {

								ReviewBlogPost reviewBlogPost = new ReviewBlogPost();
								reviewBlogPost.setIdNum(rs.getInt("idNum"));
								reviewBlogPost
										.setPostNum(rs.getString("postNum"));

								return reviewBlogPost;
							}
						},
						new Object[] { reviewBlogPost.getUserIdNum(),
								reviewBlogPost.getBookIdNum() }));
	}


}
