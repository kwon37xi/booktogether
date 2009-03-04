package com.google.code.booktogether.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.booktogether.service.BestSellersService;
import com.google.code.booktogether.service.BookService;
import com.google.code.booktogether.service.GoodWriterService;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.service.RecoBookService;
import com.google.code.booktogether.web.controller.abst.AbstractController;
import com.google.code.booktogether.web.domain.Book;
import com.google.code.booktogether.web.domain.GoodWriter;
import com.google.code.booktogether.web.domain.User;

/**
 * Main에 관련된 Controller
 * 
 * @author ParkHaeCheol
 */
@Controller()
public class MainController extends AbstractController {

	/**
	 * UserService
	 */
	@Resource(name = "bookService")
	private BookService bookService;

	/**
	 * BestSellersService
	 */
	@Resource(name = "bestSellersService")
	private BestSellersService bestSellersService;
	
	
	/**
	 * RecoBookService
	 */
	@Resource(name = "recoBookService")
	private RecoBookService recoBookService;

	/**
	 * UserService
	 */
	@Resource(name = "goodWriterService")
	private GoodWriterService goodWriterService;

	/**
	 * LibraryService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	/**
	 * 아이디 중복 확인
	 * 
	 * @param req
	 */
	@RequestMapping("/index.do")
	public ModelAndView handleDuplicateUserId(HttpServletRequest req) {
		
		//베스트 셀러 목록
		List<Book> bestSellerList=bestSellersService.getListBestSellers();
		
		
		//추천책
		List<Book> recoBookList=recoBookService.getListRecoBook();
		
		
		//서재 인기 순위
		List<User> libraryRankList=libraryService.getLibraryRank();
		
		
		//좋은 글 
		GoodWriter goodWriter=goodWriterService.getGoodWriter();
		
		
		//인기 책 순위
		List<Book> topBookHitsList=bookService.getListTopBookHits();
		
		
		//인기 검색 순위
		List<String> searchRankQuerys=bookService.getListSearchRankQuery();

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main");
		mav.addObject("searchRankQuerys", searchRankQuerys);
		mav.addObject("topBookHitsList", topBookHitsList);
		mav.addObject("goodWriter", goodWriter);
		mav.addObject("libraryRankList", libraryRankList);
		mav.addObject("bestSellerList", bestSellerList);
		mav.addObject("recoBookList", recoBookList);

		return mav;

	}
}
