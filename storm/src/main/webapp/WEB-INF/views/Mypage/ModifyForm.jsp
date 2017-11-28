<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
<%--	$(function(){
		$("#msBtn").click(function(){
			$("#modifyf").load("http://localhost:8080/storm/Mypage/MypageList.storm");
		});	
	}); 
	--%>
	$(function(){
		$("#msBtn").click(function(){
			$(location).attr("href", "../Mypage/ModifyForm.storm");
		});	
	}); 
</script>
<body>
<div>
	<h4>회원정보수정</h4>
	<div id=modifyf>
		이메일 :  
		<input type="email" placeholder="" id="email" name="email" value="${VO.email}"><br><br>
		닉네임 :  
		<input type="text" placeholder="" id="nick" name="nick" value="${VO.nick}"><br><br>
	     <button type="button" id="msBtn" value="수정완료">수정완료</button><br><br>
	</div>
</div>
</body>
</html>
