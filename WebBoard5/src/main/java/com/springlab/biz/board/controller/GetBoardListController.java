package com.springlab.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardListController implements Controller {
	@Autowired
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 목록 처리");
		
		ModelAndView mv = new ModelAndView("getBoardList");
		List<BoardDO> boardList = boardService.getBoardList(null);
		
		mv.addObject("board_list", boardList);
		
		return mv;
	}

}
