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
<title>글 전체보기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div>
		<c:if test="${loginVo != null }">
			${loginVo.name }님 반갑습니다.
			<input type="button" value="로그아웃" onclick="location.href='./logout.do'">
		</c:if>
		<c:if test="${loginVo.auth =='A'}">
			<input type="button" value="회원관리"	onclick="location.href='./managementUser.do'">
		</c:if>
	</div>
	
	<form action="./multiDelete.do" method="post" onsubmit="return chkBox()">
		<input type="submit" value="삭제">
		<input type="button" value="새글작성" onclick="location.href='./insertBoard.do'">
		
		<c:if test="${loginVo.auth eq 'A' }">
			<input type="button" value="회원전체조회" onclick="location.href='./userSelectAll.do'"> 
			<input type="button" value="게시글복구" onclick="location.href='./restoreBoard.do'"> 
		</c:if>
	
		<table class="table">
			<thead>
				<tr class="info">
					<td><input type="checkbox" onclick="allCheckValues(this.checked)"></td>
					<td>연번</td>
					<td>아이디</td>
					<td>제목</td>
					<td>등록일</td>
				</tr>
		</thead>
		<tbody>
		<c:forEach var="vo" items="${boardList}" varStatus="vs">
			<tr>
<%-- 				<td>${boardList}</td> --%>
				<td><input type="checkbox" name="chkVal" value="${vo.seq}"></td>
				<td>${fn:length(boardList)-vs.index}</td>
				<td>${vo.id }</td>
				<td><a href="./detailBoard.do?seq=${vo.seq }">${vo.title }</a></td>
				<td>
					<fmt:parseDate var="cDate" value="${vo.regdate}" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${cDate }"/>
				</td>
				
			</tr>
		</c:forEach>
			
		</tbody>
	</table>
	</form>
</div>
</body>
</html>