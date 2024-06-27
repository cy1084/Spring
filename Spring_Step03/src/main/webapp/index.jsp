<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
</head>
<body>
	<!--TODO 007_02 index를 호출 한 후에 redirect 하는 방법-->
	<%
	//response.sendRedirect(request.getContextPath() + "/home.do");
	//response.sendRedirect("./home.do");
	%>
	
	<!-- TODO 008 POST 방식으로 /info.do로 값 전달, 한글깨짐 -->
	<div style="width:300px; margin:200px auto; border: 2px solid tomato">
		<form action="./info.do" method="post">
			이름: <input type="text" name="name"><br>
			나이: <input type="number" name="age"><br>
			<input type="submit" value="값 전송">		
		</form>
		
		<h2><a href="./home.do">GET /home.do 요청</a></h2>
	</div>

</body>

</html>

