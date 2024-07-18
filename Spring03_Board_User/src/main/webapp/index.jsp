<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a:hover {
font-size: 50px;
text-decoration: none;
animation-duration: 3s;
animation-name: rainbow;
animation-iteration-count: infinite; }
@keyframes rainbow {
0% { color: #ff2a2a; }
15% { color: #ff7a2a; }
30% { color: #ffc52a; }
45% { color: #43ff2a; }
60% { color: #2a89ff; }
75% { color: #202082; }
90% { color: #6b2aff; }
100% { color: #e82aff; }
}

#artext {
background: linear-gradient(to left, violet, indigo, blue, green, yellow, orange, red);
background-clip: text;
display: inline;
color: transparent;
animation: animated 5s linear infinite;
}

@keyframes animated {
to {
background-position-x: 161px;
}
}

* {
  margin: 0;
  padding: 0;
}

/*Keyframes*/
@keyframes rain_effect {
0% {background-position: 0px 0px, 0px 0px, 0px 0px;}
50% {background-position: 400px 800px, 300px 300px, 200px 200px; background-color: #FF69B4}
100% {background-position: 0px 0px, 0px 0px, 0px 0px; background-color: #F8C8DC}
}

@-moz-keyframes rain_effect {
0% {background-position: 0px 0px, 0px 0px, 0px 0px;}
50% {background-position: 400px 800px, 300px 300px, 200px 200px; background-color: #FF69B4}
100% {background-position: 0px 0px, 0px 0px, 0px 0px; background-color: #F8C8DC}
}

@-webkit-keyframes rain_effect {
0% {background-position: 0px 0px, 0px 0px, 0px 0px;}
50% {background-position: 400px 800px, 300px 300px, 200px 200px; background-color: #FF69B4}
100% {background-position: 0px 0px, 0px 0px, 0px 0px; background-color: #F8C8DC}
}

@-ms-keyframes rain_effect {
0% {background-position: 0px 0px, 0px 0px, 0px 0px;}
50% {background-position: 400px 800px, 300px 300px, 200px 200px; background-color: #FF69B4}
100% {background-position: 0px 0px, 0px 0px, 0px 0px; background-color: #F8C8DC}
}


body {
	background-color: #F8C8DC; 
	background-image: url('img/default.png'), url('img/img01.png'), url('img/img02.png');
	background-size: 5% auto, 6% auto, 7% auto;
	animation: rain_effect 40s linear infinite;
	-webkit-animation: rain_effect 40s linear infinite;
	-moz-animation: rain_effect 40s linear infinite;
	-ms-animation: rain_effect 40s linear infinite;
}

.my_text {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    justify-content: center;
    color: #fff;
}

.my_text h1{
	font-size: 5em;
}

.my_text p{
	font-size: 3em;
}
</style>
</head>
<body>
	<h1 id="artext">오늘 점심 메뉴 두구두구두구두구</h1>
	<button onclick="location.href='./home.do'">처음요청</button>
	<a style="font-size: 50px; text-decoration: none;" href="./loginForm.do">로그인</a>
</body>
</html>