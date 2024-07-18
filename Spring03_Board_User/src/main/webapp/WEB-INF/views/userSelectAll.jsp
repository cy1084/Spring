<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>[관리자] 사용자 검색</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>[관리자] 회원전체조회</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${userList}" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${vs.id}</td>
						<td>${vs.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<fieldset>
			<legend>회원검색</legend>
			<form name="search-frm" autocomplete="on">
				<select name="type" id="type">
					<option value="id" selected>아이디</option>
					<option value="name">성명</option>
				</select>
				<input type="text" name="keyword" placeholder="검색어를 입력해주세요">
				<input type="button" value="검색" onclick="getSearchUser()">
			</form>
			<div id="viewDetail"></div>
		</fieldset>
	</div>

</body>
<script type="text/javascript">
	function getSearchUser(){
		var opt=document.getElementById("type");
		var keyword=document.getElementsByName("keyword")[0].value;
		console.log(opt.options[opt.selectedIndex].value); //name
		console.log(keyword); //비
		
		var optValue=(opt.options[opt.selectedIndex].value);
		
		$.ajax({
			url:"./getSearchUser.do",
			method:"post",
			data:{"opt":optValue,"keyword":keyword},
			dataType:"json",
			//dataType-> 형변환해서 가져온 애 
			success:function(msg){
				//console.log(msg);
				//console.log(msg["문방사우"][0]);
				
				var jArray=msg;
				Object.keys(jArray).forEach(function(key){
					console.log(key,jArray[key],jArray[key].name);
				});
				
				for(var key in jArray){
					console.log(key,jArray[key].name);
				}
				
				var html="<table class='table table-hover'>";
				//jquery의 for문
				$.each (msg,function(key,value){
					html+="<tr>";
					html+="<td>"+(key+1)"</td>";
					html+="<td><a href='./userDetail.do?id="+value.id+"'>"+value.id+"</a></td>";
					html+="<td>"+value.name+"</td>";
					html+="</tr>";
					
				})
				
				html+="</table>";
				
				$("#viewDetail").html(html);
			},
			error:function(){
				alert("잘못된 요청 처리");
			}
			/*
			                    ajax
			"true" ------- dataType:"text" ------- "true" String
			"true" ------- dataType:"json" -------  오류발생
			escape 문자로 -- dataType:"json" -------  Object  
														Object obj
														obj.isc-> "true" String
					
							dataType:"text" --------msg -> String 형태
													JSON.parse(msg) -> json 형태로
			
			*/
		})
	}
</script>
</html>