<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.board.dao.*, com.springlab.biz.board.domain.*" %>
<%
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
%>