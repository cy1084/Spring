<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>아이디 찾기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function findId(){
	  var name=document.getElementById("name").value;
	  var email=document.getElementById("email").value;
	  var info=document.getElementById("info").value;
	  
	  const emailRegex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	  
	 if(email.match(emailRegex)){
		 $.ajax({
			 url:"./findId.do",
			 type="post",
			 async:true,
			 data:{"name":name,"email":email},
			 success:function(data){
				 if(data==""){
					 info.innerHTML="아이디를 찾을 수 없습니다.";
				 }else{
					 let msg="회원님의 아이디는 ["+data+"] 입니다.";
					 info.innerHTML=msg;
				 }
			 },
			 error:function(){
				 alert("관리자에게 문의");
			 }
		 });
	 }else{
		 info.innerHTML="이메일 형식이 아닙니다.";
		}
  }
  </script>
</head>
<body>
이름: <input type="text" id="name"><br>
이메일:<input type="text" id="email"><br>
<input type="button" value="아이디찾기" onclick="findId()"><br>
<input type="button" value="닫기" onclick="javascript:self.close()"><br>
 
</body>
</html>