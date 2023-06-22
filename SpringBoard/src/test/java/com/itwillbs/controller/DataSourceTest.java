package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations =  {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {

	 // DataSource 객체를 주입
	 @Inject
	 private DataSource ds;
 
	 
     // SqlSession 객체 주입
	 @Autowired
	 private SqlSession sqlSession;

	 @Autowired
	 private BoardDAO bado;
	 
	 private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	 
	// @Test
     public void DS_ConnectText() {
         	 logger.debug("ds : " +ds);
     }
	// @Test
	 public void CPTest() {
	     logger.debug(" @@@@ sqlSession : " + sqlSession);	 
	 }
	 
	 @Test
	 public void 리스트_페이징처리() {
		 
		 // sqlSession.selectList("com.itwillbs.mapper.BoardMapper.listPage",1);
          try {
		  List<BoardVO> boardList = bado.readBoardListPage(1);

          for(BoardVO vo : boardList) {
        	    logger.debug(vo.getBno()+ " : " + vo.getTitle());
          }
		  
          } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 @Test
	 public void 리스트_페이징처리2() throws Exception {
		// 페이징처리 정보
		PageVO pvo = new PageVO();
		pvo.setPage(2);
		//페이지 설정
		pvo.setPageSize(30);
		//페이지 사이즈 설정
		List<BoardVO> boardList = bado.getBoardListPage(pvo);
		
		for(BoardVO vo : boardList) {
    	    logger.debug(vo.getBno()+ " : " + vo.getTitle());
      }
	  
	 }
}
