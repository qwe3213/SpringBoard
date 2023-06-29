<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function() {
        
        $("#btnC").click(function() {
            alert("작성 버튼 클릭");
            var obj = {
                        title : $("#title").val(), 
                        writer : $("#writer").val(),
                        content : $("#content").val()
                    }
            console.log(obj);
            
            // REST 컨트롤러로 이동하여 데이터 처리 (ajax 사용)
            $.ajax({
                url : "${contextPath}/boards",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify(obj),
                success : function(data) {
                    alert("성공 : " + data);
                    $("#title").val("");
                    $("#writer").val("");
                    $("#content").val("");
                },
                error : function() {
                    alert("실패ㅠㅠ");
                } //error
                
            });  // ajax

        }); // btnC click
            $('#btnL').click(function(){
                // ajax
            	$.ajax({
            		url : "${contextPath}/boards",
            		type : "get",
            		success : function(data){	
            			alert("성공!");
            		    
            			console.log(data);
            	//      요소에 순차적으로 접근
            	//	   $.each(접근할요소,function);
                //     $(접근할요소).each(function);
                    $.each(data,function(idx,item){
                var tag ="<tr>";
                         tag +="<td>"; 
                         tag +=item.bno; 
                         tag +="</td>"; 
                         tag +="<td>"; 
                         tag +=item.title; 
                         tag +="</td>"; 
                         tag +="<td>"; 
                         tag +=item.writer; 
                         tag +="<td>"; 
                         tag +="</tr>"; 
                         
                      $("table").append(tag);
                    });
                //  $.each(data,function(idx,item){
                          // 테이블에 값을 각각 추가
                      //    $("table").append("<tr><td>"+item.bno+"</td><td>"+item.title+"</td><td>"+item.writer+"</td></tr>");   
                    //   });		
            		}
            	});
           });
        
    });  // jquery
</script>

<body>

        <h1>board.jsp</h1>
        
        <h2>REST방식으로 게시판코드 작성</h2>
    
        <h2> 글쓰기  /boards + JSON데이터 POST</h2>

        제목 : <input type ="text" id ="title"> <br>
        이름 : <input type ="text" id ="writer"> <br>
        내용 : <input type ="text" id ="content"> <br>
        
        <input type="button" id ="btnC" value="글쓰기">
        <hr>
        
        <h2>글 리스트 정보 조회</h2>        
        
        <input type="button" id ="btnL" value="리스트 가져오기">
        
        <div id ="divL">
          <table border="1">
             
             <tr>
                <td>번호</td>
                <td>제목</td>
                <td>글쓴이</td>
             
             </tr>
             
          </table>
          </div>
        <hr>
        
        
            
</body>
</html>