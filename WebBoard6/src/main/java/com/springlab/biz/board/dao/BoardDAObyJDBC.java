package com.springlab.biz.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

//@Component("boardDAO")
public class BoardDAObyJDBC implements BoardDAO {

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
	
	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT) "
			+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ? )";
	
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=? ";
	
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	
	private final String BOARD_LIST = "select * from BOARD where ? like '%'||?||'%' order by SEQ desc";
	
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
	
	public int insertBoard(BoardDO board) throws SQLException{
		System.out.println(">>> BoardDAObyJDBC : execute insertBoard()");
		// 1. get DBconnection
		conn = getConnection();
		// 2. get SQL statement object
		stmt = conn.prepareStatement(BOARD_INSERT);
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getWriter());
		stmt.setString(3, board.getContent());
		
		// 3. execute SQL statement
		int result = stmt.executeUpdate();
		
		// 4. process result
		// 5. disconnect DB
		close();
		
		return result;
		
	}
	
	
	public int updateBoard(BoardDO board) throws SQLException{
		System.out.println(">>> BoardDAObyJDBC : execute updateBoard()");
		conn = getConnection();
		stmt = conn.prepareStatement(BOARD_UPDATE);
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getContent());
		stmt.setInt(3, board.getCnt());
		stmt.setInt(4, board.getSeq());
		
		int result = stmt.executeUpdate();
		close();
		
		return result;
		
	}
	public int deleteBoard(BoardDO board) throws SQLException{
		System.out.println(">>> BoardDAObyJDBC : execute deleteBoard()");
		
		conn = getConnection();
		stmt = conn.prepareStatement(BOARD_DELETE);
		stmt.setInt(1, board.getSeq());
		
		int result = stmt.executeUpdate();
		close();
		
		return result;
		
	}
	public BoardDO getBoard(BoardDO board) throws SQLException{
		System.out.println(">>> BoardDAObyJDBC : execute getBoard()");
		conn = getConnection();
		stmt = conn.prepareStatement(BOARD_GET);
		stmt.setInt(1, board.getSeq());
		BoardDO result = null;
		rs = stmt.executeQuery();
		if(rs.next()) {
			result = new BoardDO();
			result.setSeq(rs.getInt("SEQ"));
			result.setTitle(rs.getString("TITLE"));
			result.setWriter(rs.getString("WRITER"));
			result.setContent(rs.getString("CONTENT"));
			result.setRegDate(rs.getTimestamp("REGDATE"));
			result.setCnt(rs.getInt("CNT"));
		}
		
		
		close();
		
		return result;
	}
	public List<BoardDO> getBoardList(BoardDO board) throws SQLException{
		System.out.println(">>> BoardDAObyJDBC : execute ListBoard()");
		conn = getConnection();
		stmt = conn.prepareStatement(BOARD_LIST);
		
		stmt.setString(1, board.getSearchCondition());
		stmt.setString(2, board.getSearchKeyword());
		
		rs = stmt.executeQuery();
		List<BoardDO> list = null;
		if(rs.isBeforeFirst()) {
			list = new ArrayList<BoardDO>();
			while(rs.next()) {
				BoardDO result = new BoardDO();
				result.setSeq(rs.getInt("SEQ"));
				result.setTitle(rs.getString("TITLE"));
				result.setWriter(rs.getString("WRITER"));
				result.setContent(rs.getString("CONTENT"));
				result.setRegDate(rs.getTimestamp("REGDATE"));
				result.setCnt(rs.getInt("CNT"));
				
				list.add(result);
			}
		}
		
		
		
		close();
		
		return list;
	}
	
	
}
