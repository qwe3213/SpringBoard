package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value ="/board/*") // ~.board 특정 주소에 따른 컨트롤러 구분
public class BoardController {
	
	// 404 error -> no Mapping
	
	// 서비스 객체 주입
	
	@Autowired
	private BoardService service;
	
	// mylog
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
  
	// 글쓰기 - /board/regist (GET)
	// http://localhost:8088/board/regist
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug(" regisGET() 호출! ");
		logger.debug(" /board/regist.jsp 페이지 이동 ");
	
	}
	
   	
	// 글쓰기 - /board/regist (POST)
	@RequestMapping(value= "/regist", method = RequestMethod.POST )
	public String refistPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
	    logger.debug(" registPOST() 호출");
		// 한글처리(필터)	
		// 페이지 전달된 데이터(파라메터) 저장
//   	logger.debug("vo 출려어어어억:" +vo);
  		logger.debug("vo : {}",vo); // err 레벨에서 사용권장.
  		logger.error("msg");
		// 서비스 - 글쓰기 동작 호출 
		service.insertBoard(vo);
		
		// 리스트로 정보를 전달 (rttr)
		 rttr.addFlashAttribute("result", "CREATEOK");
		
		// 리스트 페이지로 이동
		return "redirect:/board/listALL"; // Model객체(ModelAttribute)
		// 글쓰고 게시판으로 갔는지 확인하기 위해 조수줄에 ?test=12345를 씀
		
	}
	
	// http://localhost:8088/board/listALL
	// http://localhost:8088/board/listALL?page=2&pageSize=30
	// 게시판 글 목록
	@RequestMapping(value ="/listALL", method = RequestMethod.GET)
	public String listALLGET(PageVO vo, HttpSession session , Model model,@ModelAttribute("result") String result) throws Exception{
        
		 logger.debug(" listALLGET() 호출 !! ");
		 logger.debug("result : " + result);
		 // DB에 저장된 글 정보를 가져오기
		
		 // 서비스 - DB에 저장된 값 가져오기
		 
//		 PageVO vo = new PageVO();  page =1, pageSize = 10
		 
		 // 페이징처리된 리스트 정보 가져오기
		 List<BoardVO> boardList = service.getBoardListPage(vo);
		 
		 logger.debug(" boardList : "  +boardList);
		 // 연결된 뷰페이지로 전달 (뷰 - 출력)
		 model.addAttribute("boardList",boardList);
		 
		 // 조회수 체크 값
		 session.setAttribute("checkViewCnt",true );
		 
		 //연결된 뷰페이지로 전달 (뷰 - 출력)
		 model.addAttribute("boardList", boardList);
		 
		return "/board/listALL";
	}
	
//	http://localhost:8088/board/read?bno=4
	// 글 내용(본문)보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(Model model,HttpSession session, @RequestParam("bno") int bno) throws Exception{
		// @RequestParam => getParmater() , 1:1 매핑 , 자동으로 타입캐스팅 (형변환)
		// @ModelAttribute => getParmater() + Model, 1 : N 매핑
         
		logger.debug("readGET() 호출! ");
		
		// 전달정보 저장(bno)
		logger.debug("bno : " + bno);
		
		// 전달받은정보 저장 (bno)
		
		boolean checkValue = (Boolean)session.getAttribute("checkViewCnt");
		
		if(checkValue) {
			
			// 조회수 1 증가 (checkViewCnt 정보가 참일때만)
			// => 서비스 동작 호출 
			service.updateViewcnt(bno);
		    session.setAttribute("checkViewCnt", false);
		}
		
		  service.getBoard(bno);
		
		// 글정보 조회(특정글)
		// 글정보를 Model 저장 => 연결된 뷰페이지로 전달.
		  model.addAttribute("vo",service.getBoard(bno));
//		  model.addAttribute("vo",service.getBoard(bno));
//	        => 호출하는 이름 :  boardVO
//		   전달하는 key(d이름)이 없는 경우
//		   전달되는 객체의 타입의 첫글자를 소문자로 변경해서 이름으로 사용
//		   뷰페이지로 이동 (/board/read.jsp)
	}
	
	
	 //http://localhost:8088/board/modify?bno=1
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void uodateBoardGET(Model model , @RequestParam("bno") int bno) throws Exception{
	   
		logger.debug("updateBoardGET() 호출" );
		// 전달정보 저장(bno)
		logger.debug(" bno : " + bno);
		
		// 서비스 - 특정 글정보 가져오기
		
		// Model 저장해서 연결된 뷰페이지로 전달
		 model.addAttribute("vo",service.getBoard(bno));
		//  /board/modify.jsp (read.jsp 참조)
        		
	}
	// http://localhost:8088/board/listALL
	// 글 정보 수정 (POST) 페이지 처리가 일어나 이동하므로 String 
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String updateBoardPOST(RedirectAttributes rttr,
			/* @ModelAttribute */ BoardVO uvo) throws Exception{
		   
		   logger.debug(" updateBoardPOST() 호출 ");
		   
		   // 전달된 정보 저장 (수정할 데이터)
		   logger.debug("vo : " + uvo);
		   // 서비스 - 디비에 게시판 글내용 수정 동작
		   service.modifyBoard(uvo);
		   // 상태정보 전달
		   rttr.addFlashAttribute("result","MODOK");
		   // 페이지로 이동(리스트)
		   return "redirect:/board/listALL";
		   
	 }
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String removeBoardPOST(RedirectAttributes rttr,
			@RequestParam("bno") int bno) throws Exception{
	    logger.debug("remoceBoard() 호출 ");

	    // 전달정보 저장(bno)
	       logger.debug("dvo : " + bno);
	    // 서비스 - 글정보 삭제 동작 호출 
	       service.deleteBoard(bno);
	    // 상태정보 전달
	       rttr.addFlashAttribute("result","DELOK");
	       // 페이지 이동(리스트)
	       return "redirect:/board/listALL"; 
	}
	
	// 페이징처리
    // 1. 반드시 GET방식 사용
	// 2. 본문에서 다시 목록으로 이동하는 기능이 필요함.
	// 3. 5페이지 글정보를 확인 했다면, 다시 리스트 이동후 5페이지로 이동해야함.
	// 4. 페이지양에 따라서 [다음][이전] 버튼의 활성화
	
	
	// 페이징 처리 하단부 (페이지 블럭)
	// - 시작페이지 번호 (setartPage)
	// startPage = (endPage - pageBlock) + 1
	// - 끝페이지 번호 (endPage)
	// endPage = (int)(Math.ceil(page/(double)pageBlock)*pageBlock)
	// * 마지막 페이지 번호, endPage비교
	
	// 마지막페이지번호 = Math.ceil(totalCount/pageSize)
    // if(마지막페이지번호 < endPage)
	//     endPage = 마지막 페이지 번호
	
	// - 전체 글의 개수 (totalCount) => DB count() 함수
	// - 이전 링크 (prev)
	//   startPage == 1? false: true
	// - 다음 링크 (next)
	//    endPage * pageSize >= totalCount ? false : true
	
	// 총 : 122개, 페이지당 10개씩 출력
	// => 페이지수 ? 13
	// 페이지 블럭 한번 10개씩 출력 1....10  11...20
	
	// page = 3  시작페이지 번호 : 1, 끝페이지 번호 10 이전 : X, 다음 : O
	
	// page = 10 시작페이지 번호 : 1, 끝페이지 번호 10 이전 : X, 다음 : 0

	// page = 12 시작페이지 번호 : 11, 끝페이지 번호 13 이전 : O, 다음 : X
	
	
	    // http://localhost:8088/board/listPage
		// 게시판 글 목록
		@RequestMapping(value ="/listPage", method = RequestMethod.GET)
		public String listPageGET(PageVO vo, HttpSession session , Model model,@ModelAttribute("result") String result) throws Exception{
	        
			 logger.debug(" listALLGET() 호출 !! ");
			 logger.debug("result : " + result);
			 // DB에 저장된 글 정보를 가져오기
			
			 
			 // PageVO vo = new PageVO();  page =1, pageSize = 10
			 
			 // 페이징처리된 리스트 정보 가져오기
			 List<BoardVO> boardList = service.getBoardListPage(vo);
			 
			 logger.debug(" boardList : "  +boardList);
             
			 // 페이징처리 (하단부) 정보저장객체
			 PageMaker pm = new PageMaker();
			 pm.setPageVO(vo);
			 pm.setTotalCount(3072);
			 
			 // 조회수 체크 값
			 session.setAttribute("checkViewCnt",true );
			 
			 //연결된 뷰페이지로 전달 (뷰 - 출력)
			 model.addAttribute("boardList", boardList);
			 model.addAttribute("pm", pm);
			 
			return "/board/listALL";
		}
	
	
}// controller