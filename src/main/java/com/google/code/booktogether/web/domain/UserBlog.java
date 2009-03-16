package com.google.code.booktogether.web.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.code.booktogether.web.domain.base.BaseObject;

/**
 * 사용자 블로그 정보
 * 
 * @author ParkHaeCheol
 * 
 */
public class UserBlog extends BaseObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 일련번호
	 */
	private Integer idNum;

	private String webServer;

	private String blog;

	private String id;

	private String pw;

	private Integer userIdNum;

	private Integer blogDefault;

	private String etcInfo;

	public String getEtcInfo() {
		return etcInfo;
	}

	public void setEtcInfo(String etcInfo) {
		this.etcInfo = etcInfo;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Integer getBlogDefault() {
		return blogDefault;
	}

	public void setBlogDefault(Integer blogDefault) {
		this.blogDefault = blogDefault;
	}

	public Integer getIdNum() {
		return idNum;
	}

	public void setIdNum(Integer idNum) {
		this.idNum = idNum;
	}

	public String getWebServer() {
		return webServer;
	}

	public void setWebServer(String webServer) {
		this.webServer = webServer;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(Integer userIdNum) {
		this.userIdNum = userIdNum;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof UserBlog == false) {
			return false;
		}

		if (this == o) {
			return true;
		}

		UserBlog rhs = (UserBlog) o;

		EqualsBuilder equb = new EqualsBuilder();
		equb.append(idNum, rhs.getIdNum());
		equb.append(webServer, rhs.getWebServer());
		equb.append(blog, rhs.getBlog());
		equb.append(id, rhs.getId());
		equb.append(blogDefault, rhs.getBlogDefault());
		equb.append(userIdNum, rhs.getUserIdNum());

		return equb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hashcode = new HashCodeBuilder(17, 37);
		hashcode.append(idNum);
		hashcode.append(webServer);
		hashcode.append(blog);
		hashcode.append(id);
		hashcode.append(blogDefault);
		hashcode.append(userIdNum);

		return hashcode.toHashCode();
	}

	@Override
	public String toString() {

		ToStringBuilder tob = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		tob.append("idNum", idNum);
		tob.append("webServer", webServer);
		tob.append("blog", blog);
		tob.append("id", id);
		tob.append("blogDefault", blogDefault);
		tob.append("userIdNum", userIdNum);

		return tob.toString();

	}

}
