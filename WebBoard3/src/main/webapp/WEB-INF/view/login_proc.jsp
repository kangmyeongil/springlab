<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.user.dao.*, com.springlab.biz.user.domain.*" %>
<%
	// step 1. get request parameters
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// step 2. data processing by business logic
	UserDO user = new UserDO();
	user.setId(id);
	user.setPassword(password);
	
	UserDAO dao = new UserDAObyJDBC();
	user = dao.getUser(user);
	
	// step 3. ouput results -> rout to views
	if (user != null && password.equals(user.getPassword())) {
		session.setAttribute("user", user);
		response.sendRedirect("getBoardList.jsp");
	}
	else {
		response.sendRedirect("login.jsp");
	}
%>