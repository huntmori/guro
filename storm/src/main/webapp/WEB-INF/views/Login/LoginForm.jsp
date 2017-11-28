<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>
 <div class="w3-container w3-blue-grey" >
 <h2>Please Log In</h2>
 </div>
   <form class="w3-container" action="../Login/LoginProc.storm" >
        <div class="w3-section">
          <label><b>ID</b></label>
          <input class="w3-input" type="text" name="id" placeholder="이메일을 입력하세요" required>
          <label><b>Password</b></label>
          <input class="w3-input" type="text" name="pw" placeholder="비밀번호를 입력하세요" required>
          <button class="w3-button w3-block w3-blue-grey w3-section w3-padding" type="submit" >Login</button>
          
        </div>
      </form>

</body>
</html>










