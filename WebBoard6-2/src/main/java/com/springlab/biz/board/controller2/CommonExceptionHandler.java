package com.springlab.biz.board.controller2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.springlab.biz.board")
public class CommonExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public String handleArithmeticException(Exception ex, Model model) {
		model.addAttribute("exception", ex);
		return "error/arithmeticException";
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(Exception ex, Model model) {
		model.addAttribute("exception", ex);
		return "error/illegalArgumentException";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {
		model.addAttribute("exception", ex);
		return "error/error";
	}
}
