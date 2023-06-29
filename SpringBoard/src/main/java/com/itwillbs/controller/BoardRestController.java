package com.itwillbs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {


	@Autowired
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
    //http://localhost:8088/controller/board (x)
	//http://localhost:8088/board (o)
	// 주소 : /boards + JSON  (POST)
	   @RequestMapping(value = "", method = RequestMethod.POST)
	    public ResponseEntity<String> addBoard(@RequestBody BoardVO vo) throws Exception {
	        logger.debug("addBoard() 호출");
	        logger.debug("vo : {}", vo);
	        
	        service.insertBoard(vo);
	        
	        return new ResponseEntity<String>("addOK", HttpStatus.OK);
	    }
	   // 주소: /boards  (GET)
	   @RequestMapping(value="", method = RequestMethod.GET)
	   public ResponseEntity<List<BoardVO>> listBoard() throws Exception {
		   logger.debug(" listBoard() 호출");
		   logger.debug(" 주소 : /boards (get방식) ");
		   
		   List<BoardVO> boardList = service.getListAll();
		   
		   return new ResponseEntity<List<BoardVO>>(boardList,HttpStatus.OK);
	   
	   }
}
