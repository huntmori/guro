<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function Search() {
		//	무결성 검사 하고
		$("#sfrm").submit();
	}
	$(document).ready(function(){
		$(".fBtn").click(function() {
			alert("commufollow실행하는 함수");
			var whatdo = $(this).attr("data-1");
			$(location).attr("href", "../Commu/CommuFollow.storm?whatdo="+whatdo+"&data2=${LIST.communo}&nowPage=${PINFO.nowPage}");
		});	
		$("#sBtn").click(Search);
		
	});
</script>
<body>
	<div>
<!-- 	검색창을 만든다. 
		<form method="POST" id="sfrm" action="../Commu/CommuSearch.storm">
			<input type="hidden" name="nowPage" value="1">
			<table width="1000" border="1" align="center">
				<tr>
					<td align="center">
						<select id="target" name="target">
							<option value="category">카테고리</option>
							<option value="genre">장르</option>
							<option value="tag">태그</option>
						</select>
						<input type="text" id="word" name="word">
						<input type="button" id="sBtn" value="검색하기">					
					</td>
				</tr>
			</table>
		</form>-->
	<!--<img src="../img/commu/${data.commuImgName}" alt="" width="380"/>  -->
<!-- 	목록보기를 보여준다. -->

		<c:forEach var="data" items="${LIST}">
			<div align="center">
				<c:if test="${not empty sessionScope.UID and empty SHOWLIST}">
					<input type="button" data-1="add" value="팔로우" class="fBtn" align="center" style="color:black">
				</c:if>
				<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'N'}">
					<input type="button" data-1="follow" value="팔로우재등록" class="fBtn" align="center" style="color:black" >
				</c:if>
				<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'Y'}">
					<input type="button" data-1="unfollow" value="팔로우취소" class="fBtn" align="center" style="color:black">
				</c:if>
			</div>
			<div class="box"  style="cursor: pointer;  color:silver; background-color:#001638;" onclick="location.href='../Commu/CommuView.storm?oriNo=${data.communo}&nowPage=${PINFO.nowPage}'">
				<div id="change">
					<img src="http://ww2.sjkoreancatholic.org/files/testing_image.jpg" alt="커뮤니티이미지" width="300"/><br><br>${data.commutext} 								
				</div>
				<div class="inner">
					<h3>${data.communame}</h3>
				</div>
			</div>
		</c:forEach>
	</div>
		

						
	<table width="800" border="1" align="center">
		<tr style="color:silver">
			<td align="center">
				<c:if test="${PINFO.startPage eq 1}">
					[이전]
				</c:if>	
				<c:if test="${PINFO.startPage ne 1}">
					<a href="../Commu/CommuMain.storm?nowPage=${PINFO.startPage - 1}">[이전]</a>
				</c:if>		
				<c:forEach var="i" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					[<a href="../Commu/CommuMain.storm?nowPage=${i}">${i}</a>]								
				</c:forEach>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="../Commu/CommuMain.storm?nowPage=${PINFO.endPage + 1}">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
			

</body>
</html>