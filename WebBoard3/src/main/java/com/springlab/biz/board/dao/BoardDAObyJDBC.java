package com.springlab.biz.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.springlab.biz.board.domain.BoardDO;

//@Component("boardDAO")
public class BoardDAObyJDBC implements BoardDAO {
	
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
	
	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT) "
									+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ?)";
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=?";
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	private final String BOARD_LIST = "select * from BOARD order by SEQ desc";
	
	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process insertBoard()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getWriter());
			stmt.setString(3, board.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process updateBoard()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setInt(3, board.getCnt());
			stmt.setInt(4, board.getSeq());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process deleteBoard()");
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, board.getSeq());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt,  conn);
		}
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoard()");
		
		BoardDO result = null;
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, board.getSeq());
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = new BoardDO();
				result.setSeq(rs.getInt("SEQ"));
				result.setTitle(rs.getString("TITLE"));
				result.setWriter(rs.getString("WRITER"));
				result.setContent(rs.getString("CONTENT"));
				result.setRegDate(rs.getDate("REGDATE"));
				result.setCnt(rs.getInt("CNT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt,  conn);
		}
		
		return result;
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardDAObyJDBC : process getBoardList()");
		
		List<BoardDO> list = null;
		
		try {
			conn = JDBCUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			if (rs.isBeforeFirst()) {
				list = new ArrayList<BoardDO>();
				while (rs.next()) {
					BoardDO result = new BoardDO();
					result.setSeq(rs.getInt("SEQ"));
					result.setTitle(rs.getString("TITLE"));
					result.setWriter(rs.getString("WRITER"));
					result.setContent(rs.getString("CONTENT"));
					result.setRegDate(rs.getDate("REGDATE"));
					result.setCnt(rs.getInt("CNT"));
					
					list.add(result);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt,  conn);
		}
		
		return list;
	}

}
