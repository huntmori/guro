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
<script>
	$(document).ready(function()
	{
		$("#header").load("../header.jsp");
		$("#searchBar").load("../App/AppSearchForm.storm");
		
	});
</script>

<body>
	<div	id="header"></div>
	<div	id="searchBar"></div>
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<%--	[이전] --%>
				<c:if test="${PINFO.startPage eq 1}">
					[이전]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="">[이전]</a>
				</c:if>
				<%--	[1][2][3] --%>
				<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="../App/AppList.storm?nowPage=${page}">[${page}]</a>
				</c:forEach>
				<%--	[다음] --%>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
	<div class="divTable"	style="WIDTH:800px;">
		<div	class="divTableBody">
			<div	class="divTableRow" >
				<div class="divTableCell" >이미지</div><!-- APP IMG -->
				<div class="divTableCell">제목</div>
				<div class="divTableCell" >출시일</div>
				<div class="divTableCell" >가격</div>
			</div>
			<c:forEach	var="data"	items="${APP_LIST}">
					
					<div	class="divTableRow" >
						<div class="divTableCell" ></div><!-- APP IMG -->
						<div class="divTableCell title"  >
							<a	href="../App/AppView.storm?nowPage=${PINFO.nowPage}&app_id=${data.id}">
								${data.title}
							</a>
						</div>
						
						<div class="divTableCell" >${data.realDate}</div>
						<div class="divTableCell" >${data.price}</div>
					</div>
				
			</c:forEach>
		</div>
	</div>
</body>
</html>