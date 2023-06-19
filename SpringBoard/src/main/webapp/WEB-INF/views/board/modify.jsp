<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<h1>http://localhost:8088/board/modify 호출</h1>
<h1>/board/read.jsp</h1>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 수정하기</h3>
	</div>
	
        <!-- 수정(get-post)/삭제 정보 전달용  -->
        <form role="form" id="fr" >
           

    	<div class="box-body">
    	<div class="form-group">
				<label for="exampleInputEmail1">번 호</label> <input type="text"
					name="bno"class="form-control" id="exampleInputEmail1"
					 value="${vo.bno }" readonly>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제목</label> <input type="text"
					name="title"class="form-control" id="exampleInputEmail1"
					 value="${vo.title }">
			</div>

			<div class="form-group">
				<label for="exampleInputPassword1">이름</label> <input type="text"
				name="writer" class="form-control" id="exampleInputPassword1"
					 value="${vo.writer }">
			</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control"  name="content" rows="3" >${vo.content }</textarea>
 		</div>

	</div>
		</form>
		<div class="box-footer">
			<button type="submit" class="btn btn-danger">수정하기</button>
			<button type="reset" class="btn btn-warning">취소하기</button>
		</div>
</div>

<script>
      $(document).ready(function(){
    	 
    	 var formObj = $("#fr");
         
    	 $(".btn-danger").click(function(){
    		// 수정하기 동작   - modify 주소, post 방식
    	    formObj.attr("method", "post");
    		formObj.submit(); 
    		
    	 });
         
      });

</script>
        

<%@ include file="../include/footer.jsp"%>