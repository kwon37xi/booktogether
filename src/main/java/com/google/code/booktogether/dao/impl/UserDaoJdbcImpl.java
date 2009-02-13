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
import com.google.code.booktogether.dao.rowmapper.ZoneRowMapper;
import com.google.code.booktogether.service.util.XmlUtil;
import com.google.code.booktogether.web.domain.User;
import com.google.code.booktogether.web.domain.UserAddInfo;
import com.google.code.booktogether.web.domain.UserPw;
import com.google.code.booktogether.web.domain.Zone;


@Repository("userJdbcDao")
public class UserDaoJdbcImpl  extends SimpleJdbcDaoSupport implements UserDao{


	@Resource(name="dataSource")
	public void setJdbcDao(DataSource dataSource){
		setDataSource(dataSource);
	}

	//사용자 RowMapper


	//사용자 비밀번호  RowMapper


	@Override
	public int deleteUser(int id) {

		String sql=XmlUtil.getInstance().getSQL("user","DELETE_USER_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						id
				}
		);

		return count;
	}

	@Override
	public int getDbcount() {

		String sql=XmlUtil.getInstance().getSQL("user","DBCOUNT_USER_SQL");

		int dbcount=getSimpleJdbcTemplate().queryForInt(sql);

		return dbcount;

	}

	@Override
	public int getLastNumIncrement() {

		String sql=XmlUtil.getInstance().getSQL("user","GET_LAST_NUM");

		int last_increment=getSimpleJdbcTemplate().queryForInt(sql);

		return last_increment;
	}

	@Override
	public List<User> getListUser(int startpage, int pageSize) {

		UserRowMapper userRowMapper=new UserRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","LIST_USER_SQL");

		List<User> userlist=getSimpleJdbcTemplate().query(
				sql
				, userRowMapper
				, new Object[]{
						startpage
						,pageSize
				}
		);

		return userlist;
	}

	@Override
	public User getUser(int id) {

		UserRowMapper userRowMapper=new UserRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","GET_USER_SQL");

		User user=(User)DataAccessUtils.singleResult(
				getSimpleJdbcTemplate().query(
						sql
						, userRowMapper
						, new Object[]{
								id
						}
				)
		);

		return user;

	}

	@Override
	public int insertUser(User user) {

		String sql=XmlUtil.getInstance().getSQL("user","INSERT_USER_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						user.getUser_id()
						,user.getEmail()
						,user.getNickname()
						,user.getName()
				}
		);

		return count;

	}

	@Override
	public int modifyUser(User user) {

		String sql=XmlUtil.getInstance().getSQL("user","MODIFY_USER_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						user.getEmail()
						,user.getNickname()
						,user.getName()
						,user.getId()
				}
		);

		return count;

	}

	@Override
	public int modifyUserAddInfo(UserAddInfo userAddInfo) {

		String sql=XmlUtil.getInstance().getSQL("user","MODIFY_USERADDINFO_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						userAddInfo.getBlog()
						,userAddInfo.getThumnail()
						,userAddInfo.getId()
				}

		);

		return count;

	}

	@Override
	public int modifyUserPw(UserPw userpw) {

		String sql=XmlUtil.getInstance().getSQL("user","MODIFY_USER_PW_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						userpw.getDigest()
						, userpw.getSalt()
						, userpw.getUser_id()
				}
		);

		return count;

	}

	@Override
	public int insertUserPw(UserPw userPw) {

		String sql=XmlUtil.getInstance().getSQL("user","INSERT_USER_PW_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						userPw.getUser_id()
						, userPw.getDigest()
						, userPw.getSalt()
				}
		);

		return count;
	}

	@Override
	public UserPw getUserPw(int id) {

		UserPwRowMapper userPwRowMapper=new UserPwRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","GET_USER_PW_SQL");

		UserPw userPw=(UserPw)DataAccessUtils.singleResult(
				getSimpleJdbcTemplate().query(
						sql
						, userPwRowMapper
						, new Object[]{
								id
						}
				)
		);

		return userPw;
	}

	@Override
	public User isExistUser(String user_id) {

		UserRowMapper userRowMapper=new UserRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","EXIST_USER_SQL");

		User user=(User)DataAccessUtils.singleResult(
				getSimpleJdbcTemplate().query(
						sql
						, userRowMapper
						, new Object[]{
								user_id
						}
				)
		);

		return user;
	}

	@Override
	public String findID(User user) {

		UserRowMapper userRowMapper=new UserRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","FIND_USER_ID_SQL");

		user=(User)DataAccessUtils.singleResult(
				getSimpleJdbcTemplate().query(
						sql
						, userRowMapper
						, new Object[]{
								user.getName()
								, user.getEmail()
						}
				)
		);

		return user.getUser_id();

	}

	@Override
	public User findPW(User user) {

		UserRowMapper userRowMapper=new UserRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","FIND_USER_PW_SQL");

		user=(User)DataAccessUtils.singleResult(
				getSimpleJdbcTemplate().query(
						sql
						, userRowMapper
						, new Object[]{
								user.getUser_id()
								, user.getName()
								, user.getEmail()
						}
				)
		);

		return user;

	}

	@Override
	public int insertUserAddInfo(UserAddInfo userAddInfo) {
		String sql=XmlUtil.getInstance().getSQL("user","INSERT_USERADDINFO_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						userAddInfo.getUser_id()
						, userAddInfo.getBlog()
						, userAddInfo.getThumnail()
				}
		);

		return count;
	}

	@Override
	public int insertZone(Zone zone) {

		String sql=XmlUtil.getInstance().getSQL("user","INSERT_ZONE_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						zone.getUser_id()
						,zone.getZone()
				}
		);

		return count;
	}

	@Override
	public List<Zone> getZones(int id) {

		ZoneRowMapper zoneRowMapper=new ZoneRowMapper();

		String sql=XmlUtil.getInstance().getSQL("user","GET_ZONE_SQL");

		List<Zone> zonelist=getSimpleJdbcTemplate().query(
				sql
				,zoneRowMapper
				,new Object[]{
						id
				}
		);

		return zonelist;

	}

	@Override
	public int deleteZone(int zone_id, int id) {

		String sql=XmlUtil.getInstance().getSQL("user","DELETE_ZONE_SQL");

		int count=getSimpleJdbcTemplate().update(
				sql
				, new Object[]{
						zone_id
						, id
				}
		);

		return count;
	}

	@Override
	public int duplicateUser_id(String user_id) {
		
		String sql=XmlUtil.getInstance().getSQL("user","DUPLICATE_USER_ID_SQL");

		int count=getSimpleJdbcTemplate().queryForInt(
				sql
				, new Object[]{
						user_id
				}
		);

		return count;
	}


}
