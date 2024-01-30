package com.springlab.biz.board.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				System.out.println(">>> 로그아웃 처리");
				
				// step #1. get request parameters

				// step #2. data processing - DB 연동 처리
				// close session
				request.getSession().invalidate();
				// step #3. output processing result
				return new ModelAndView("redirect:login");
	}

}
