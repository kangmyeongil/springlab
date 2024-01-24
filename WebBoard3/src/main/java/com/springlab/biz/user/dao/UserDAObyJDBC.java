package com.springlab.biz.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.springlab.biz.board.dao.JDBCUtil;
import com.springlab.biz.user.domain.UserDO;

//@Component("userDAO")
public class UserDAObyJDBC implements UserDAO {

//	@Value("${jdbc.driver}")
//	private String jdbcDriver;
//	@Value("${jdbc.url}")
//	private String jdbcUrl;
//	@Value("${jdbc.user}")
//	private String jdbcUser;
//	@Value("${jdbc.password}")
//	private String jdbcPassword;

	private final String jdbcDriver="org.h2.Driver";
	private final String jdbcUrl="jdbc:h2:tcp://localhost/~/test";
	private final String jdbcUser="sa";
	private final String jdbcPassword="1234";
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String USER_INSERT = "insert into USERS(ID, PASSWORD, NAME, ROLE) values(?, ?, ?, ?)";
	private final String USER_UPDATE = "update USERS set PASSWORD=?, NAME=?, ROLE=? where ID=?";
	private final String USER_DELETE = "delete USERS where ID=?";
	private final String USER_GET = "select * from USERS where ID=?";
	private final String USER_LIST = "select * from USERS order by ID";
	
	@Override
	public void insertUser(UserDO user) {
		System.out.println(">>> UserDAObyJDBC : process insertUser()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getRole());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public void updateUser(UserDO user) {
		System.out.println(">>> UserDAObyJDBC : process updateUser()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getRole());
			stmt.setString(4, user.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public void deleteUser(UserDO user) {
		System.out.println(">>> UserDAObyJDBC : process deleteUser()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(USER_DELETE);
			stmt.setString(1, user.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public UserDO getUser(UserDO user) {
		System.out.println(">>> UserDAObyJDBC : process getUser()");
		
		UserDO result = null;
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, user.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = new UserDO();
				result.setId(rs.getString("ID"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setName(rs.getString("NAME"));
				result.setRole(rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
		
		return result;
	}

	@Override
	public List<UserDO> getUserList(UserDO user) {
		System.out.println(">>> UserDAObyJDBC : process getUserList()");
		
		List<UserDO> list = null;
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(USER_LIST);
			rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				list = new ArrayList<UserDO>();
				while (rs.next()) {
					user = new UserDO();
					user.setId(rs.getString("ID"));
					user.setPassword(rs.getString("PASSWORD"));
					user.setName(rs.getString("NAME"));
					user.setRole(rs.getString("ROLE"));
					list.add(user);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
		
		return list;
	}

}
