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
	<div>�̹���</div>
	<div>����:${APP_INFO.title}</div>
	<div>����:${APP_INFO.price}</div>
	<div>${APP_INFO.text}</div>
	<div>�����: ${APP_INFO.realDate}</div>
	<br><br>
	<div>�±�:
		<c:forEach	var="TAG"	items="${TAG_LIST}"	varStatus="status">
			<input type="hidden"	id="${TAG.id}"	value="${TAG.id}">
				[${TAG.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>�帣:
		<c:forEach	var="GENRE"	items="${GENRE_LIST}"	varStatus="status">
			<input type="hidden"	id="${GENRE.id}"	value="${GENRE.id}">
				[${GENRE.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>���߻�:
		<c:forEach	var="DEV"	items="${DEVELOPER_LIST}"	varStatus="status">
			<input type="hidden"	id="${DEV.id}"	value="${DEV.id}">
				[${DEV.name}]
		</c:forEach>	
	</div>
	<br><br>
	<div>��޻�:
		<c:forEach	var="PUB"	items="${PUBLISHER_LIST}"	varStatus="status">
			<input type="hidden"	id="${PUB.id}"	value="${PUB.id}">
				[${PUB.name}]
		</c:forEach>	
	</div>	
	<br><br>
	<div>ī�װ�:
		<c:forEach	var="CATEGORY"	items="${CATEGORY_LIST}"	varStatus="status">
			<input type="hidden"	id="${CATEGORY.id}"	value="${CATEGORY.id}">
				[${CATEGORY.name}]
		</c:forEach>
	</div>
	
	<div>���:<br>
	<table>
		<tr>
			<th>���</th>
			<th>�������̽�</th>
			<th>����</th>
			<th>�ڸ�</th>
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