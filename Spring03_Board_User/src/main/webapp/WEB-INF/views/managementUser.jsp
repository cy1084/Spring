<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$("button[name=btnChk]").click(function(event){
  			event.preventDefault();
  			var btnOrder=$(this).val();
  			console.log(btnOrder);
  			
  			var chkRow=$("input[name=chkid]:checked");
  			for(let i=0;i<chkRow.length;i++){
  				console.log(chkRow[i]);
  				console.log(chkRow[i].parentNode.parentNode.children[4].textContent);
  				
  				if(btnOrder=="toAuth"){
  					chkRow[i].parentNode.parentNode.children[4].textContent="A";
  				}
  				
  			}
  			changeAjax();
  		});
  	});
  	
  	function changeAjax(val){
  		var checkCnt=document.querySelectorAll("input[name=chkid]:checked");
  		
  		if(checkCnt.length==0){
  			alert("한 개 이상의 수정 데이터를 선택해주세요");
  		}else{
  			var data=$("form").serialize(); //form의 객체들을 한 번에 받을 수, key value 형태로 자동으로 보여줌
  			//var data=$("form").serializeArray();
  			console.log(data);
  			console.log("./"+val+".do");
  			
  			var urlPath="./"+val+".do";
  			
  			$.ajax({
  				url:urlPath,
  				//url:"./convertForm.do",
  				type:"post",
  				//contentType:"application/json",
  				//data:JSON.stringify(data),
  				data:data,
  				success:function(msg){
  					if(msg=="true"){
  						alert("변경 완료");
  					}else{
  						alert("변경 취소");
  					}
  				},
  				error:function(){
  					alert("잘못된 요청 처리");	
  				}
  			});
  			
  			//dataType : 서버에서 어떤 타입을 받을 것인지를 의미
  			//contentType : 서버로 데이터를 보낼 때에 어떤 타입으로 보낼 것인지
  			//디폴트 값은 application/x-www-form-urlencoded; charset=utf-8
  			//JSON.stringify() : JavaScript 객체를 JSON 문자열로 변환
  			//JSON.parse() : JSON문자열을 JavaScript객체로 변환
  		}
  	}
  	
  	//0703 12:19~ 다시 듣기: 요청처리 변경
  </script>
</head>
<body>
	<form method="post" onsubmit="return checkBox()">
		<table class="table">
			<thead>
				<tr class="info">
					<th><input type="checkbox" onclick="allCheck(this.checked)"></th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>권한</th>
					<th>활성여부</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${userList }">
					<tr>
						<td><input type="checkbox" name="chkid" value="${vo.id}"></td>
						<td>${vo.id}</td>
						<td>${vo.name}</td>
						<td>${vo.email}</td>
						<td>${vo.auth}</td>
						<td>${vo.enable}</td>
						<td>${vo.joinindate}</td>
						
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr style="text-align: center;">
					<td colspan="7">
						<button name="btnChk" value="toAuth">관리자로 변경</button>
						<button name="btnChk" value="toUser">일반 유저로 변경</button>
						<button name="btnChk" value="toNonActive">비활성화</button>
						<button name="btnChk" value="toActive">활성화</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>