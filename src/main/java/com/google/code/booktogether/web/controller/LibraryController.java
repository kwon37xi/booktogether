package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.page.PageBean;

/**
 * Library에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class LibraryController extends AbstractController {

	/**
	 * LibraryService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 비공개화면보기
	 * 
	 * @param req
	 * @return 비공개화면
	 */
	@RequestMapping("/library/unOpenLibraryView.do")
	public ModelAndView handleUnOpenLibraryView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/library/unOpenLibrary");

		return mav;

	}

	/**
	 * 수정화면 보기
	 * 
	 * @param req
	 * @return 수정화면
	 */
	@RequestMapping("/library/modifyLibraryView.do")
	public ModelAndView handleModifyLibraryView(HttpServletRequest req) {

		Library library = libraryService.getLibrary(getLoginUserId(),
				getLoginUserIdNum());

		// library.setNotice(library.getNotice().replaceAll("\r\n", "<br/>"));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/modifyLibrary");
		mav.addObject("library", library);

		return mav;

	}

	/**
	 * 서재 수정하기
	 * 
	 * @param req
	 * @return 수정하기
	 */
	@RequestMapping("/library/modifyLibrary.do")
	public ModelAndView handleModifyLibrary(HttpServletRequest req,
			Library library) {

		if (log.isInfoEnabled()) {
			log.info(library);
		}

		library.getUser().setIdNum(getLoginUserIdNum());

		// 사용자 아이디, 비밀번호 일치 되는치 검사
		boolean result = libraryService.modifyLibrary(library);

		// 성공시
		if (result) {

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내서재 정보 수정 성공", RequestAttributes.SCOPE_SESSION);

		} else { // 실패시

			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"내서재 정보 수정 실패", RequestAttributes.SCOPE_SESSION);

		}

		// 경로 설정
		return new ModelAndView("redirect:/library/getLibrary.do?userId="
				+ getLoginUserId());

	}

	/**
	 * 서재 조회하기
	 * 
	 * @param req
	 * @return 조회화면
	 */
	@RequestMapping("/library/getLibrary.do")
	public ModelAndView handleGetLibrary(HttpServletRequest req) {

		Library library = getLibrary();

		library.setNotice(library.getNotice().replaceAll("\r\n", "<br/>"));

		// 경로 설정
		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/library");
		mav.addObject("library", library);

		return mav;

	}

	/**
	 * 서재 검색
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchLibrary.do")
	public ModelAndView handleSearchLibrary(
			HttpServletRequest req,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "searchType", required = false) String searchType) {

		List<User> userList = null;

		if (query != null && searchType != null) {

			userList = libraryService.getListSearchLibrary(query, searchType);

		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/searchLibrary");
		mav.addObject("userList", userList);

		return mav;

	}

	/**
	 * 서재내 책 검색
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/library/searchBookInLibrary.do")
	public ModelAndView handleSearchBookInLibrary(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum,
			@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = (pageNo == null) ? 1 : pageNo;
		bookName = (bookName == null) ? "" : bookName.trim();

		PageBean pageBean = new PageBean();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(5);

		List<LibraryBook> libraryBookList = libraryService.getListLibraryBook(
				libraryIdNum, bookName, pageBean);

		
		boolean moreLibraryBookList = (libraryBookList.size() >= pageBean.getPageSize()) ? true
				: false;

		List<PossessBook> possessBookList = libraryService.getListPossessBook(
				libraryIdNum, bookName, pageBean);

		boolean morePossessBookList = (possessBookList.size() >= pageBean
				.getPageSize()) ? true : false;

		ModelAndView mav = new ModelAndView();
		mav.setViewName("library/searchBookInLibrary");
		mav.addObject("libraryBookList", libraryBookList);
		mav.addObject("possessBookList", possessBookList);
		mav.addObject("moreLibraryBookList", moreLibraryBookList);
		mav.addObject("morePossessBookList", morePossessBookList);
		mav.addObject("libraryIdNum", libraryIdNum);
		mav.addObject("bookName", bookName);

		return mav;

	}
}
