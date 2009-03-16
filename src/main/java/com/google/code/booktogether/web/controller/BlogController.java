package com.google.code.booktogether.web.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.BlogService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.UserBlog;

/**
 * Blog에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class BlogController extends AbstractController {

	/**
	 * BlogService
	 */
	@Resource(name = "blogService")
	private BlogService blogService;

	@RequestMapping("/blog/validBlogAjax.do")
	public void handleValidBlog(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "webServer", required = false) String webServer,
			@RequestParam(value = "blog", required = false) String blog,
			@RequestParam(value = "etcInfo", required = false) String etcInfo,
			@RequestParam(value = "blogId", required = false) String blogId,
			@RequestParam(value = "blogPw", required = false) String blogPw) {

		JSONObject json = new JSONObject();
		
		UserBlog userBlog = new UserBlog();
		userBlog.setWebServer(webServer);
		userBlog.setId(blogId);
		userBlog.setPw(blogPw);
		userBlog.setBlog(blog);
		userBlog.setEtcInfo(etcInfo);

		// 우편정보목록
		boolean result = blogService.validUserBlog(userBlog);

		String message = null;
		String retValue = null;

		if (result) {
			message = "유효성검사 완료";
			retValue = "true";
		} else {
			message = "유효성검사 실패";
			retValue = "false";
		}

		json.element("message", message);
		json.element("status", retValue);

		try {
			res.setContentType("application/json;charset=utf-8");
			res.getWriter().print(json.toString());
		} catch (IOException e) {
			throw new BooktogetherException("블로그 검사 실패", e);
		}
	}

}
