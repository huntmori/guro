<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Document</title>
	<meta name="mypage" content="width=device-width, initial-scale=1" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(function(){
	    $("#commudiv").load("http://localhost:8080/storm/Mypage/MyCommuList.storm");
	});
	
	$(function(){
		$("#mBtn").click(function(){
			alert("�����Ѵ�"); 
			$(location).attr("href", "../Mypage/ModifyForm.storm");
		});
	});
<%--	$(function(){
		$("#mBtn").click(function(){
			$(location).attr("href", "http://localhost:8080/storm/Mypage/ModifyForm.storm");
			
		});	
	});
	--%>
</script>
<style>
	#aa {border-style : solid;}
</style>
<body>
<h1>����������</h1>

<div id="aa">
	<h3>${VO.nick}���� ���� ��õ����</h3><br><br>
</div>
<div id="aa">
	<h3>${VO.nick}���� Ŀ�´�Ƽ</h3>
		<div id=commudiv>	
	
		</div>
</div>
<div id="aa">
	<h3>ȸ����������</h3>
	<div id=imodify>
		�̸��� : ${VO.email}<br><br>
		�г��� : ${VO.nick}<br><br>
	
	     <button type="button" id="mBtn" value="����">����</button><br><br>
	</div>
</div>
</body>
</html>




