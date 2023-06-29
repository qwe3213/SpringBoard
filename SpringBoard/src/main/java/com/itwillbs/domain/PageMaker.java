package com.itwillbs.domain;

public class PageMaker {

	// 하단부 페이징처리 
	
	
	private int totalCount; // 전체 글의 개수 
	private int startPage;  // 페이지 시작번호
	private int endPage;    // 페이지 끝번호
	private boolean prev;   // 이전
	private boolean next;   // 다음
	
	private int pageBlock = 10;  // 페이지 블럭의 수
	
//	private int page;      // 페이지 번호
//	private int pageSize;  // 페이지 크기
	
    private PageVO pageVO;

    public void setPageVO(PageVO pageVO) {
    	this.pageVO = pageVO;
    }

    public void setTotalCount(int totalCount) {
    	this.totalCount = totalCount;
        //  DB에서 계산된 정보를 가져오기
    	calcMyPage();
    }
    
    public void calcMyPage() {
    	// 페이징처리 하단 필요한 정보 계산 - 시작
    	
    	endPage = (int)Math.ceil(pageVO.getPage()/(double)pageBlock) * pageBlock;
    	
    	startPage
    	  = (endPage - pageBlock) + 1;
    	
    	int tmpEndpage = (int)Math.ceil(totalCount / (double)pageVO.getPageSize());
    	if(endPage > tmpEndpage) {
    		endPage = tmpEndpage;
    	}

        //   prev = (startPage == 1)? false : true;
    	prev = startPage != 1;
    	
    	next = (endPage * pageVO.getPageSize() >= totalCount)? false : true;
    	
    	
    	// 페이징처리 하단 필요한 정보 계산 - 끝
    }

    public int getTotalCount() {
		return totalCount;
	}
	
    
	

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public PageVO getPageVO() {
		return pageVO;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", pageBlock=" + pageBlock + ", pageVO=" + pageVO + "]";
	}

	
    
}
