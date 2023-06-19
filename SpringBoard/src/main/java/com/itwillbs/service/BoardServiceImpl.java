package com.itwillbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bdao;
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
	   
		//  DAO 객체 필요 => 개체 주입
		  bdao.createBoard(vo);
		// DAO - 글정보 저장 기능 호출
        
		 
		
	}

	@Override
	public List<BoardVO> getListAll() throws Exception {
		
		
		//DAO 글목록 가져오기
		return bdao.readBoardListAll();
	}

	@Override
	public void updateViewcnt(Integer bno) throws Exception {
	
		bdao.upViewcnt(bno);
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		
		return bdao.readBoard(bno);
	}

	@Override
	public void modifyBoard(BoardVO uvo) throws Exception {
		bdao.updateBoard(uvo);
	}
	
	

	
	
	
}
