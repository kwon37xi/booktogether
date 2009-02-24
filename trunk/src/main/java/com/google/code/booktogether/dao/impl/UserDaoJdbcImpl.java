package com.google.code.booktogether.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

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

	@Resource(name = "dataSource")
	public void setJdbcDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public int deleteUser(int id) {

		String sql = sqlparser.getSQL("user", "DELETE_USER_SQL");

		return getSimpleJdbcTemplate().update(sql, new Object[] { id });
	}

	@Override
	public int getDbCount() {

		String sql = sqlparser.getSQL("user", "DBCOUNT_USER_SQL");

		return getSimpleJdbcTemplate().queryForInt(sql);

	}

	@Override
	public int getLastNumIncrement() {

		String sql = sqlparser.getSQL("user", "GET_LAST_NUM");

		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	@Override
	public List<User> getListUser(Integer startRow, Integer endRow) {

		String sql = sqlparser.getSQL("user", "LIST_USER_SQL");

		return getSimpleJdbcTemplate().query(sql, new UserRowMapper(),
				new Object[] { startRow, endRow });
	}

	@Override
	public User getUser(Integer userId) {

		String sql = sqlparser.getSQL("user", "GET_USER_SQL");

		return (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new UserRowMapper(), new Object[] { userId }));

	}

	@Override
	public int insertUser(User user) {

		String sql = sqlparser.getSQL("user", "INSERT_USER_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getUserId(), user.getEmail(),
						user.getNickname(), user.getName() });

	}

	@Override
	public int modifyUser(User user) {

		String sql = sqlparser.getSQL("user", "MODIFY_USER_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { user.getEmail(), user.getNickname(),
						user.getName(), user.getIdNum() });

	}

	@Override
	public int modifyUserAddInfo(UserAddInfo userAddInfo) {

		String sql = sqlparser.getSQL("user", "MODIFY_USERADDINFO_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userAddInfo.getBlog(),
						userAddInfo.getThumnail(), userAddInfo.getIdNum() });

	}

	@Override
	public int modifyUserPw(UserPw userpw) {

		String sql = sqlparser.getSQL("user", "MODIFY_USER_PW_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userpw.getDigest(), userpw.getSalt(),
						userpw.getUserIdNum() });

	}

	@Override
	public int insertUserPw(UserPw userPw) {

		String sql = sqlparser.getSQL("user", "INSERT_USER_PW_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userPw.getUserIdNum(), userPw.getDigest(),
						userPw.getSalt() });
	}

	@Override
	public UserPw getUserPw(Integer userIdNum) {

		String sql = sqlparser.getSQL("user", "GET_USER_PW_SQL");

		return (UserPw) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new UserPwRowMapper(), new Object[] { userIdNum }));
	}

	@Override
	public User isExistUser(String userId) {

		String sql = sqlparser.getSQL("user", "EXIST_USER_SQL");

		return (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(sql, new UserRowMapper(), new Object[] { userId }));
	}

	@Override
	public String findId(User user) {

		String sql = sqlparser.getSQL("user", "FIND_USER_ID_SQL");

		Class<String> returnValue = null;

		String userId = null;

		try {
			userId = getSimpleJdbcTemplate().queryForObject(sql, returnValue,
					new Object[] { user.getName(), user.getEmail() });
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

		String sql = sqlparser.getSQL("user", "FIND_USER_PW_SQL");

		return (User) DataAccessUtils.singleResult(getSimpleJdbcTemplate()
				.query(
						sql,
						new UserRowMapper(),
						new Object[] { user.getUserId(), user.getName(),
								user.getEmail() }));
	}

	@Override
	public int insertUserAddInfo(UserAddInfo userAddInfo) {

		String sql = sqlparser.getSQL("user", "INSERT_USERADDINFO_SQL");

		return getSimpleJdbcTemplate().update(
				sql,
				new Object[] { userAddInfo.getUserIdNum(),
						userAddInfo.getBlog(), userAddInfo.getThumnail() });
	}

	@Override
	public int insertZone(Zone zone) {

		String sql = sqlparser.getSQL("user", "INSERT_ZONE_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { zone.getUserIdNum(), zone.getZone() });
	}

	@Override
	public List<Zone> getZones(Integer userIdNum) {

		ZoneRowMapper zoneRowMapper = new ZoneRowMapper();

		String sql = sqlparser.getSQL("user", "GET_ZONE_SQL");

		return getSimpleJdbcTemplate().query(sql, zoneRowMapper,
				new Object[] { userIdNum });

	}

	@Override
	public int deleteZone(Integer zoneIdNum, Integer userIdNum) {

		String sql = sqlparser.getSQL("user", "DELETE_ZONE_SQL");

		return getSimpleJdbcTemplate().update(sql,
				new Object[] { zoneIdNum, userIdNum });
	}

	@Override
	public int duplicateUserId(String userId) {

		String sql = sqlparser.getSQL("user", "DUPLICATE_USER_ID_SQL");

		return getSimpleJdbcTemplate()
				.queryForInt(sql, new Object[] { userId });
	}

	@Override
	public List<Zipcode> getListZipcode(String addr) {

		String sql = sqlparser.getSQL("user", "LIST_ZIPCODE_SQL");

		return getSimpleJdbcTemplate().query(
				sql,
				new ZipcodeRowMapper(),
				new Object[] { "%" + addr + "%", "%" + addr + "%",
						"%" + addr + "%" });
	}

}
