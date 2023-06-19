package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {

	  // Board에서 수행하는 동작 선언
	
	  // 글쓰기 - Create
	  public void  insertBoard(BoardVO vo) throws Exception;
	  
	  // 글 목록 조회 - Read
	  public List<BoardVO> getListAll() throws Exception;
	  
	  // 글 조회수 1증가 - Update
	  public void updateViewcnt(Integer bno) throws Exception;
	  
	  // 특정 글정보 조회 - Read
      public BoardVO getBoard(Integer bno) throws Exception;

      // 특정 글정보 수정 - Update
      public void modifyBoard(BoardVO uvo) throws Exception;
	
}
