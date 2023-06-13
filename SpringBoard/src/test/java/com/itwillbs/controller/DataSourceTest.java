package com.itwillbs.controller;

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

	 private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	 
	 @Test
     public void DS_ConnectText() {
         	 logger.debug("ds : " +ds);
     }
	 @Test
	 public void CPTest() {
	     logger.debug(" @@@@ sqlSession : " + sqlSession);	 
	 }
}
