package com.springlab.biz.board.controller2;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String viewName = null;
		System.out.println(">>> 로그인 처리");
		String method = request.getMethod();
			if(method.equalsIgnoreCase("GET")) {
				viewName="login";
				
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
		
		return viewName;
	}

}
