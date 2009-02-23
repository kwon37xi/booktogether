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
import com.google.code.booktogether.web.page.PageBean;

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

		return libraryDao.getLibrary(userId);

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibrary(Library library) {

		int count = libraryDao.modifyLibrary(library);

		if (count != 1) {
			throw new BooktogetherException("개인서재 수정 실패");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertLibrary(Library library) {

		if (log.isInfoEnabled()) {
			log.info(library);
		}

		int count = libraryDao.insertLibrary(library);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		}

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteLibraryBook(Integer libraryBookIdNum) {

		int count = libraryDao.deleteLibraryBook(libraryBookIdNum);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		}

		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean deletePossessBook(Integer userIdNum, Integer possessBookIdNum) {

		int count = libraryDao.modifyLibraryBookIsPossess(userIdNum,
				possessBookIdNum);

		if (count != 1) {
			throw new BooktogetherException("개인서재 소유정보 수정 실패");
		}

		count = libraryDao.deletePossessBook(possessBookIdNum);

		if (count != 1) {
			throw new BooktogetherException("개인소유책 삭제 실패");
		}

		return true;

	}

	@Override
	public LibraryBook getLibraryBook(Integer libraryBookIdNum) {

		return libraryDao.getLibraryBook(libraryBookIdNum);

	}

	@Override
	public List<LibraryBook> getListLibraryBook(LibraryBook libraryBook,
			PageBean pageBean) {

		int dbCount = libraryDao.getDbCountLibraryBook(libraryBook);

		pageBean.setDbCount(dbCount);

		return libraryDao.getListLibraryBook(libraryBook, pageBean
				.getStartPage() - 1, pageBean.getEndPage());
	}

	@Override
	public List<PossessBook> getListPossessBook(String userId, PageBean pageBean) {

		int dbCount = libraryDao.getDbCountPossessBook(userId);

		pageBean.setDbCount(dbCount);

		return libraryDao.getListPossessBook(userId,
				pageBean.getStartPage() - 1, pageBean.getEndPage());

	}

	@Override
	public PossessBook getPossessBook(Integer possessBookIdNum) {

		return libraryDao.getPossessBook(possessBookIdNum);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertLibraryBook(LibraryBook libraryBook) {

		int count = libraryDao.insertLibraryBook(libraryBook);

		if (count != 1) {
			throw new BooktogetherException("개인서재 등록 실패");
		}
		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertPossessBook(PossessBook possessBook) {

		int count = libraryDao.insertPossessBook(possessBook);

		if (count != 1) {
			throw new BooktogetherException("소유한 책정보 등록 실패");
		}
		return true;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibraryBook(LibraryBook libraryBook) {

		int count = libraryDao.modifyLibraryBook(libraryBook);

		if (count != 1) {
			throw new BooktogetherException("개인서재 책 수정 실패");
		}
		return true;

	}

	@Override
	public boolean duplicateLibraryBook(Integer libraryIdNum, Integer boolIdNum) {

		int count = libraryDao.duplicateLibraryBook(libraryIdNum, boolIdNum);

		if (count == 1) {
			return true;
		}

		return false;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyPossessBook(PossessBook possessBook) {

		int count = libraryDao.modifyPossessBook(possessBook);

		if (count != 1) {
			throw new BooktogetherException("내소유책 수정 실패");
		}

		return true;

	}

}
