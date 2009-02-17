package com.google.code.booktogether.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("loginCheckInterceptor")
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		log.info("로그인 체크시작");

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();

		Integer idNum = (Integer) ra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);

		if (idNum != null) {
			log.info("로그인 되어있다.");
		} else {
			ra.setAttribute("message", "로그인 되어있지 않습니다.",
					RequestAttributes.SCOPE_SESSION);
			response.sendRedirect("/user/login.do");
			return false;
		}

		log.info("로그인 체크종료");

		return true;
	}

}
