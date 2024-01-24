package com.springlab.biz.board.controller2;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println(">>> 게시글 삭제 처리");
		
		// step #1. get request parameters
		

		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		BoardDAO dao = new BoardDAObyJDBC();
		dao.deleteBoard(board);
		
		// step #3. output processing result
		return "redirect:getBoardList.do";
	}

}
