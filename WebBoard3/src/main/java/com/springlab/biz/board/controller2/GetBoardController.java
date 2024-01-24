package com.springlab.biz.board.controller2;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println(">>> 게시글 조회 처리");
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		BoardDAO dao = new BoardDAObyJDBC();
		board = dao.getBoard(board);
		
		request.setAttribute("board", board);
		
		return "getBoard";
	}

}
