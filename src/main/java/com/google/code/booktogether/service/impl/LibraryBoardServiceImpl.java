package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.LibraryBoardDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.LibraryBoardService;
import com.google.code.booktogether.service.util.HTMLInputFilter;
import com.google.code.booktogether.web.domain.LibraryBoard;

@Service("libraryBoardService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class LibraryBoardServiceImpl implements LibraryBoardService {

	/**
	 * 서재 JDBC DAO DI
	 */
	@Resource(name = "libraryBoardJdbcDao")
	private LibraryBoardDao libraryBoardDao;

	/**
	 * html 필터
	 */
	@Resource(name = "htmlInputFilter")
	private HTMLInputFilter htmlInputFilter;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	@Transactional(readOnly = false)
	public boolean deleteLibraryBook(LibraryBoard libraryBoard) {
		
		int count = libraryBoardDao.deleteLibraryBook(libraryBoard);
		
		if(count!=1){
			throw new BooktogetherException("방명록 삭제 실패");
		}
		
		return true;
	}

	@Override
	public List<LibraryBoard> getListLibraryBook(Integer libraryIdNum) {

		List<LibraryBoard> libraryBoardlist = libraryBoardDao
				.getListLibraryBook(libraryIdNum);

		return libraryBoardlist;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertLibraryBook(LibraryBoard libraryBoard) {
		
		log.info(libraryBoard);

		String content=htmlInputFilter.stripHTML(libraryBoard.getContent());
		
		log.info(content);
		
		libraryBoard.setContent(content);
		
		int count = libraryBoardDao.insertLibraryBook(libraryBoard);
		
		if(count!=1){
			throw new BooktogetherException("방명록 등록 실패");
		}
		
		return true;
	}

}
