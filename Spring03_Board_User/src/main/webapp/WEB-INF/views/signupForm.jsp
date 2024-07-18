<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
   <div class="container">
      <form action="./signUp.do" method="post">
         <h2>회원가입</h2>
         <h3>필수 값을 입력해주세요</h3>
         <div>
            <input class="form-control" type="text" name="name" id="name" placeholder="이름작성">
            <input class="form-control" type="text" name="id" id="id" placeholder="아이디" maxlength="10" onclick="return duplicate()">
            <input class="form-control" type="password" name="password" id="pw" placeholder="비밀번호">
            <input class="form-control" type="password" name="password" id="pwok" placeholder="비밀번호 확인" onkeyup="checkPw()">
            <!-- 비밀번호 확인은 넘어갈 거 아니니까 name 필요 없음 -->
            <span id="pwc" style="color:red;">비밀번호가 동일하지 않습니다</span>
            <input class="form-control" type="text" name="email" id="email" placeholder="이메일">
         </div>
         <div>
            <input class="btn btn-primary" type="submit" value="회원가입">
            <input class="btn btn-danger" type="button" value="가입취소" onclick="javascript:history.back(-1)">
         </div>
      </form>
   </div>
</body>
<script type="text/javascript">
   window.onload=function(){
      document.querySelector("input[type=submit]").onclick = function(e){
         e.preventDefault();
         
         var name = document.getElementById("name");
         var id = document.getElementById("id");
         var password = document.getElementById("pw");
         var pwok = document.getElementById("pwok");
         var email = document.getElementById("email");
         
         const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
         
         if(name.value.length < 3){
            alert("정확한 성명을 작성해주세요");
            return;
         }
         if(password.value != pwok.value){
            alert("비밀번호가 일치하지 않습니다");
            return;
         }
         if(!email.value.match(emailRegex)){
            alert("잘못된 이메일 형식입니다");
            return;
         }
         
         document.forms[0].submit();
         
      }
      document.getElementById("pwc").style.display="none";
   }
   
   function duplicate(){
      window.open("./duplication.do", "중복검사", "width=500px, height=500px");
   }
   
   function checkPw(){
      var pw = document.getElementById("pw").value;
      var pwcheck = document.getElementById("pwok").value;
      var isc = true;
      if(pw != pwcheck){
         console.log(pw);
         console.log(pwcheck);
         document.getElementById("pwc").style.display="block";
      }
      if(pw == pwcheck){
         console.log(pw);
         console.log(pwcheck);
         document.getElementById("pwc").style.display="none";
      }
   }
</script>
</html>