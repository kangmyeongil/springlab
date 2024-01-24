package com.springlab.biz.board.controller2;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println(">>> 게시글 수정 처리");
		// step #1. get request parameters

		int seq = Integer.parseInt(request.getParameter("seq"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		BoardDAO dao = new BoardDAObyJDBC();
		board = dao.getBoard(board);
		
		if (!board.getTitle().equals(title) ||
			!board.getContent().equals(content) || 
			(board.getCnt() != cnt)) {
			board.setTitle(title);
			board.setContent(content);
			board.setCnt(cnt);
			
			dao.updateBoard(board);
		}
		
		// step #3. output processing result
		return "redirect:getBoardList.do";
	}

}
