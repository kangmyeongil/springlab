package com.springlab.biz.board.controller2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;
import com.springlab.biz.user.domain.UserDO;
import com.springlab.biz.user.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@Controller
@RequestMapping("/")
@SessionAttributes(value={"user", "board"})
public class BoardController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping(value="login", method=RequestMethod.GET)
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
//	@RequestMapping(value="login", method=RequestMethod.POST)
	@PostMapping("login")
//	public String loginProc(HttpServletRequest request) {
//	public String loginProc(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
	public String loginProc(UserDO user ,Model model) {
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
		
//		UserDO user = new UserDO();
//		user.setId(id);
		

		UserDO user1 = userService.getUser(user);
		
		String viewName = null;
		
		if (user1 != null && user1.getPassword().equals(user.getPassword())) {
//			request.getSession().setAttribute("user", user);
			model.addAttribute("user", user1);
			viewName = "redirect:getBoardList";
		}
		else {
			viewName = "redirect:login";
		}
		
		return viewName;
		
	}
	
	@RequestMapping("getBoardList")
	public String getBoardList(BoardDO board, Model model) {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardService.getBoardList(board);
		
		Map<String, String> conditionMap = new LinkedHashMap<String, String>();
		conditionMap.put("TITLE", "제목");
		conditionMap.put("CONTENT", "내용");
		conditionMap.put("WRITER", "작성자");
		
		model.addAttribute("board_list", boardList);
		model.addAttribute("conditionMap", conditionMap);
		
		return "getBoardList";
	}
	
	@RequestMapping("getBoard")
	public String getBoard(BoardDO board, Model model) {
		System.out.println(">>> 게시글 조회 처리");
		
		
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		BoardDO board = new BoardDO();
//		board.setSeq(seq);
		
		board = boardService.getBoard(board);
		

		String viewName = null;
		if(board!=null) {
			board.setCnt(board.getCnt()+1);
			boardService.updateBoard(board);
			model.addAttribute("board", board);
			viewName = "getBoard";
		}
		else {
			viewName = "redirect:getBoardList";
		}
		
		return viewName;
	}
	
	@GetMapping("insertBoard")
	public String insertBoard(){
		System.out.println(">>> 게시글 등록 처리");
		
		return "insertBoard";
		
	}
	
	@PostMapping("insertBoard")
	public String insertBoardProc(HttpServletRequest request) throws IOException, ServletException {
		
		BoardDO board = new BoardDO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		// upload append file
		
		String uploadDir = request.getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println(uploadDir);
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}
		
		Collection<Part> parts = request.getParts();
		StringBuilder sb = new StringBuilder();
		
		for(Part part : parts) {
			if(!part.getName().equals("uploadFile")) continue;
			if(part.getSize() <= 0) continue;
			
			String fileName = part.getSubmittedFileName();
			sb.append(fileName);
			sb.append(",");
			
			InputStream fis = part.getInputStream();
			// dir-path + file-name
			// /WEB-INF/upload + / + filename
			String filePath = uploadDir + File.separator + fileName;
			
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fis.close();
			fos.close();
			
		}
		int length = sb.length();
		if(length > 0 ) {
			sb.delete(length-1, length);
		}
		board.setUploadFiles(sb.toString());
		System.out.println(board);
		// register to DB
		boardService.insertBoard(board);
		// step #3. output processing result
		return "redirect:getBoardList";
	}
	
	
	@RequestMapping("updateBoard")
	public String updateBoard(@ModelAttribute("board") BoardDO board) {
		System.out.println(">>> 게시글 수정 처리");
//		// step #1. get request parameters
//
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		int cnt = Integer.parseInt(request.getParameter("cnt"));
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		
//		// step #2. data processing - DB 연동 처리
//		BoardDO board = new BoardDO();
//		board.setSeq(seq);
		
		
		BoardDO board1 = boardService.getBoard(board);
		String viewName  = null;
		
		if (!board1.getTitle().equals(board.getTitle()) ||
			!board1.getContent().equals(board.getContent()) || 
			(board1.getCnt() != board.getCnt())) {
//			board1.setTitle(board.getTitle());
//			board1.setContent(board.getContent());
//			board1.setCnt(board.getCnt());
			
			boardService.updateBoard(board);
			viewName = "redirect:getBoardList";
		}
		
		// step #3. output processing result
		return viewName;
	}
	
	@RequestMapping("deleteBoard")
	public String deleteBoard(BoardDO board, Model model) {
		System.out.println(">>> 게시글 삭제 처리");
//		ModelAndView mv = new ModelAndView();
//		// step #1. get request parameters
//		
//
//		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// step #2. data processing - DB 연동 처리
//		BoardDO board = new BoardDO();
//		board.setSeq(seq);
		
		boardService.deleteBoard(board);
		
		// step #3. output processing result
		return "redirect:getBoardList";
		
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		System.out.println(">>> 로그아웃 처리");
		
		// step #1. get request parameters

		// step #2. data processing - DB 연동 처리
		// close session
		session.invalidate();
		// step #3. output processing result
		return "redirect:login";
	}
	
	
}
