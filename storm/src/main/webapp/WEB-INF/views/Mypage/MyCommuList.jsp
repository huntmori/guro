<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>
		<c:forEach var="data" items="${LIST}">
			<div align="center">
				<c:if test="${not empty sessionScope.UID and empty SHOWLIST}">
					<input type="button" data-1="add" value="언팔로우" class="fBtn" align="center" style="color:black">
				</c:if>
				<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'N'}">
					<input type="button" data-1="follow" value="팔로우재등록" class="fBtn" align="center" style="color:black" >
				</c:if>
				<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'Y'}">
					<input type="button" data-1="unfollow" value="팔로우취소" class="fBtn" align="center" style="color:black">
				</c:if>
			</div>
			<div class="box" "onclick="location.href='../Mypage/MyCommuList.storm?communo=${data.communo}&nowPage=${PINFO.nowPage}'">
				<div id="change">
					<img src="#" alt="커뮤니티이미지" width="300"/><br><br>
				<div class="inner">
					<h3>${data.communame}</h3>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
