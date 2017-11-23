<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
/* DivTable.com */
.divTable{
	display: table;
	width: 100%;
}
.divTableRow {
	display: table-row;
}
.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
}
.divTableCell, .divTableHead {
	border: 1px solid #999999;
	display: table-cell;
	padding: 3px 10px;
}
.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
	font-weight: bold;
}
.divTableFoot {
	background-color: #EEE;
	display: table-footer-group;
	font-weight: bold;
}
.divTableBody {
	display: table-row-group;
}
</style>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>
	<form	id="searchForm" action="">
		<c:forEach	var="tag"	items="${TAG_LIST}"	varStatus="status">
			<div style="width:300px;">
				<input	type="checkbox" 	id="tagChecker"	value="${tag.tag_id}">${tag.tag_name}
			</div>
			<c:if	test="${status.count mod 4 eq 0}">
				<br>
			</c:if>
		</c:forEach>
	</form>
	<div class="divTable">
		<div	class="divTableBody">
			<c:forEach	var="data"	items="${APP_LIST}">
				<div	class="divTableRow">
					<div class="divTableCell"  ></div><!-- APP IMG -->
					<div class="divTableCell">${data.title}</div>
					<div class="divTableCell">${data.releaseDate}</div>
					<div class="divTableCell">${data.price}</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>