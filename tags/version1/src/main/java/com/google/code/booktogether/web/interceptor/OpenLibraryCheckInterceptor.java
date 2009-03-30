package com.google.code.booktogether.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.web.domain.Library;

@Component("openLibraryCheckInterceptor")
public class OpenLibraryCheckInterceptor extends HandlerInterceptorAdapter {

	/**
	 * LibraryService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String userId = ServletRequestUtils.getStringParameter(request,
				"userId");

		Integer libraryIdNum = ServletRequestUtils.getIntParameter(request,
				"libraryIdNum");

		Integer userIdNum = (Integer) RequestContextHolder
				.getRequestAttributes().getAttribute("idNum",
						RequestAttributes.SCOPE_SESSION);

		Library library = null;

		if (userId == null) {
			library=libraryService.getLibrary(libraryIdNum,userIdNum);
		} else {
			library=libraryService.getLibrary(userId, userIdNum);
		}

		// 서재가 있을시
		if (library != null) {

			if (library.getIsOpen() == 2) {

				// 주인이 아닐경우
				RequestContextHolder.getRequestAttributes().setAttribute(
						"message", "비공개 개인서재입니다",
						RequestAttributes.SCOPE_SESSION);

				response.sendRedirect("/message.do");

				return false;

			} 

		} else { // 서재가 없을시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"해당 아이디가 없습니다", RequestAttributes.SCOPE_SESSION);

			if (log.isInfoEnabled()) {
				log.info("해당 아이디가 없다");
			}

			response.sendRedirect("/message.do");

			return false;

		}
		
		request.setAttribute("library", library);

		return true;
	}

}
