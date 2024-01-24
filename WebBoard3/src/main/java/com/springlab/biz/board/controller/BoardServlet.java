package com.springlab.biz.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

/**
 * Servlet implementation class BoardServlet
 */
//@WebServlet("*.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(uri+"\n"+path);
		String viewName = null;
		HttpSession session = request.getSession();
		// step #2. data processing
		if(path.equals("/login.do")) {
			System.out.println(">>> 로그인 처리");
			String method = request.getMethod();
			if(method.equalsIgnoreCase("GET")) {
				viewName="view/login_proc.jsp";
			}
			else if(method.equalsIgnoreCase("POST")) {
				
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO user = new UserDO();
			user.setId(id);
			
			UserDAO dao = new UserDAObyJDBC();
			user = dao.getUser(user);
			
			if (user != null && password.equals(user.getPassword())) {
				request.getSession().setAttribute("user", user);
				viewName = "redirect:getBoardList.do";
			}
			else {
				viewName="redircet:login.do";
			}
		
			}
		}
		else if (path.equals("/logout.do")) {
			System.out.println(">>> 로그아웃 처리");
			
			// step #1. get request parameters

			// step #2. data processing - DB 연동 처리
			// close session
			session.invalidate();
			
			// step #3. output processing result
			viewName="redirect:login.jsp";
		}
		
		else if (path.equals("/insertBoard.do")) {
			System.out.println(">>> 게시글 등록 처리");
			
			String method = request.getMethod();
			
			if(method.equalsIgnoreCase("GET")) {
				viewName="jsp/insertBoard.jsp";
				
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
				response.sendRedirect("getBoardList.do");
			}
		}
		
		else if (path.equals("/updateBoard.do")) {
			System.out.println(">>> 게시글 수정 처리");
			// step #1. get request parameters
			request.setCharacterEncoding("UTF-8");

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
			response.sendRedirect("getBoardList.jsp");
		}
		else if (path.equals("/deleteBoard.do")) {
			System.out.println(">>> 게시글 삭제 처리");
			
			// step #1. get request parameters
			
			request.setCharacterEncoding("UTF-8");

			int seq = Integer.parseInt(request.getParameter("seq"));
			
			// step #2. data processing - DB 연동 처리
			BoardDO board = new BoardDO();
			board.setSeq(seq);
			
			BoardDAO dao = new BoardDAObyJDBC();
			dao.deleteBoard(board);
			
			// step #3. output processing result
			response.sendRedirect("getBoardList.jsp");
		}
		else if (path.equals("/getBoard.do")) {
			System.out.println(">>> 게시글 조회 처리");
			int seq = Integer.parseInt(request.getParameter("seq"));
			BoardDO board = new BoardDO();
			board.setSeq(seq);
			
			BoardDAO dao = new BoardDAObyJDBC();
			board = dao.getBoard(board);
			
			request.setAttribute("board", board);
			
			viewName = "jsp/getBoard.jsp";
		}
		else if (path.equals("/getBoardList.do")) {
			System.out.println(">>> 게시글 목록 처리");
			
			BoardDAO dao = new BoardDAObyJDBC();
			List<BoardDO> boardList = dao.getBoardList(null);
			
			request.setAttribute("board_list", boardList);
			
			viewName = "redirect:getBoardList.jsp";
		}
		// step #3. output results
		if(viewName != null) {
			if(viewName.contains("redirect:")) {
				response.sendRedirect(viewName.substring(viewName.lastIndexOf(":")+1));
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
