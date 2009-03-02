package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.GoodWriterService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.GoodWriter;

/**
 * GoodListener에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class GoodWriterController extends AbstractController {

	/**
	 * UserService
	 */
	@Resource(name = "goodWriterService")
	private GoodWriterService goodWriterService;

	/**
	 * 좋은글 등록화면
	 * 
	 * @return
	 */
	@RequestMapping("/goodwriter/insertGoodWriterView.do")
	public ModelAndView handleInsertGoodWriterView(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("goodwriter/insertGoodWriter");

		return mav;
	}

	/**
	 * 좋은글 등록
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/goodwriter/insertGoodWriter.do")
	public ModelAndView handleInsertGoodWriter(HttpServletRequest req,
			GoodWriter goodWriter) {

		boolean result = goodWriterService.insertGoodWriter(goodWriter);

		if (result) {

		}

		return new ModelAndView("redirect:/goodwriter/getGoodWriter.do");

	}

	/**
	 * 좋은글 조회
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/goodwriter/getGoodWriter.do")
	public ModelAndView handleGetGoodWriter(HttpServletRequest req) {

		GoodWriter goodWriter = goodWriterService.getGoodWriter();

		ModelAndView mav = new ModelAndView();
		mav.addObject("goodWriter", goodWriter);
		mav.setViewName("/index");

		return mav;

	}

}
