package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
    
	  // 디비연결정보, mapper접근 -> SqlSession 객체
	@Autowired  
	private SqlSession sqlSession;
	  
	private static final String NAMESPACE
	            = "com.itwillbs.mapper.BoardMapper";

	private static final Logger logger 
	      = LoggerFactory.getLogger(BoardDAOImpl.class);    
	
	@Override
	public void createBoard(BoardVO vo) throws Exception {
	   
		logger.debug("sqlSession 객체 - Mybatis-mapper 객체");
	
		int result = sqlSession.insert(NAMESPACE+".create",vo);
		 // 제대로 입력이 되었을경우 1이 나옴 아닐경우 0
		 if(result != 0)
			  logger.debug("글쓰기 완료");
	}

	@Override
	public List<BoardVO> readBoardListAll() throws Exception {
		
		 logger.debug("readBoardListAll() 호출 !!!@@");
		 
		return sqlSession.selectList(NAMESPACE+".listAll");
	                       // selectList = 리스트를 자동으로 보여줌
	}

	@Override
	public void upViewcnt(Integer bno) throws Exception {
           logger.debug("updateViewcnt(Integet bno) 호출! ");
           sqlSession.update(NAMESPACE + ".upViewcnt",bno);
            
	}

	@Override
	public BoardVO readBoard(Integer bno) throws Exception {
	    logger.debug(" readBoard(Integer bno) 호출");
//		BoardVO vo = sqlSession.selectOne(NAMESPACE+".getBoard",bno);
//		return vo;

	    return sqlSession.selectOne(NAMESPACE+".getBoard",bno);
	}
	
	



	
	
}
