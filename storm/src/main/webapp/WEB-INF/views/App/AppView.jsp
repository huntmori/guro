<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
		
	<div>이미지</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">제목</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.title}
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">가격</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.price}
		</div>
	</div>
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">${APP_INFO.title}</h3>
		</div>
		<div class="panel-body">
  			${APP_INFO.text}
		</div>
	</div>
	
	
	<div>출시일: ${APP_INFO.realDate}</div>
	<br><br>
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">태그</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="TAG"	items="${TAG_LIST}"	varStatus="status">
			<input type="hidden"	id="${TAG.id}"	value="${TAG.id}">
				<button class="btn btn-primary" type="button">
  						${TAG.name}
				</button>
			</c:forEach>	
		</div>
	</div>
	
	
	<div class="panel panel-primary">
		<div class="panel-heading">
  			<h3 class="panel-title">장르</h3>
		</div>
		<div class="panel-body">
  			<c:forEach	var="GENRE"	items="${GENRE_LIST}"	varStatus="status">
				<input type="hidden"	id="${GENRE.id}"	value="${GENRE.id}">
					<button class="btn btn-primary" type="button">
						${GENRE.name}
					</button>
			</c:forEach>	
		</div>
	</div>
	
	<br><br>
	<div>개발사:
		<c:forEach	var="DEV"	items="${DEVELOPER_LIST}"	varStatus="status">
			<input type="hidden"	id="${DEV.id}"	value="${DEV.id}">
				<button class="btn btn-primary" type="button">
					${DEV.name}
				</button>
		</c:forEach>	
	</div>
	<br><br>
	<div>배급사:
		<c:forEach	var="PUB"	items="${PUBLISHER_LIST}"	varStatus="status">
			<input type="hidden"	id="${PUB.id}"	value="${PUB.id}">
				<button class="btn btn-primary" type="button">
					${PUB.name}
				</button>
		</c:forEach>	
	</div>	
	<br><br>
	<div>카테고리:
		<c:forEach	var="CATEGORY"	items="${CATEGORY_LIST}"	varStatus="status">
			<input type="hidden"	id="${CATEGORY.id}"	value="${CATEGORY.id}">
				<button class="btn btn-primary" type="button">
					${CATEGORY.name}
				</button>
		</c:forEach>
	</div>
	
	<div>언어:<br>
	<table class="table table-striped">
		<tr>
			<th>언어</th>
			<th>인터페이스</th>
			<th>음성</th>
			<th>자막</th>
		</tr>
		
		
		<c:forEach	var="LANGUAGE"		items="${LANGUAGE_LIST}"	varStatus="status">
			<tr>
					<td>
						<input type="hidden"	id="${LANGUAGE.id}"	value="${LANGUAGE.id}">
							${LANGUAGE.name}
					</td> 
					<td>
						<c:if	test ="${LANGUAGE.supported_interface eq 'Y' }">
							지원
						</c:if>
						<c:if	test ="${LANGUAGE.supported_interface ne 'Y' }">
							미지원
						</c:if>
					</td> 
					<td>
						<c:if	test ="${LANGUAGE.supported_voice eq 'Y' }">
							지원
						</c:if>
						<c:if	test ="${LANGUAGE.supported_voice ne 'Y' }">
							미지원
						</c:if>
					</td> 
					<td>
						<c:if	test ="${LANGUAGE.supported_subtitle eq 'Y' }">
							지원
						</c:if>
						<c:if	test ="${LANGUAGE.supported_subtitle ne 'Y' }">
							미지원
						</c:if>
					</td>
				</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>