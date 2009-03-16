package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.LibraryBoardService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.LibraryBoard;

/**
 * LibraryBoard에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller
public class LibraryBoardController extends AbstractController {

	/**
	 * libraryBoardService
	 */
	@Resource(name = "libraryBoardService")
	private LibraryBoardService libraryBoardService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 방명록 보여주기(목록)
	 * 
	 * @param req
	 * @param libraryIdNum
	 * @return
	 */
	@RequestMapping("/library/getListLibraryBoard.do")
	public ModelAndView handleListBookInLibrary(
			HttpServletRequest req,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum) {

		log.info(libraryIdNum);

		List<LibraryBoard> libraryBoardList = libraryBoardService
				.getListLibraryBook(libraryIdNum);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("libraryboard/getListLibraryBoard");
		mav.addObject("libraryBoardList", libraryBoardList);
		mav.addObject("libraryIdNum", libraryIdNum);

		return mav;

	}

	/**
	 * 방명록 등록
	 * 
	 * @param req
	 * @param content
	 * @param libraryIdNum
	 * @return
	 */
	@RequestMapping("/library/insertLibraryBoard.do")
	public ModelAndView handleInsertLibraryBoard(
			HttpServletRequest req,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum) {

		LibraryBoard libraryBoard = new LibraryBoard();

		libraryBoard.setLibraryIdNum(libraryIdNum);
		libraryBoard.setWriter(getLoginUserIdNum());
		libraryBoard.setContent(content);

		boolean result = libraryBoardService.insertLibraryBook(libraryBoard);

		if (result) {
			if (log.isInfoEnabled()) {
				log.info("방명록 등록 성공");
			}
		} else {
			if (log.isInfoEnabled()) {
				log.info("방명록 등록 실패");
			}
		}

		return new ModelAndView(
				"redirect:/library/getListLibraryBoard.do?libraryIdNum="
						+ libraryIdNum);

	}

	/**
	 * 방명록 삭제
	 * 
	 * @param req
	 * @param boardIdNum
	 * @param libraryIdNum
	 * @return
	 */
	@RequestMapping("/library/deleteLibraryBoard.do")
	public ModelAndView handleDeleteLibraryBoard(
			HttpServletRequest req,
			@RequestParam(value = "boardIdNum", required = false) Integer boardIdNum,
			@RequestParam(value = "libraryIdNum", required = false) Integer libraryIdNum) {

		LibraryBoard libraryBoard = new LibraryBoard();

		libraryBoard.setLibraryIdNum(libraryIdNum);
		libraryBoard.setWriter(getLoginUserIdNum());
		libraryBoard.setIdNum(boardIdNum);

		log.info(libraryBoard);

		boolean result = libraryBoardService.deleteLibraryBook(libraryBoard);

		if (result) {
			if (log.isInfoEnabled()) {
				log.info("방명록 삭제 성공");
			}
		} else {
			if (log.isInfoEnabled()) {
				log.info("방명록 삭제 실패");
			}
		}

		return new ModelAndView(
				"redirect:/library/getListLibraryBoard.do?libraryIdNum="
						+ libraryIdNum);

	}

}
