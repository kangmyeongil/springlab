package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertBoardController implements Controller {

	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 등록 처리");
		String method = request.getMethod();
		ModelAndView mv = new ModelAndView();
		if(method.equalsIgnoreCase("GET")) {
			mv.setViewName("insertBoard");
			
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
			
			
			boardService.insertBoard(board);
			
			
			
			// step #3. output processing result
			mv.setViewName("redirect:getBoardList");
		}
		return mv;
	}

}
