package com.springlab.biz.board.controller;

import java.io.IOException;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("view/login.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// step 1. get request parameters
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		// step 2. data processing by business logic
		UserDO user = new UserDO();
		user.setId(id);
		user.setPassword(password);
		
		UserDAO dao = new UserDAObyJDBC();
		user = dao.getUser(user);
		// step 3. output results -> rout to views
		if (user != null && password.equals(user.getPassword())) {
			session.setAttribute("user", user);
			response.sendRedirect("getBoardList.do");
		
		}
		else {
			response.sendRedirect("login.do");
		}
	}

}
