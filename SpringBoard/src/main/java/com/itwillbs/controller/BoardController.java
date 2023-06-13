package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value ="/board/*") // ~.board 특정 주소에 따른 컨트롤러 구분
public class BoardController {
	 
	// mylog
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
  
	// 글쓰기 - /board/regist (GET)
	// http://localhost:8088/board/regist
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug(" regisGET() 호출! ");
		logger.debug(" /board/regist.jsp 페이지 이동 ");
	
	}
	// 글쓰기 - /board/regist (POST)
	
}
