package com.google.code.booktogether.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.exception.BooktogetherException;
import com.google.code.booktogether.service.LibraryService;
import com.google.code.booktogether.service.UserService;
import com.google.code.booktogether.service.util.HTMLInputFilter;
import com.google.code.booktogether.service.util.ImageResize;
import com.google.code.booktogether.service.util.PasswordAuthenticator;
import com.google.code.booktogether.web.domain.Library;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;
import com.google.code.booktogether.web.page.PageBean;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

	/**
	 * 사용자 JDBC DAO DI
	 */
	@Resource(name = "userJdbcDao")
	private UserDao userJdbcDao;

	/**
	 * html 필터
	 */
	@Resource(name = "htmlInputFilter")
	private HTMLInputFilter htmlInputFilter;

	/**
	 * BookService
	 */
	@Resource(name = "libraryService")
	private LibraryService libraryService;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	@Transactional(readOnly = false)
	public boolean deleteUser(Integer idNum) {

		int count = userJdbcDao.deleteUser(idNum);

		if (count != 1) {
			throw new BooktogetherException("해당 사용자 ID존재 하지 않음");
		}

		return true;
	}

	@Override
	public List<User> getListUser(PageBean pageBean) {

		int dbCount = userJdbcDao.getDbCount();

		pageBean.setDbCount(dbCount);

		return userJdbcDao.getListUser(pageBean.getStartRow() - 1, pageBean
				.getEndRow());
	}

	@Override
	public User getUser(Integer idNum) {

		return userJdbcDao.getUser(idNum);
	}

	@Override
	public User getUserDetail(Integer idNum) {

		User user = userJdbcDao.getUser(idNum);

		List<Zone> zoneList = userJdbcDao.getZones(idNum);

		Zone[] zones = (Zone[]) zoneList.toArray(new Zone[zoneList.size()]);

		user.setZones(zones);

		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean insertUser(User user, UserPw userPw) {

		boolean result = false;

		// 스크립트 제거
		user.setEmail(htmlInputFilter.stripHTML(user.getEmail()));
		user.setName(htmlInputFilter.stripHTML(user.getName()));
		user.setNickname(htmlInputFilter.stripHTML(user.getNickname()));

		// 사용자 등록
		int count = userJdbcDao.insertUser(user);

		if (count == 0) {
			throw new BooktogetherException("비밀번호 등록 실패");
		}

		// 사용자 일련번호 정보 가지고 오기
		Integer idNum = userJdbcDao.getLastNumIncrement();

		// --------암호화 작업 시작---------------
		byte[] salt = PasswordAuthenticator.generatorSalt();
		byte[] digest = null;

		try {
			digest = PasswordAuthenticator.createPasswordDigest(userPw.getPw(),
					salt);
		} catch (Exception e) {
			throw new BooktogetherException("비밀번호 암호화 실패");
		}
		// --------암호화 작업 종료---------------

		// 암호화 정보 세팅
		userPw.setUserIdNum(idNum);
		userPw.setDigest(digest);
		userPw.setSalt(salt);

		// 암호 정보 등록
		count = userJdbcDao.insertUserPw(userPw);

		if (count != 1) {
			throw new BooktogetherException("비밀번호 등록 실패");
		}

		// 사용자 추가 정보 세팅
		user.getUserAddInfo().setUserIdNum(idNum);

		UserAddInfo userAddInfo = user.getUserAddInfo();

		// 스크립트 제거
		userAddInfo.setBlog(htmlInputFilter.stripHTML(userAddInfo.getBlog()));
		userAddInfo.setThumnail(htmlInputFilter.stripHTML(userAddInfo
				.getThumnail()));

		count = userJdbcDao.insertUserAddInfo(userAddInfo);

		if (count != 1) {
			throw new BooktogetherException("사용자 추가정보 등록 실패");
		}

		// 지역명 등록(생활반경)
		for (Zone zone : user.getZones()) {

			if (zone != null) {

				zone.setUserIdNum(idNum);
				count = userJdbcDao.insertZone(zone);

				if (count != 1) {
					throw new BooktogetherException("생활반경 등록 실패");
				}
			}

		}

		// 개인서제 등록
		Library library = new Library();
		library.getUser().setIdNum(idNum);
		library.setIsOpen(0);
		library.setNotice("등록된 인사말이 없습니다.");

		result = libraryService.insertLibrary(library);

		if (!result) {
			throw new BooktogetherException("사용자 개인서재 등록 실패");
		}

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyUser(User user) {

		// 스크립트 제거
		user.setEmail(htmlInputFilter.stripHTML(user.getEmail()));
		user.setName(htmlInputFilter.stripHTML(user.getName()));
		user.setNickname(htmlInputFilter.stripHTML(user.getNickname()));

		int count = userJdbcDao.modifyUser(user);

		if (count != 1) {
			throw new BooktogetherException("사용자 기본정보 수정 실패");
		}

		UserAddInfo userAddInfo = user.getUserAddInfo();

		// 스크립트 제거
		userAddInfo.setBlog(htmlInputFilter.stripHTML(userAddInfo.getBlog()));
		userAddInfo.setThumnail(htmlInputFilter.stripHTML(userAddInfo
				.getThumnail()));

		count = userJdbcDao.modifyUserAddInfo(userAddInfo);

		if (count != 1) {
			throw new BooktogetherException("사용자 추가 정보 수정실패");
		}

		// 생호라 반경 등록
		for (Zone zone : user.getZones()) {

			if (zone != null) {

				count = userJdbcDao.insertZone(zone);

				if (count != 1) {
					throw new BooktogetherException("사용자 생활반경 등록 실패");
				}
			}

		}

		return true;
	}

	@Override
	public User validIdPwUser(String userId, String pw) {

		User user = userJdbcDao.isExistUser(userId);

		if (user != null) {

			UserPw userPw = userJdbcDao.getUserPw(user.getIdNum());

			byte[] salt = userPw.getSalt();
			byte[] hashedDigest = userPw.getDigest();
			byte[] digest = null;

			try {
				digest = PasswordAuthenticator.createPasswordDigest(pw, salt);
			} catch (Exception e1) {
				throw new BooktogetherException("비밀번호 암호화 실패");
			}

			if (Arrays.equals(digest, hashedDigest)) {
				if (log.isInfoEnabled()) {
					log.info("일치한다.");
				}
			} else {
				if (log.isInfoEnabled()) {
					log.info("일치하지 않는다.");
				}
				return null;
			}

		}

		return user;
	}

	@Override
	public String findId(User user) {

		return userJdbcDao.findId(user);

	}

	@Override
	@Transactional(readOnly = false)
	public String findPw(User user) {

		String message = "";

		user = userJdbcDao.findPw(user);

		if (user != null) {

			// 임시 비밀번호 맘들기
			String temp_pw = RandomStringUtils.random(8, true, true);

			byte[] salt = PasswordAuthenticator.generatorSalt();
			byte[] digest = null;

			try {
				digest = PasswordAuthenticator.createPasswordDigest(temp_pw,
						salt);
			} catch (Exception e) {
				message = "시스템에 문제가 발생하여 비밀번호 전송이 실패 하였습니다.";
				return message;
			}

			// 임시 암호 저장
			UserPw userPw = new UserPw();
			userPw.setDigest(digest);
			userPw.setSalt(salt);
			userPw.setUserIdNum(user.getIdNum());

			int count = userJdbcDao.modifyUserPw(userPw);

			if (count != 1) {
				throw new BooktogetherException("임시 비밀번호로 비밀번호 수정 실패");
			}

			// 이메일로 전송 알고리즘 구현 해야됨

			message = "성공적으로 전송되었습니다.";

		} else {
			message = "해당 사용자가 존재하지 않습니다.";
		}

		return message;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean modifyPw(User user, String newPw) {

		byte[] salt = PasswordAuthenticator.generatorSalt();
		byte[] digest = null;

		try {
			digest = PasswordAuthenticator.createPasswordDigest(newPw, salt);
		} catch (Exception e) {
			throw new BooktogetherException("비밀번호 암호화 실패", e);
		}

		// 비밀번호 변경 내용(암호화) 저장
		UserPw userPw = new UserPw();
		userPw.setDigest(digest);
		userPw.setSalt(salt);
		userPw.setUserIdNum(user.getIdNum());

		int count = userJdbcDao.modifyUserPw(userPw);

		if (count != 1) {
			throw new BooktogetherException("비밀번호 수정 실패");
		}

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteZone(Integer zoneIdNum, Integer userIdNum) {

		int count = userJdbcDao.deleteZone(zoneIdNum, userIdNum);

		if (count != 1) {
			throw new BooktogetherException("생활 반경 삭제 실패");
		}

		return true;

	}

	@Override
	public String createImageResize(MultipartFile file, String realPath) {

		// 임의의 파일명 현재 시간+기존파일명
		String filename = System.currentTimeMillis()
				+ file.getOriginalFilename();

		if (!file.getOriginalFilename().equals("")) {

			try {

				ImageResize.createImageResize(file.getInputStream(), realPath
						+ File.separatorChar + filename, 100, 100);

			} catch (Exception e) {
				filename = "";
				throw new BooktogetherException("이미지 축소 및 JPEG 압축 과정에서 실패", e);

			}
		}else{
			filename="userDefault.png";
		}

		return filename;
	}

	@Override
	public boolean deleteThumnail(String realPath, String filename) {

		boolean result = new File(realPath + File.separatorChar + filename)
				.delete();

		return result;
	}

	@Override
	public boolean duplicateUserId(String userId) {

		boolean result = false;

		int count = userJdbcDao.duplicateUserId(userId);

		result = (count == 0) ? true : false;

		return result;
	}

	@Override
	public List<Zipcode> getListAddr(String addr) {

		return userJdbcDao.getListZipcode(addr);

	}

	@Override
	public List<String> getListUserAddr(String userId) {
		List<String> zoneName = userJdbcDao.getListUserAddr(userId);
		return zoneName;
	}

}
