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
h1 {
	text-align: center;
}

h2 {
	height: 40px;
	border-width: 1px;
	border-style: solid;
	font-size: 1.875em;
	font-weight: normal;
	color: white;
	background-color: #191970;
	text-align: center;
}

h3 {
	text-align: right;
}
</style>

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

#listheader tr:nth-child(even) {
	background-color: #f2f2f2;
}

#listheader tr:hover {
	background-color: #ddd;
}

#listheader th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #191970;
	color: white;
}
</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
	<!-- 	검색 폼 만들고 -->
	<!-- 	검색 결과 출력하고 -->
	<div class="container">
		<table class="table table-bordered" width="1000" border="1"
			align="center">
			<h2>조회결과</h2>
		</table>
		<table id="listheader" width="1000" border="1" align="center">
			<tr>
				<th>사용자번호</th>
				<th>이메일</th>
				<th>닉네임</th>
				<th>사용유무</th>
			</tr>
			<c:forEach var="data" items="${LIST}">
				<tr>
					<td>${data.user_key}</td>
					<td>${data.user_email}</td>
					<td><a
						href="../UserManager/UserView.storm?nowPage=${nowPage}&userKey=${data.user_key}">${data.user_nickname}</a>
					</td>
					<td>${data.user_enable}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 	기타 기능 만들고 -->
</body>
</html>