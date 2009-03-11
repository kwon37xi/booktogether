package com.google.code.booktogether.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BestSellersService;
import com.google.code.booktogether.web.controller.abst.AbstractController;


/**
 * BestSellers에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class BestSellersController extends AbstractController {

	/**
	 * UserService
	 */
	@Resource(name = "bestSellersService")
	private BestSellersService bestSellersService;

	/**
	 * 베스트셀러 등록
	 * 
	 * @param req
	 * @return 로그인화면
	 */
	@RequestMapping("/book/insertBestSellers.do")
	public ModelAndView handleInsertGoodWriter(HttpServletRequest req,
			@RequestParam(value = "bookIdNum", required = false) Integer bookIdNum) {

		boolean result = bestSellersService.insertBestSeller(bookIdNum);

		if (result) {
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"베스트 셀러에 등록되어있습니다.", RequestAttributes.SCOPE_SESSION);
		}else{
			RequestContextHolder.getRequestAttributes().setAttribute("message",
					"베스트 셀러에 등록 실패 되어있습니다.", RequestAttributes.SCOPE_SESSION);
		}

		return new ModelAndView("redirect:/book/getBook.do?bookIdNum="+bookIdNum);

	}

}
