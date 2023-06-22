package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionController.class);

	// http://localhost:8088/board/read?bno=A 
	@ExceptionHandler(Exception.class)
	public String common(Model model, Exception e) {
		logger.debug(" 예외발생!!! ");
		model.addAttribute("err", e);
		
		return "/err/myError";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView common2(Exception e) {
		logger.debug(" NULLPOINTER 예외발생!!! ");
	    
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/err/myError");
		mav.addObject(e);
		
		return mav;
		 
	}
	
}
