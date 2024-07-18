<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>답글 작성 화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h3>답글 입력 화면</h3>
	<div class="container">
		<form action="./replyInsert.do" method="post">
			<input type="hidden" name="seq" value="${boardOne.seq }" > <!-- 부모의 seq 값, 넘어갈 때 이 seq를 가져갈 수 있도록 -->
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="form-control">${loginVo.id}</div>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" id="title" name="title" required="required" placeholder="원본제목:${fn:substring(boardOne.title,0,10)}"/>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" id="content" name="content" required="required"></textarea>
			</div>
			<div>
				<button type="submit" class="btn btn-success">답글 작성</button>
				<button type="reset" class="btn btn-success">입력 초기화</button>
				<button class="btn btn-default" onclick="history.back(-1)">취소</button>
			</div>
		</form>
	</div>

</body>
</html>