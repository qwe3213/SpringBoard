package com.itwillbs.domain;

import lombok.Data;

//@Data
public class PageVO {

	// 페이지 정보 저장 객체
	// 페이지 정보
	
	private int page;
	// 페이지 크기(한페이지에 출력될 크기)
	private int pageSize;
	
	// 페이지 정보에 따른 인덱스 정보
    //  private int pageIndex; (저장x)
    
	public void setPage(int page) {
		// 0이하일경우 1로 설정하기
        if(page <=0) {
        	 this.page = 1;
             return;
        }
		this.page = page;
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100) {
			 this.pageSize = 10;
			 return;
		}
		
		this.pageSize = pageSize;
	}
    
	// get()는 mapper에서 사용 ${OOO} 사용 
	// => VO객체의 getOOO() 메서드 의미함.
	public int getPage() {
		return page;
	}

	public int getPageSize() {

		return pageSize;
	}
	
	// 페이지에 따른 시작인덱스 계산
	public int getStartPage() {
		
		return (this.page - 1) * this.pageSize;
	}
	
	public PageVO() {
		
		// 페이징처리의 기본값 설정
		this.page = 1;
		this.pageSize = 10;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}
