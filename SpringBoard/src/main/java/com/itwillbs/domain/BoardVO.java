package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int viewcnt;
    private Timestamp regdate;
    
//    private String sBoard; -> setSBoard() 생성 -> el표현식 SBoard 호출
    
}


