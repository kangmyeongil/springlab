package com.springlab.biz.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.springlab.biz.user.domain.UserDO;

@Repository("userDAO")
public class UserDAObyJDBC implements UserDAO {
	
	@Value("${jdbc.driverClass}")
	private String driverClass;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String USER_INSERT = "insert into USERS(ID, PASSWORD, NAME, ROLE) "
			+ "values(?, ?, ?, ? )";
	
	private final String USER_UPDATE = "update USERS set NAME=?, ROLE=? where ID=? ";
	
	private final String USER_DELETE = "delete from USERS where ID=?";
	
	private final String USER_GET = "select * from USERS where ID=?";
	
	private final String USER_LIST = "select * from USERS order by ID desc";
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				url, user, password);
	}

	public void close() throws SQLException{
		if(rs!=null) {
		    if (!rs.isClosed()) {
		    	rs.close();
		    	rs = null;
		    }
		}
		if(stmt!=null) {
		if (!stmt.isClosed()) {
			stmt.close();
			stmt = null;
		}
		}
		if(conn!=null) {
		if (!conn.isClosed()) {
			conn.close();
			conn = null;
		}
		}
	}
	
	
	
	
	@Override
	public int insertUser(UserDO user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> UserDAObyJDBC : execute insertUser()");
		// 1. get DBconnection
		conn = getConnection();
		// 2. get SQL statement object
		stmt = conn.prepareStatement(USER_INSERT);
		stmt.setString(1,  user.getId());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getName());
		stmt.setString(4, user.getRole());
		
		// 3. execute SQL statement
		int result = stmt.executeUpdate();
		
		// 4. process result
		// 5. disconnect DB
		close();
		
		return result;
	}

	@Override
	public int updateUser(UserDO user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> UserDAObyJDBC : execute updateUser()");
		// 1. get DBconnection
		conn = getConnection();
		// 2. get SQL statement object
		stmt = conn.prepareStatement(USER_UPDATE);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getRole());
		stmt.setString(3, user.getId());
		
		// 3. execute SQL statement
		int result = stmt.executeUpdate();
		
		// 4. process result
		// 5. disconnect DB
		close();
		
		return result;
	}

	@Override
	public int deleteUser(UserDO user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> UserDAObyJDBC : execute deleteUser()");
		// 1. get DBconnection
		conn = getConnection();
		// 2. get SQL statement object
		stmt = conn.prepareStatement(USER_DELETE);
		stmt.setString(1, user.getId());
		
		// 3. execute SQL statement
		int result = stmt.executeUpdate();
		
		// 4. process result
		// 5. disconnect DB
		close();
		
		return result;
	}

	@Override
	public UserDO getUser(UserDO user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> UserDAObyJDBC : execute getUser()");
		conn = getConnection();
		stmt = conn.prepareStatement(USER_GET);
		stmt.setString(1, user.getId());
		UserDO result = null;
		rs = stmt.executeQuery();
		if(rs.next()) {
			result = new UserDO();
			result.setId(rs.getString("ID"));
			result.setPassword(rs.getString("PASSWORD"));
			result.setName(rs.getString("NAME"));
			result.setRole(rs.getString("ROLE"));
		}
		close();
		
		return result;
	}

	@Override
	public List<UserDO> getUserList(UserDO user) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> UserDAObyJDBC : execute getUserList()");
		conn = getConnection();
		stmt = conn.prepareStatement(USER_LIST);
		List<UserDO> list = null;
		rs = stmt.executeQuery();
		if(rs.isBeforeFirst()) {
			list = new ArrayList<UserDO>();
			if(rs.next()) {
				UserDO result = new UserDO();
				result.setId(rs.getString("ID"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setName(rs.getString("NAME"));
				result.setRole(rs.getString("ROLE"));
				list.add(result);
			}
		}
		close();
		
		return list;
	}

}
