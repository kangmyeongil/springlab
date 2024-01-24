package com.springlab.biz.board.controller2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println(">>> 로그아웃 처리");
		
		// step #1. get request parameters

		// step #2. data processing - DB 연동 처리
		// close session
		request.getSession().invalidate();
		
		// step #3. output processing result
		return "login";
	}

}
