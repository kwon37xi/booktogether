package com.google.code.booktogether.service.blogpost.impl;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.blogpost.BlogPost;
import com.google.code.booktogether.web.domain.BookReview;
import com.google.code.booktogether.web.domain.UserBlog;

public class MetaWeBlogPostImpl implements BlogPost {

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	public Object newPostBlog(UserBlog userBlog, BookReview bookReview) {

		Object postIdNum = null;

		String serverURL = userBlog.getWebServer();

		if (serverURL == null) {
			throw new BooktogetherException("WebServer주소가 NULL값 입니다");
		}

		try {

			XmlRpcClient server = new XmlRpcClient();
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

			config.setServerURL(new java.net.URL(serverURL));
			server.setConfig(config);

			if (serverURL.indexOf("egloos") != -1) {

				postIdNum = newPostBlogEgloos(server, config, userBlog,
						bookReview);

			} else {
				throw new BooktogetherException(
						"해당 WebServer주소로는 POSTING기능을 지원하지 않는다.");
			}

		} catch (Exception exception) {
			log.info("JavaClient: " + exception.toString());
		}

		return postIdNum;
	}

	private Object newPostBlogEgloos(XmlRpcClient server,
			XmlRpcClientConfigImpl config, UserBlog userBlog,
			BookReview bookReview) {

		Object postIdNum = null;

		try {

			Vector<Object> params = new Vector<Object>();

			params.addElement(1); // BLOG ID(블로그
			// 일련번호)
			params.addElement(new String(userBlog.getId())); // ID
			params.addElement(new String(userBlog.getPw())); // PW

			Hashtable<String, String> hashtable = new Hashtable<String, String>();
			hashtable.put("title", bookReview.getTitle());
			hashtable.put("description", bookReview.getReview());
			hashtable.put("mt_keywords", "booktogether");

			params.addElement(hashtable);
			// true몇 공개 false면 비공개
			params.add(new Boolean(true));

			postIdNum = server.execute(config, "metaWeblog.newPost", params);

			log.info(postIdNum);

		} catch (XmlRpcException exception) {
			log.info("JavaClient: XML-RPC Fault #"
					+ Integer.toString(exception.code) + ": "
					+ exception.toString());
		} catch (Exception exception) {
			log.info("JavaClient: " + exception.toString());
		}

		// 포스팅된 번호가 나옵니다.
		return postIdNum;
	}

	private Object editPostBlogEgloos(XmlRpcClient server,
			XmlRpcClientConfigImpl config, UserBlog userBlog,
			BookReview bookReview, String postNum) {

		Object postIdNum = null;

		try {

			Vector<Object> params = new Vector<Object>();

			params.addElement(new String(postNum)); // POST ID(블로그 일련번호)
			params.addElement(new String(userBlog.getId())); // ID
			params.addElement(new String(userBlog.getPw())); // PW

			Hashtable<String, String> hashtable = new Hashtable<String, String>();
			hashtable.put("title", bookReview.getTitle());
			hashtable.put("description", bookReview.getReview());
			hashtable.put("mt_keywords", "booktogether");

			params.addElement(hashtable);
			// true몇 공개 false면 비공개
			params.add(new Boolean(true));

			postIdNum = server.execute(config, "metaWeblog.editPost", params);

			log.info(postIdNum);

		} catch (XmlRpcException exception) {
			log.info("JavaClient: XML-RPC Fault #"
					+ Integer.toString(exception.code) + ": "
					+ exception.toString());
		} catch (Exception exception) {
			log.info("JavaClient: " + exception.toString());
		}

		// 포스팅된 번호가 나옵니다.
		return postIdNum;
	}

	@Override
	public boolean validBlog(UserBlog userBlog) {

		boolean result = false;

		String serverURL = userBlog.getWebServer();

		XmlRpcClient server = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
			config.setServerURL(new java.net.URL(serverURL));

			if (serverURL == null) {
				throw new BooktogetherException("WebServer주소가 NULL값 입니다");
			}

			config.setServerURL(new java.net.URL(serverURL));
			server.setConfig(config);

			if (serverURL.indexOf("egloos") != -1) {

				Object[] params = new Object[] { 1, userBlog.getId(),
						userBlog.getPw() };

				Object[] returnedObj = null;

				try {
					returnedObj = (Object[]) server.execute(
							"metaWeblog.getCategories", params);
				} catch (Exception e) {
					result = false;
				}

				if (returnedObj != null) {
					result = true;
				}

			} else if (serverURL.indexOf("daum") != -1) {

			} else {
				throw new BooktogetherException(
						"해당 WebServer주소로는 POSTING기능을 지원하지 않는다.");
			}
		} catch (Exception e) {
			throw new BooktogetherException("에러에러에러", e);
		}

		return result;
	}

	@Override
	public Object[] getCategories(UserBlog userBlog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getRecentPosts(UserBlog userBlog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editPostBlog(UserBlog userBlog, BookReview bookReview,
			String postNum) {

		String serverURL = userBlog.getWebServer();

		if (serverURL == null) {
			throw new BooktogetherException("WebServer주소가 NULL값 입니다");
		}

		try {

			XmlRpcClient server = new XmlRpcClient();
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

			config.setServerURL(new java.net.URL(serverURL));
			server.setConfig(config);

			if (serverURL.indexOf("egloos") != -1) {

				editPostBlogEgloos(server, config, userBlog, bookReview,
						postNum);

			} else {
				throw new BooktogetherException(
						"해당 WebServer주소로는 POSTING기능을 지원하지 않는다.");
			}

		} catch (Exception exception) {
			log.info("JavaClient: " + exception.toString());
		}

		return true;
	}

}