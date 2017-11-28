<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#mBtn")
								.click(
										function() {
											$(location)
													.attr("href",
															"../UserManager/ModifyForm.storm?userKey=${MAP.VIEW.user_key}&nowPage=${nowPage}");
										});

						$("#dBtn").click(function() {

							$("#ifrm").submit();
						});
					});
</script>
<body>
	<table width="800" border="1" align="center">
		<tr>
			<td>사용자번호</td>
			<td>${MAP.VIEW.user_key}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${MAP.VIEW.user_email}</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${MAP.VIEW.user_nickname}</td>
		</tr>
		<tr>
			<td>사용유무</td>
			<td>${MAP.VIEW.user_enable}</td>
		</tr>
	</table>

	<table width="800" border="1" align="center">
		<tr>
			<td align="center">
			<input type="button" id="mBtn" value="수정하기">
			<input type="button" id="dBtn" value="삭제하기"></td>
		</tr>
	</table>
	<!-- 	
			이처럼 중요한 정보를 GET 방식으로 보내면 보안에 취약하다.
			
			임시 폼을 만들어서 전달할 값을 모두 폼 안에 입력한 후
			그 폼을 submit 방식으로 전달하여 보안에 주의 해야 한다,
	-->
	<form method="POST" id="ifrm" action="../UserManager/DeleteProc.storm">
		<input type="hidden" id="userKey" name="userKey"
			value="${MAP.VIEW.user_key}">
		<input type="hidden"
			id="nowPage" name="nowPage" value="${nowPage}">
	</form>
</body>
</html>
