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
	<h4>ȸ����������</h4>
	<div id=modifyf>
		�̸��� :  
		<input type="email" placeholder="" id="email" name="email" value="${VO.email}"><br><br>
		�г��� :  
		<input type="text" placeholder="" id="nick" name="nick" value="${VO.nick}"><br><br>
	     <button type="button" id="msBtn" value="�����Ϸ�">�����Ϸ�</button><br><br>
	</div>
</div>
</body>
</html>
