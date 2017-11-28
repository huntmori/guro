<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
#listheader {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#listheader td, #listheader th {
    border: 1px solid #ddd;
    padding: 8px;
}

#listheader tr:nth-child(even){background-color: #f2f2f2;}

#listheader tr:hover {background-color: #ddd;}

#listheader th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: 	#191970;
    color: white;
}
</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#sBtn").click(function() {
			//	
			$("#sfrm").submit();
		});
	});
</script>

<body>
	<%--	검색 상자를 만들고 --%>
	<div class="container">
		<form method="POST" id="sfrm" action="../UserManager/UserSearch.storm">
			<input type="hidden" value="${PINFO.nowPage}" id="nowPage"
				name="nowPage">
			<table class="table table-bordered" width="1000" border="1" align="center">
				<tr>
					<td align="center">
					<select id="kind" name="kind" style="background:#404040;color:white; font-size:12pt">
							<option value="user_key" style="background:#404040;color:white; font-size:12pt">사용자번호</option>
							<option value="user_email" style="background:#404040;color:white; font-size:12pt">이메일</option>
							<option value="user_nickname" style="background:#404040;color:white; font-size:12pt">닉네임</option>
							<option value="user_enable" style="background:#404040;color:white; font-size:12pt">사용유무</option>
					</select>
					<input type="text" id="word" name="word">
					<input type="button"  class="btn btn-primary" id="sBtn" value="검색하기"></td>
				</tr>
			</table>
		</form>

		<%--	목록을 보여주고 --%>
		<table class="table table-bordered" id="listheader" width="1000" border="1" align="center">
		<thead>
			<tr>
				<th>사용자번호</th>
				<th>이메일</th>
				<th>닉네임</th>
				<th>사용유무</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="data" items="${LIST}">
				<%--
		목록 구하기 질의 명령을 실행하면 한줄의 데이터가 VO 클래스 묶여서
		이것이 다시 ArrayList로 묶여서 제공된다.
--%>
				<tr>
					<td>${data.user_key}</td>
					<td>${data.user_email}</td>
					<td><a
						href="../UserManager/UserView.storm?nowPage=${PINFO.nowPage}&userKey=${data.user_key}">${data.user_nickname}</a>
					</td>
					<td>${data.user_enable}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<%--	페이지 이동 기능 --%>
		<table class="table table-bordered" width="1000" border="1" align="center">
			<tr>
				<td align="center">
					<%--	[이전] --%> <c:if test="${PINFO.startPage eq 1}">
					[이전]
				</c:if> <c:if test="${PINFO.startPage ne 1}">
						<a href="">[이전]</a>
					</c:if> <%--	[1][2][3] --%> <c:forEach var="page"
						begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="">[${page}]</a>
					</c:forEach> <%--	[다음] --%> <c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
				</c:if> <c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="">[다음]</a>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>