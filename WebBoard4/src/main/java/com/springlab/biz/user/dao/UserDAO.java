package com.springlab.biz.user.dao;

import java.sql.SQLException;
import java.util.List;

import com.springlab.biz.user.domain.UserDO;

public interface UserDAO {
	public int insertUser(UserDO borad) throws SQLException;
	public int updateUser(UserDO borad) throws SQLException;
	public int deleteUser(UserDO borad) throws SQLException;
	public UserDO getUser(UserDO borad) throws SQLException;
	public List<UserDO> getUserList(UserDO borad) throws SQLException;
}
