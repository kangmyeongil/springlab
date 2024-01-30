package com.springlab.biz.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Override
	public int insertUser(UserDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.insertUser(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(UserDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.updateUser(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(UserDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.deleteUser(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public UserDO getUser(UserDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.getUser(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDO> getUserList(UserDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.getUserList(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
