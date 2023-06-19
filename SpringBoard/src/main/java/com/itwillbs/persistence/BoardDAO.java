package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {

	 // Board관련된 DB 동작 선언 / 호출
     
	 // 글쓰기 - C
     public void createBoard(BoardVO vo) throws Exception; 
	  
     // 글목록 조회 - R
     public List<BoardVO> readBoardListAll() throws Exception;

     // 글 조회수 1 증가 - U
     public void upViewcnt(Integer bno) throws Exception;
     
     // 특정 글정보 조회 - R
     public BoardVO readBoard(Integer bno) throws Exception;
     
     // 특정 글정보 수정 - U
     public void updateBoard(BoardVO uvo) throws Exception;

     // 특정 글정보 삭제 - D 
     public void deleteBoard(Integer bno) throws Exception;
 }
