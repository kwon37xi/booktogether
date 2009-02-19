package com.google.code.booktogether.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.booktogether.dao.LibraryDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;

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

		log.info(library);

		int count = libraryDao.insertLibrary(library);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteLibraryBook(Integer libraryBookIdNum) {

		int count = libraryDao.deleteLibraryBook(libraryBookIdNum);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deletePossessBook(Integer userIdNum, Integer possessBookIdNum) {

		int count = libraryDao.modifyLibraryBookIsPossess(userIdNum,
				possessBookIdNum);

		if (count != 1) {
			throw new BooktogetherException("개인서재 소유정보 수정 실패");
		} else {

			count = libraryDao.deletePossessBook(possessBookIdNum);

			log.info(count);
			
			if (count != 1) {
				throw new BooktogetherException("개인소유책 삭제 실패");
			} else {
				return true;
			}

		}
	}

	@Override
	public LibraryBook getLibraryBook(Integer libraryBookIdNum) {

		LibraryBook libraryBook = libraryDao.getLibraryBook(libraryBookIdNum);

		return libraryBook;

	}

	@Override
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			Integer startPage, Integer endPage) {

		List<LibraryBook> libraryBookList = libraryDao.getListLibraryBook(
				libraryBook, startPage, endPage);

		return libraryBookList;
	}

	@Override
	public List<PossessBook> getListPossessBook(String userIdNum,
			Integer startPage, Integer endPage) {

		List<PossessBook> possessBookList = libraryDao.getListPossessBook(
				userIdNum, startPage, endPage);

		return possessBookList;

	}

	@Override
	public PossessBook getPossessBook(Integer possessBookIdNum) {

		PossessBook possessBook = libraryDao.getPossessBook(possessBookIdNum);

		return possessBook;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertLibraryBook(LibraryBook libraryBook) {

		int count = libraryDao.insertLibraryBook(libraryBook);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertPossessBook(PossessBook possessBook) {

		int count = libraryDao.insertPossessBook(possessBook);

		if (count != 1) {
			throw new BooktogetherException("소유한 책정보 등록 실패");
		} else {
			return true;
		}

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibraryBook(LibraryBook libraryBook) {

		int count = libraryDao.modifyLibraryBook(libraryBook);

		if (count != 1) {
			throw new BooktogetherException("개인서재 책 수정 실패");
		} else {
			return true;
		}
	}

	@Override
	public boolean duplicateLibraryBook(Integer libraryIdNum, Integer boolIdNum) {

		int count = libraryDao.duplicateLibraryBook(libraryIdNum, boolIdNum);

		if (count == 1) {
			log.info("중복이다!!!");
			return true;
		} else {
			log.info("중복아니다.!!!");
			return false;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyPossessBook(PossessBook possessBook) {

		int count = libraryDao.modifyPossessBook(possessBook);

		if (count != 1) {
			throw new BooktogetherException("내소유책 수정 실패");
		} else {
			return true;
		}
	}

}
