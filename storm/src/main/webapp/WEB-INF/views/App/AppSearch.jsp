<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<form	id="searchForm"	action="">
	  <br>태그로 검색
	  	<table>
	  		<tr>
	  		<td>
	  		<c:forEach	var="TAG"	items="${TAG_LIST}"  varStatus="cnt">
	  			<input type="checkBox"	value="${TAG}.id">${TAG.name}
	  			<c:if test="${ (cnt.count mod 6 )eq 0 }">
	  				<br>
	  			</c:if>
	  		</c:forEach>			  			
			</td>
			</tr>
			
	  		
		</table>	  		
	  <br>카테고리로 검색
	  		<c:forEach	var="CATEGORY"	items="${CATEGORY_LIST}">
	  			<input type="checkBox"	value="${CATEGORY}.id">${CATEGORY.name}
	  		</c:forEach>
	  <br>장르로 검색
	  		<c:forEach	var="GENRE"	items="${GENRE_LIST}">
	  			<input type="checkBox"	value="${GENRE}.id">${GENRE.name}
	  		</c:forEach>
	  <br><input type="button" id="submitButton" value="검색">	  
	  </form>
</body>
</html>
