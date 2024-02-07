package com.springlab.biz.board.controller2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@Slf4j
class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
//	@GetMapping("getBoard")
//	public String getBoard(BoardDO board) throws JsonProcessingException {
//		log.info(">>> 게시글 API 처리");
//		
//		board = boardService.getBoard(board);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String result = mapper.writeValueAsString(board);
//
//		
//		if(board!=null) {
//			board.setCnt(board.getCnt()+1);
//			boardService.updateBoard(board);
//		}
//		
//		return result;
//	}
	
	
//	@GetMapping("getBoard")
//	public BoardDO getBoard(BoardDO board) throws JsonProcessingException {
//		log.info(">>> 게시글 API 처리");
//
//		board = boardService.getBoard(board);
//		
//		if(board!=null) {
//			board.setCnt(board.getCnt()+1);
//			boardService.updateBoard(board);
//		}
//		return board;
//	}
	
	
	
	@GetMapping("getBoard")
	public ResponseEntity<BoardDO> getBoard(BoardDO board) throws JsonProcessingException {
		log.info(">>> 게시글 API 처리");

		board = boardService.getBoard(board);
		
		if(board==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			board.setCnt(board.getCnt()+1);
			boardService.updateBoard(board);
			//return ResponseEntity.ok().body(board);
			return ResponseEntity.ok(board);

		}
	}
	/*
	@GetMapping("getBoardList")
	public ResponseEntity<List<BoardDO>> getBoardList(BoardDO board) {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardService.getBoardList(board);
		
		if(boardList==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(boardList);
		}
		
	}
	*/
	@GetMapping("getBoardList")
	public ResponseEntity<Result> getBoardList(BoardDO board) {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardService.getBoardList(board);
		
		if(boardList==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(new Result(boardList, boardList.size()));
		}
	}
	
	@Getter
	@AllArgsConstructor
	private class Result{
		private List<BoardDO> boardList;
		private int count;
	}
}
