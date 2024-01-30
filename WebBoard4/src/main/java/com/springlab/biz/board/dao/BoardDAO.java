package com.springlab.biz.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.springlab.biz.board.domain.BoardDO;

public interface BoardDAO {
	
	// CRUD operations
	public int insertBoard(BoardDO board) throws SQLException;
	public int updateBoard(BoardDO board) throws SQLException;
	public int deleteBoard(BoardDO board) throws SQLException;
	public BoardDO getBoard(BoardDO board) throws SQLException;
	public List<BoardDO> getBoardList(BoardDO board) throws SQLException;
}
