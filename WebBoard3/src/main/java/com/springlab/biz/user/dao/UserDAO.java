package com.springlab.biz.user.dao;

import java.util.List;

import com.springlab.biz.user.domain.UserDO;

public interface UserDAO {

	public void insertUser(UserDO user);
	public void updateUser(UserDO user);
	public void deleteUser(UserDO user);
	public UserDO getUser(UserDO user);
	public List<UserDO> getUserList(UserDO user);
	
}
