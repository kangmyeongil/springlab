package com.springlab.biz.board.controller2;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println(">>> 게시글 등록 처리");
		String viewName = null;
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("GET")) {
			viewName="insertBoard";
			
		}
		else if(method.equalsIgnoreCase("POST")) {
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");	

			// step #2. data processing - DB 연동 처리
			BoardDO board = new BoardDO();
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			BoardDAO dao = new BoardDAObyJDBC();
			dao.insertBoard(board);
			
			// step #3. output processing result
			viewName = "redirect:getBoardList.do";
		}
		return viewName;
	}

}
