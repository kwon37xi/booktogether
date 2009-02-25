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
import com.google.code.booktogether.service.util.HTMLInputFilter;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.LibraryBook;
import com.google.code.booktogether.web.domain.PossessBook;
import com.google.code.booktogether.web.page.PageBean;

@Service("libraryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class LibraryServiceImpl implements LibraryService {

	/**
	 * 서재 JDBC DAO DI
	 */
	@Resource(name = "libraryJdbcDao")
	private LibraryDao libraryDao;

	/**
	 * html 필터
	 */
	@Resource(name = "htmlInputFilter")
	private HTMLInputFilter htmlInputFilter;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Library getLibrary(String userId, Integer userIdNum) {

		Library library = libraryDao.getLibrary(userId);

		if (library != null) {

			// 공개인지 아닌지 검사

			// 공개가 아닐경우
			if (library.getIsOpen() == 1) {

				userIdNum = (userIdNum == null) ? 0 : userIdNum;

				// 주인일경우
				if (library.getUser().getIdNum().intValue() == userIdNum
						.intValue()) { 

					library.setNotice(library.getNotice().replaceAll("\r\n",
							"<br/>"));
					
				} else {	//주인이 아닐경우
					
					library.setIsOpen(2);

				}

			} else {  //공개일경우
				library.setNotice(library.getNotice().replaceAll("\r\n",
						"<br/>"));
			}

		}

		return library;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibrary(Library library) {

		// 스크립트 제거-간단한 태그는 허용
		library.setNotice(htmlInputFilter.filter(library.getNotice()));

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
				.getStartRow() - 1, pageBean.getEndRow());
	}

	@Override
	public List<PossessBook> getListPossessBook(String userId, PageBean pageBean) {

		int dbCount = libraryDao.getDbCountPossessBook(userId);

		pageBean.setDbCount(dbCount);

		return libraryDao.getListPossessBook(userId,
				pageBean.getStartRow() - 1, pageBean.getEndRow());

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

	@Override
	public List<PossessBook> getListZoneBook(String userId, PageBean pageBean) {

		int dbCount = libraryDao.getDbCountListZoneBook(userId);

		pageBean.setDbCount(dbCount);

		List<PossessBook> possessBook = libraryDao.getListZoneBook(userId,
				pageBean.getStartRow() - 1, pageBean.getEndRow());

		return possessBook;
	}

	@Override
	public PossessBook getPossessBook(Integer bookIdNum, Integer userIdNum) {

		return libraryDao.getPossessBook(bookIdNum, userIdNum);

	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyLibraryBook(LibraryBook libraryBook,
			PossessBook possessBook, Integer serviceDiv) {

		int count = 0;

		if (serviceDiv == 0) { // 등록

			count = libraryDao.insertPossessBook(possessBook);

			if (count != 1) {
				throw new BooktogetherException("소유책으로 등록 실패");
			}

		} else if (serviceDiv == 1) { // 삭제

			count = libraryDao.deletePossessBook(possessBook.getIdNum());

			if (count != 1) {
				throw new BooktogetherException("가지고 있는 소유책 삭제 실패");
			}

		} else if (serviceDiv == 2) { // 수정

			count = libraryDao.modifyPossessBook(possessBook);

			if (count != 1) {
				throw new BooktogetherException("가지고 있는 소유책 수정 실패");
			}

		} else {

			log.info("이건 막장이여.ㅋ");
		}

		count = libraryDao.modifyLibraryBook(libraryBook);

		if (count != 1) {
			throw new BooktogetherException("개인서재 책 수정 실패");
		}

		return true;
	}

	@Override
	public Library getLibrary(Integer libraryIdNum, Integer userIdNum) {

		Library library = libraryDao.getLibrary(libraryIdNum);

		if (library != null) {

			// 공개인지 아닌지 검사

			// 공개가 아닐경우
			if (library.getIsOpen() == 1) {

				userIdNum = (userIdNum == null) ? 0 : userIdNum;

				// 주인일경우
				if (library.getUser().getIdNum().intValue() == userIdNum
						.intValue()) { 

					library.setNotice(library.getNotice().replaceAll("\r\n",
							"<br/>"));
					
				} else {	//주인이 아닐경우
					
					library.setIsOpen(2);

				}

			} else {  //공개일경우
				library.setNotice(library.getNotice().replaceAll("\r\n",
						"<br/>"));
			}

		}

		return library;
	}

}