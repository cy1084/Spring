<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>삭제 글 리스트</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
<%-- 		${fn:length(restoreList) } --%>
		<table class="table table-hover">
			<thead>
				<tr class="info">
					<th>연번</th>
					<th>아이디</th>
					<th>제목</th>
					<th>등록일</th>
					<th>복구</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="vo" items="${restoreList}" varStatus="vs">
				<!-- var="vo": 각 반복 주기마다 현재 항목을 가리키는 변수의 이름 -->
				<!-- 반복 상태 변수로, 반복의 현재 상태를 추적, 현재 반복의 인덱스(0부터 시작)를 얻을 수 있음 -->
					<tr>
<%-- 						<td>${vs.index}</td> --%>
						<td>${vo.seq}</td>
						<td>${vo.id}</td>
						<td>${vo.title}</td>
						<td>${vo.regdate}</td>
						<td>
							<button onclick="restorejQuery(this)">jQuery복구</button>
							<button onclick="restoreFetch(this)">Fetch복구</button>
							<button onclick="restoreAxios(this)">Axios복구</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<!-- 라인 삭제 -->
<script type="text/javascript">
	function restorejQuery(obj){
		var parentTr=obj.closest("tr");
		var seq=parentTr.children[0].textContent; //seq 번호 가져오기
		
		
		$.ajax({
			url:"./restore.do",
			type:"get",
			data:{seqs:seq},
			success:function(msg){
				if(msg == 'true'){
					parentTr.remove();
				}
			},
			error:function(){
				alert("잘못된 요청");
			}
		});
		
	}
	
	function restoreFetch(obj){
		var parentTr=obj.closest("tr");
		var seq=parentTr.children[0].textContent;
		
		fetch("./restore.do?seqs="+seq)
		.then(response => response.text()) //text로 형변환
		.then(data => {
			if(data == 'true'){
				parentTr.remove();
			}
		})
		.catch(error =>console.log("잘못된 요청 처리"));
	}
	
	//axios: 라이브러리므로 cdn으로 가져와야 함
	function restoreAxios(obj){
		var parentTr=obj.closest("tr");
		var seq=parentTr.children[0].textContent;
		
		axios.get("./restore.do",{
			params:{seqs:seq}
		})
		.then(response => {
			let data=response.data;
			if(data == 'true'){
				parentTr.remove();
			}
		}) //데이터 한 방에 가져옴
		.catch(error=>console.log(error))
	}
</script>
</body>
</html>