package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.code.booktogether.dao.UserDao;
import com.google.code.booktogether.dao.rowmapper.UserPwRowMapper;
import com.google.code.booktogether.dao.rowmapper.UserRowMapper;
import com.google.code.booktogether.dao.rowmapper.ZipcodeRowMapper;
import com.google.code.booktogether.dao.rowmapper.ZoneRowMapper;
import com.google.code.booktogether.dao.sqlparser.impl.SqlParserXmlImpl;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zipcode;
import com.google.code.booktogether.web.domain.Zone;

@Repository("userJdbcDao")
public class UserDaoJdbcImpl extends SimpleJdbcDaoSupport implements UserDao {

	@Resource(name = "SqlParser")
	SqlParserXmlImpl sqlparser;

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int deleteUser(int id) {

		String sql = sqlparser.getSQL("user", "DELETE_USER_SQL");

		int count = getSimpleJdbcTemplate().update(sql, new Object[] { id });

		return count;
	}

	@Override
	public int getDbCount() {

		String sql = sqlparser.getSQL("user", "DBCOUNT_USER_SQL");

		int dbCount = getSimpleJdbcTemplate().queryForInt(sql);

		return dbCount;

	}

	@Override
	public int getLastNumIncrement() {

		String sql = sqlparser.getSQL("user", "GET_LAST_NUM");

		int lastIncrement = getSimpleJdbcTemplate().queryForInt(sql);

		return lastIncrement;
	}

	@Override
	public List<User> getListUser(Integer startPage, Integer pageSize) {

		UserRowMapper userRowMapper = new UserRowMapper();

		String sql = sqlparser.getSQL("user", "LIST_USER_SQL");

		List<User> userlist = getSimpleJdbcTemplate().query(sql, userRowMapper,
				new Object[] { startPage, pageSize });

		return userlist;
	}

	@Override
	public User getUser(Integer userId) {

		UserRowMapper userRowMapper = new UserRowMapper();

		String sql = sqlparser.getSQL("user", "GET_USER_SQL");

		User user = (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, userRowMapper, new Object[] { userId }));

		return user;

	}

	@Override
	public int insertUser(User user) {

		String sql = sqlparser.getSQL("user", "INSERT_USER_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getUserId(), user.getEmail(),
						user.getNickname(), user.getName() });

		return count;

	}

	@Override
	public int modifyUser(User user) {

		String sql = sqlparser.getSQL("user", "MODIFY_USER_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getEmail(), user.getNickname(),
						user.getName(), user.getIdNum() });

		return count;

	}

	@Override
	public int modifyUserAddInfo(UserAddInfo userAddInfo) {

		String sql = sqlparser.getSQL("user", "MODIFY_USERADDINFO_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userAddInfo.getBlog(),
						userAddInfo.getThumnail(), userAddInfo.getIdNum() }

		);

		return count;

	}

	@Override
	public int modifyUserPw(UserPw userpw) {

		String sql = sqlparser.getSQL("user", "MODIFY_USER_PW_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userpw.getDigest(), userpw.getSalt(),
						userpw.getUserIdNum() });

		return count;

	}

	@Override
	public int insertUserPw(UserPw userPw) {

		String sql = sqlparser.getSQL("user", "INSERT_USER_PW_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userPw.getUserIdNum(), userPw.getDigest(),
						userPw.getSalt() });

		return count;
	}

	@Override
	public UserPw getUserPw(Integer userIdNum) {

		UserPwRowMapper userPwRowMapper = new UserPwRowMapper();

		String sql = sqlparser.getSQL("user", "GET_USER_PW_SQL");

		UserPw userPw = (UserPw) DataAccessUtils
				.singleResult(getSimpleJdbcTemplate().query(sql,
						userPwRowMapper, new Object[] { userIdNum }));

		return userPw;
	}

	@Override
	public User isExistUser(String user_id) {

		UserRowMapper userRowMapper = new UserRowMapper();

		String sql = sqlparser.getSQL("user", "EXIST_USER_SQL");

		User user = (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, userRowMapper, new Object[] { user_id }));

		return user;
	}

	@Override
	public String findId(User user) {

		UserRowMapper userRowMapper = new UserRowMapper();

		String sql = sqlparser.getSQL("user", "FIND_USER_ID_SQL");

		user = (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, userRowMapper,
						new Object[] { user.getName(), user.getEmail() }));

		return user.getUserId();

	}

	@Override
	public User findPw(User user) {

		UserRowMapper userRowMapper = new UserRowMapper();

		String sql = sqlparser.getSQL("user", "FIND_USER_PW_SQL");

		user = (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(
						sql,
						userRowMapper,
						new Object[] { user.getUserId(), user.getName(),
								user.getEmail() }));

		return user;

	}

	@Override
	public int insertUserAddInfo(UserAddInfo userAddInfo) {

		String sql = sqlparser.getSQL("user", "INSERT_USERADDINFO_SQL");

		int count = getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userAddInfo.getUserIdNum(),
						userAddInfo.getBlog(), userAddInfo.getThumnail() });

		return count;
	}

	@Override
	public int insertZone(Zone zone) {

		String sql = sqlparser.getSQL("user", "INSERT_ZONE_SQL");

		int count = getSimpleJdbcTemplate().update(sql,
				new Object[] { zone.getUserIdNum(), zone.getZone() });

		return count;
	}

	@Override
	public List<Zone> getZones(Integer userIdNum) {

		ZoneRowMapper zoneRowMapper = new ZoneRowMapper();

		String sql = sqlparser.getSQL("user", "GET_ZONE_SQL");

		List<Zone> zoneList = getSimpleJdbcTemplate().query(sql, zoneRowMapper,
				new Object[] { userIdNum });

		return zoneList;

	}

	@Override
	public int deleteZone(Integer zoneIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("user", "DELETE_ZONE_SQL");

		int count = getSimpleJdbcTemplate().update(sql,
				new Object[] { zoneIdNum, userIdNum });

		return count;
	}

	@Override
	public int duplicateUserId(String userId) {

		String sql = sqlparser.getSQL("user", "DUPLICATE_USER_ID_SQL");

		int count = getSimpleJdbcTemplate().queryForInt(sql,
				new Object[] { userId });

		return count;
	}

	@Override
	public List<Zipcode> getListZipcode(String addr) {

		ZipcodeRowMapper zipcodeRowMapper = new ZipcodeRowMapper();

		String sql = sqlparser.getSQL("user", "LIST_ZIPCODE_SQL");

		List<Zipcode> zipcodeList = getSimpleJdbcTemplate().query(
				sql,
				zipcodeRowMapper,
				new Object[] { "%" + addr + "%", "%" + addr + "%",
						"%" + addr + "%" });

		return zipcodeList;
	}

}
