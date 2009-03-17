package com.google.code.booktogether.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("loginCheckInterceptor")
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();

		Integer idNum = (Integer) ra.getAttribute("idNum",
				RequestAttributes.SCOPE_SESSION);
		
		if (idNum != null) {
		} else {
			ra.setAttribute("message", "로그인 되어있지 않습니다.",
					RequestAttributes.SCOPE_SESSION);
			response.sendRedirect("/message.do");
			return false;
		}

		return true;
	}

}
