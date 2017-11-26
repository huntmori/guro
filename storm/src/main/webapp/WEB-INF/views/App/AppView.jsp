<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>이미지</div>
	<div>제목:${APP_INFO.title}</div>
	<div>가격:${APP_INFO.price}</div>
	<div>${APP_INFO.text}</div>
	<div>출시일: ${APP_INFO.realDate}</div>
	<br><br>
	<div>태그:
		<c:forEach	var="TAG"	items="${TAG_LIST}"	varStatus="status">
			<input type="hidden"	id="${TAG.id}"	value="${TAG.id}">
				[${TAG.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>장르:
		<c:forEach	var="GENRE"	items="${GENRE_LIST}"	varStatus="status">
			<input type="hidden"	id="${GENRE.id}"	value="${GENRE.id}">
				[${GENRE.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>개발사:
		<c:forEach	var="DEV"	items="${DEVELOPER_LIST}"	varStatus="status">
			<input type="hidden"	id="${DEV.id}"	value="${DEV.id}">
				[${DEV.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>배급사:
		<c:forEach	var="PUB"	items="${PUBLISHER_LIST}"	varStatus="status">
			<input type="hidden"	id="${PUB.id}"	value="${PUB.id}">
				[${PUB.name}]
		</c:forEach>	
	</div>	
	<br><br>
	<div>카테고리:
		<c:forEach	var="CATEGORY"	items="${CATEGORY_LIST}"	varStatus="status">
			<input type="hidden"	id="${CATEGORY.id}"	value="${CATEGORY.id}">
				[${CATEGORY.name}]
		</c:forEach>
	</div>
	
	<div>언어:<br>
	<table>
		<tr>
			<th>언어</th>
			<th>인터페이스</th>
			<th>음성</th>
			<th>자막</th>
		</tr>
	
		<c:forEach	var="LANGUAGE"		items="${LANGUAGE_LIST}"	varStatus="status">
			<tr>
					<td ><input type="hidden"	id="${LANGUAGE.id}"	value="${LANGUAGE.id}">
								[${LANGUAGE.name}]</td> 
					<td>${LANGUAGE.supported_interface}</td> 
					<td>${LANGUAGE.supported_voice}</td> 
					<td>${LANGUAGE.supported_subtitle}</td>
				</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>