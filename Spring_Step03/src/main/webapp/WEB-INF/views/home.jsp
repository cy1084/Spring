<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- TODO 006 HomeController의 home.do에 의해 실행되는 페이지 -->
<title>home에 의해 호출되는 페이지</title>
</head>
<body>
	<h2>처음 호출되는 home.do에서 실행</h2>
	<div>${name }</div>
	<pre>	
		RequestMapping에 의해 실행된 메소드의 반환 이름을 통해,
		ViewResolver에 prefix와 suffix를 통해 만들어진 화면 위치!
		RequestMapping-> return String-> DispatcherServlet-> ViewResolver-> 파일 위치
	</pre>

</body>
</html>