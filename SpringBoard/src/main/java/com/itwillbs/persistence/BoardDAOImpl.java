package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	// 디비연결정보, mapper접근 -> SqlSession 객체
	@Autowired
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.itwillbs.mappers.BoardMapper";

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);

	@Override
	public void createBoard(BoardVO vo) throws Exception {

		logger.debug("sqlSession 객체 - Mybatis-mapper 객체");

		int result = sqlSession.insert(NAMESPACE + ".create", vo);
		// 제대로 입력이 되었을경우 1이 나옴 아닐경우 0
		if (result != 0)
			logger.debug("글쓰기 완료");
	}

	@Override
	public List<BoardVO> readBoardListAll() throws Exception {

		logger.debug("readBoardListAll() 호출 !!!@@");

		return sqlSession.selectList(NAMESPACE + ".listPage");
		// selectList = 리스트를 자동으로 보여줌
	}

	@Override
	public void upViewcnt(Integer bno) throws Exception {
		logger.debug("updateViewcnt(Integet bno) 호출! ");
		sqlSession.update(NAMESPACE + ".upViewcnt", bno);

	}

	@Override
	public BoardVO readBoard(Integer bno) throws Exception {
		logger.debug(" readBoard(Integer bno) 호출");
//		BoardVO vo = sqlSession.selectOne(NAMESPACE+".getBoard",bno);
//		return vo;

		return sqlSession.selectOne(NAMESPACE + ".getBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO uvo) throws Exception {

		logger.debug("updateBoard(BoardVO uvo) 호출 ");

		int result = sqlSession.update(NAMESPACE + ".updateBoard", uvo);
		if (result == 1) {
			logger.debug(uvo.getBno() + "글정보 수정 완료! ");
		}
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
        logger.debug("사ㄱ제ㅔㅔㅔㅔㅔㅔㅔㅔ호출 DAODOADOADOADO");
	    int result = sqlSession.delete(NAMESPACE+".deleteBoard",bno);
	   if(result == 1 ) {
		     logger.debug(" 글정보 삭제 완료오오옹옹");
	   }
	}
//	
//	  @Override public void deleteBoard(BoardVO dvo) throws Exception {
//	  
//	  logger.debug("삭제ㅔㅔㅔㅔㅔㅔ호출 DAO");
//	  
//	  int result = sqlSession.delete(NAMESPACE+".deleteBoard", dvo);
//	  
//	  if(result == 1) { logger.debug("글정보 삭제 완료오옹"); } }
//	  

	@Override
	public List<BoardVO> readBoardListPage(Integer page) throws Exception {
		
		logger.debug("페이지ㅣㅣㅣㅣㅣㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ처리@@@@");
		
		if(page <= 0) {
			page = 1;
		}
		// 페이지에 따른 위치 인덱스 계산하기
		// 1 -> 0 , 2 -> 10, 3 -> 20 
		page = (page -1)*10;
		return sqlSession.selectList(NAMESPACE+".listPage",page);
	}

	@Override
	public List<BoardVO> getBoardListPage(PageVO vo) throws Exception {
	    logger.debug("getBoardListPage(PageVO) 호출"); 

	    return sqlSession.selectList(NAMESPACE+".listPage", vo);
	}
	
	
	  
	 
}
