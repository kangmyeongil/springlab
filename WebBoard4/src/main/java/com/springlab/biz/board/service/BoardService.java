package com.springlab.biz.board.service;

import java.sql.SQLException;
import java.util.List;

import com.springlab.biz.board.domain.BoardDO;

public interface BoardService {
	public int insertBoard(BoardDO board);
	public int updateBoard(BoardDO board);
	public int deleteBoard(BoardDO board);
	public BoardDO getBoard(BoardDO board);
	public List<BoardDO> getBoardList(BoardDO board);

}
