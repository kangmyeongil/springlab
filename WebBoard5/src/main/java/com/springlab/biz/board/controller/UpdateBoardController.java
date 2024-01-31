package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateBoardController implements Controller {

	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 수정 처리");
		// step #1. get request parameters

		int seq = Integer.parseInt(request.getParameter("seq"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		
		board = boardService.getBoard(board);
		ModelAndView mv = new ModelAndView();
		
		if (!board.getTitle().equals(title) ||
			!board.getContent().equals(content) || 
			(board.getCnt() != cnt)) {
			board.setTitle(title);
			board.setContent(content);
			board.setCnt(cnt);
			
			boardService.updateBoard(board);
			mv.setViewName("redirect:getBoardList");
		}
		
		// step #3. output processing result
		return mv;
	}

}
