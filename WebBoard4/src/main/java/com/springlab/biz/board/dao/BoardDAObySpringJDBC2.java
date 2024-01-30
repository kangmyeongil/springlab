package com.springlab.biz.board.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

@Repository("boardDAO")
public class BoardDAObySpringJDBC2 extends JdbcDaoSupport implements BoardDAO {
	
	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT) "
			+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ? )";
	
	private final String BOARD_INSERT2 = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT) "
			+ "values(?, ?, ?, ? )";
	
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=? ";
	
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	
	private final String BOARD_LIST = "select * from BOARD order by SEQ desc";
	
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	
	@Override
	public int insertBoard(BoardDO board) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> BoardDAObySpringJDBC : execute insertBoard()");
		jdbcTemplate.update(BOARD_INSERT, board.getTitle(), board.getWriter(), board.getContent());
		return 0;
	}
	
//	public int insertBoard(BoardDO board) throws SQLException {
//		// TODO Auto-generated method stub
//		System.out.println(">>> BoardDAObySpringJDBC : execute insertBoard()");
//		jdbcTemplate.update(BOARD_INSERT, board.getSeq() ,board.getTitle(), board.getWriter(), board.getContent());
//		return 0;
//	}

	@Override
	public int updateBoard(BoardDO board) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> BoardDAObySpringJDBC : execute updateBoard()");
		jdbcTemplate.update(BOARD_UPDATE, board.getTitle(), board.getContent(), board.getCnt(),board.getSeq());
		return 0;
	}

	@Override
	public int deleteBoard(BoardDO board) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> BoardDAObySpringJDBC : execute deleteBoard()");
		jdbcTemplate.update(BOARD_DELETE, board.getSeq());
		return 0;
	}

	@Override
	public BoardDO getBoard(BoardDO board) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> BoardDAObySpringJDBC : execute getBoard()");
		
		return jdbcTemplate.queryForObject(BOARD_GET, boardRowMapper, board.getSeq());
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(">>> BoardDAObySpringJDBC : execute getBoardList()");
		
		return jdbcTemplate.query(BOARD_LIST, boardRowMapper);
	}

}
