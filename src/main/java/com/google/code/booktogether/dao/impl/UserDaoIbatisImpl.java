package com.google.code.booktogether.dao.impl;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;
import com.google.code.booktogether.web.page.PageBean;

public class UserDaoIbatisImpl extends SqlMapClientDaoSupport implements
		UserDao {

	@Override
	public int deleteUser(int id) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.deleteUser",
				id);
	}

	@Override
	public int getDbCount() {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"USERDAO.DBCOUNT_USER_SQL");

	}

	@Override
	public int getLastNumIncrement() {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"USERDAO.GET_LAST_NUM");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getListUser(Integer startRow, Integer endRow) {

		return (List<User>) getSqlMapClientTemplate().queryForList("USERDAO.LIST_USER_SQL",
				new PageBean(startRow, endRow));
	}

	@Override
	public User getUser(Integer userId) {

		return (User) getSqlMapClientTemplate().queryForList("USERDAO.GET_USER_SQL", userId);

	}

	@Override
	public int insertUser(User user) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.INSERT_USER_SQL", user);

	}

	@Override
	public int modifyUser(User user) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.MODIFY_USER_SQL");

	}

	@Override
	public int modifyUserAddInfo(UserAddInfo userAddInfo) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.MODIFY_USERADDINFO_SQL", userAddInfo);

	}

	@Override
	public int modifyUserPw(UserPw userpw) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.MODIFY_USER_PW_SQL", userpw);

	}

	@Override
	public int insertUserPw(UserPw userPw) {

		return (Integer) getSqlMapClientTemplate().update("USERDAO.INSERT_USER_PW_SQL", userPw);
	}

	@Override
	public UserPw getUserPw(Integer userIdNum) {

		return (UserPw) getSqlMapClientTemplate()
				.queryForObject("USERDAO.GET_USER_PW_SQL", userIdNum);
	}

	@Override
	public User isExistUser(String userId) {

		return (User) getSqlMapClientTemplate().queryForObject("USERDAO.EXIST_USER_SQL", userId);
	}

	@Override
	public String findId(User user) {

		String userId = null;

		try {
			userId = (String) getSqlMapClientTemplate().queryForObject("USERDAO.FIND_USER_ID_SQL",
					user);
		} catch (Exception e) {
			return "";
		}

		if (userId == null) {
			return "";
		}

		return userId;

	}

	@Override
	public User findPw(User user) {

		return (User) getSqlMapClientTemplate().queryForObject("USERDAO.FIND_USER_PW_SQL", user);
	}

	@Override
	public int insertUserAddInfo(UserAddInfo userAddInfo) {

		return getSqlMapClientTemplate().update("USERDAO.INSERT_USERADDINFO_SQL", userAddInfo);
	}

	@Override
	public int insertZone(Zone zone) {

		return getSqlMapClientTemplate().update("USERDAO.INSERT_ZONE_SQL", zone);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Zone> getZones(Integer userIdNum) {

		return getSqlMapClientTemplate().queryForList("USERDAO.GET_ZONE_SQL", userIdNum);

	}

	@Override
	// 여기 조금 봐야겠다.
	public int deleteZone(Integer zoneIdNum, Integer userIdNum) {

		return getSqlMapClientTemplate().update("USERDAO.DELETE_ZONE_SQL",
				new Object[] { zoneIdNum, userIdNum });
	}

	@Override
	public int duplicateUserId(String userId) {

		return (Integer) getSqlMapClientTemplate().queryForObject("USERDAO.DUPLICATE_USER_ID_SQL", userId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Zipcode> getListZipcode(String addr) {

		return getSqlMapClientTemplate().queryForList("USERDAO.LIST_ZIPCODE_SQL", "%" + addr + "%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListUserAddr(String userId) {

		return (List<String>) getSqlMapClientTemplate().queryForList("USERDAO.LIST_ZONENAME_SQL",
				userId);
	}

}
