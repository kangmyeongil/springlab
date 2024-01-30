package com.springlab.biz.board.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

@Service("boardService")
@Transactional(readOnly=true, rollbackFor=Throwable.class)
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("boardDAO")
	private BoardDAO dao;

	@Transactional(readOnly=false, rollbackFor=SQLException.class)
	@Override
	public int insertBoard(BoardDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.insertBoard(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Transactional(readOnly=false)
	@Override
	public int updateBoard(BoardDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.updateBoard(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteBoard(BoardDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.deleteBoard(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.getBoard(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		// TODO Auto-generated method stub
		try {
			return dao.getBoardList(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
