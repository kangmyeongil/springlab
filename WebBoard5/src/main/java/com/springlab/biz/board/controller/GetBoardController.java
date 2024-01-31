package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardController implements Controller {

	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 조회 처리");
		
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		board = boardService.getBoard(board);
		
		ModelAndView mv = new ModelAndView();
		
		if(board!=null) {
			board.setCnt(board.getCnt()+1);
			boardService.updateBoard(board);
			mv.addObject("board", board);
			mv.setViewName("getBoard");
		}
		else {
			mv.setViewName("redirect:getBoardList");
		}
		
		return mv;
	}

}
