package com.google.code.booktogether.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.LibraryDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.web.domain.Library;

@Service("libraryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class LibraryServiceImpl implements LibraryService {

	// 서재 JDBC DAO DI
	@Resource(name = "libraryJdbcDao")
	private LibraryDao libraryDao;
	
	
	
	

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Library getLibrary(String userId) {

		Library library = libraryDao.getLibrary(userId);

		return library;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibrary(Library library) {

		int count = libraryDao.modifyLibrary(library);

		if (count != 1) {
			throw new BooktogetherException("개인서재 수정 실패");
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertLibrary(Library library) {
		
		int count = libraryDao.insertLibrary(library);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		} else {
			return true;
		}
	}

}
