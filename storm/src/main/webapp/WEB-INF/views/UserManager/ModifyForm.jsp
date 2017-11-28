<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	function sendModify() {
		var frm = document.getElementById("mFrm");
		frm.submit();
	}
</script>
<body>
	<form method="POST" id="mFrm" action="../UserManager/ModifyProc.storm">
		<input type="hidden" name="userKey" value="${VIEW.user_key}">
		<input type="hidden" name="nowPage" value="${nowPage}">
		<table width="800" border="1" align="center">
			<tr>
				<td>사용자번호</td>
				<td><input type="text" value="${VIEW.user_key}" readyOnly>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="user_email" name="user_email"
					value="${VIEW.user_email}"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" id="user_nickname" name="user_nickname"
					value="${VIEW.user_nickname}"></td>
			</tr>
			<tr>
				<td>사용유무</td>
				<td><input type="text" id="user_enable" name="user_enable"
					value="${VIEW.user_enable}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" id="mBtn"
					value="수정하기" onClick="sendModify()"></td>
			</tr>
		</table>
	</form>
</body>
</html>