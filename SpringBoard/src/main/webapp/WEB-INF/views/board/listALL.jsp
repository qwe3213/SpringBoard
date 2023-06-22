<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@  include file="../include/header.jsp"%>

<h1>http://localhost:8088/board/listALL</h1>
<h1>/board/listALL.jsp</h1>

<script>

// 디비 - 서비스 - 컨트롤러 -> 뷰(jsp) -> JS -> AJAX  


$(document).ready(function(){
		
	var data = "${result}";

	if (data == "CREATEOK") {
 //		alert(" 글쓰기 성공!");
//		$(".modal-body").html("글쓰기 성공!" );	
//		$("#modal-info").modal("show");
		callModal("글쓰기 성공!");
	}
	if(data == "MODOK"){
		callModal("글 수정 성공!");
	 }
	if(data == "DELOK"){
		callModal("글 삭제 성공!")
	}
	
	function callModal(txt){
		
		$(".modal-body").html(txt);	
		$("#modal-info").modal("show");
	}
	});
</script>
<h2>result : ${result }</h2>
 ${pm}
<!-- 모달창  -->
<div class="modal modal-info fade" id="modal-info"
     style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">ITWILL 페이지 정보</h4>
			</div>
			<div class="modal-body">
				<p>One fine body…</p>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-outline pull-left"
					data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-outline" data-dismiss="modal">확인</button>
			</div>
		</div>

	</div>

</div>


<!-- 모달창  -->

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">Bordered Table</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 60px">번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th style="width: 60px">ViewCnt</th>
				</tr>
				<c:forEach var="vo" items="${boardList }">
					<tr>
						<td>${vo.bno }</td>
						<td><a href="/board/read?bno=${vo.bno}">${vo.title }</a></td>
						<td>${vo.writer }</td>
						<td>${vo.regdate }</td>
						<td><span class="badge bg-maroon">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>