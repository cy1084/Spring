<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인화면</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">

img.frog {
  transform-origin: center bottom;
  animation: swaying-flower 3s infinite ease-in-out;
}
@keyframes swaying-flower {
  0% {transform: rotate(-10deg);}
  50% {transform: rotate(10deg);}
  100% {transform: rotate(-10deg);}
}

</style>
</head>
<body>
	<div>
		<img class="frog" src="./img/default.png" alt="이상해씨">
	</div>
	<div class="container" style="margin-top: 100px;">
	<form action="./login.do" method="post">
		<table class="table table-hover">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" required="required">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password" required="required">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn" type="submit" value="로그인">
					<input class="btn" type="button" value="회원가입" onclick="javascript:location.href='./signupForm.do'">
					<input class="btn" type="button" value="아이디찾기" onclick="findId()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function findId(){
		window.open("./findIdWindow.do", "_blank", "width=500px, height=300px");
	}

</script>
</html>