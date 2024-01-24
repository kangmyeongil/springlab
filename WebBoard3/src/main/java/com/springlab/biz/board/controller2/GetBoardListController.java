package com.springlab.biz.board.controller2;

import java.util.List;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 게시글 목록 처리");
		
		BoardDAO dao = new BoardDAObyJDBC();
		List<BoardDO> boardList = dao.getBoardList(null);
		
		request.setAttribute("board_list", boardList);
		
		return "getBoardList";
	}

}
