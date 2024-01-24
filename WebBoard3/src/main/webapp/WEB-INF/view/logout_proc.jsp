<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.board.dao.*, com.springlab.biz.board.domain.*" %>
<%
	// step #1. get request parameters

	// step #2. data processing - DB 연동 처리
	// close session
	session.invalidate();
	
	// step #3. output processing result
	response.sendRedirect("login.jsp");
%>