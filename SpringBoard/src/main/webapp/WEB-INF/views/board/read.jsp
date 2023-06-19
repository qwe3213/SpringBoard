<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<h1>http://localhost:8088/board/read 호출</h1>
<h1>/board/read.jsp</h1>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 본문(내용)</h3>
	</div>
	
        <!-- 수정(get-post)/삭제 정보 전달용  -->
        <form role="form" id="fr" >
             <input type="hidden" name="bno" value="${vo.bno}">        
             
        </form>

    	<div class="box-body">
    	<div class="form-group">
				<label for="exampleInputEmail1">번 호</label> <input type="text"
					name="title"class="form-control" id="exampleInputEmail1"
					readonly value="${vo.bno }">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제목</label> <input type="text"
					name="title"class="form-control" id="exampleInputEmail1"
					readonly value="${vo.title }">
			</div>

			<div class="form-group">
				<label for="exampleInputPassword1">이름</label> <input type="text"
				name="writer" class="form-control" id="exampleInputPassword1"
					readonly value="${vo.writer }">
			</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control"  name="content" rows="3" readonly>${vo.content }</textarea>
		</div>
		</div>
		<div class="box-footer">
			<button type="button" class="btn btn-danger">수정하기</button>
			<button type="button" class="btn btn-warning">삭제하기</button>
			<button type="button" class="btn btn-info">목록으로</button>
		</div>
</div>

        <script>
            $(document).ready(function() {
            	
            	// 폼태그 객체
                //	$("#fr"); id 선택자
                  var frObj =  $("form[role='form']"); // 속성탐색 선택자
            	
                    console.log(frObj);
            	// 수정하기 버튼
            	$(".btn-danger").click(function(){
            	//	location.href="/board/modify?bno=${vo.bno}";
            	    // 폼태그 사용해서 bno 가지고, 수정페이지로 이동(GET)
            	    frObj.attr("action", "/board/modify");
            	    frObj.submit();
            	});
            	
            	// 삭제하기 버튼
            	$(".btn-warning").click(function(){
            		frObj.attr("action" , "/board/remove");
            		frObj.attr("method","post");
            		frObj.submit();
            		
            	});
            	
            	
                // 목록으로 버튼
            	$(".btn-success").click(function(){
            		location.href='/board/listALL';
            	});
            	
            });
        </script>


<%@ include file="../include/footer.jsp"%>