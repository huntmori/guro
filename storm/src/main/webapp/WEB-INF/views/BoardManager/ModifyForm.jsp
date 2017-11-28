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
	<form method="POST" id="mFrm" action="../BoardManager/ModifyProc.storm">
		<input type="hidden" name="boardNo" value="${VIEW.board_no}">
		<input type="hidden" name="nowPage" value="${nowPage}">
		<table width="800" border="1" align="center">
			<tr>
				<td>게시글 번호</td>
				<td><input type="text" value="${VIEW.board_no}" readyOnly>
			</tr>
			<tr>
				<td>상품 번호</td>
				<td><input type="text" id="board_app_id" name="board_app_id"
					value="${VIEW.board_app_id}"></td>
			</tr>
			<tr>
				<td>사용자 번호</td>
				<td><input type="text" id="board_user_key"
					name="board_user_key" value="${VIEW.board_user_key}"></td>
			</tr>
			<tr>
				<td>게시글 제목</td>
				<td><input type="text" id="board_title" name="board_title"
					value="${VIEW.board_title}"></td>
			</tr>
			<tr>
				<td>게시글 작성일</td>
				<td><input type="text" id="board_wdate" name="board_wdate"
					value="${VIEW.board_wdate}"></td>
			</tr>
			<tr>
				<td>게시글 본문</td>
				<td><textarea id="board_text" name="board_text">${VIEW.board_text}</textarea></td>
			</tr>
			<tr>
				<td>게시글 삭제여부</td>
				<td><input type="text" id="board_isshow" name="board_isshow"
					value="${VIEW.board_isshow}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" id="mBtn"
					value="수정하기" onClick="sendModify()"></td>
			</tr>
		</table>
	</form>
</body>
</html>