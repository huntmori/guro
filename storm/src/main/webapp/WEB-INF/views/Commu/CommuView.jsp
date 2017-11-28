<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$(".fbt").click(function() {
			var whatdo = $(this).attr("data-1");
			$(location).attr("href", "../Commu/CommuFollow.storm?whatdo="+whatdo+"&data2=${data.communo}&nowpage=${NOWPAGE}");
		});
		$("#clbtn").click(function(){
			//	목록보기 요청을 하면 된다.
			$(location).attr("href", "../Commu/CommuMain.storm?nowPage=${nowPage}");
		});
	});
</script>
<body>
	<table width="1000" border="1">
		<tr>
				<%--	컨트롤로에서 정보받기	--%>
			<td><img src="http://ww2.sjkoreancatholic.org/files/testing_image.jpg" width="350"></td>
		</tr>
		<tr>
	 		<td><h1>커뮤니티명 : ${MAP.VIEW.communame}</h1>${MAP.VIEW.commutext}</td>
	 	</tr>
	 	<tr>
			<td>출시일 : ${MAP.VIEW.commudate}<br>
			가격 : ${MAP.VIEW.commuprice}
			</td>
			 
		</tr>
	</table>
	<div align="center">
		<c:if test="${not empty sessionScope.UID and empty SHOWLIST}">
			<input type="button" data-1="add" value="팔로우" class="fbt" align="center" style="color:black">
		</c:if>
		<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'N'}">
			<input type="button" data-1="follow" value="팔로우재등록" class="fbt" align="center" style="color:black" >
		</c:if>
		<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'Y'}">
			<input type="button" data-1="unfollow" value="팔로우취소" class="fbt" align="center" style="color:black">
		</c:if>
		<input type="button" id="clbtn" value="커뮤니티목록보기" align="center" style="color:black">
	</div>
	<div>
		<br><br><br>
	</div>
	<c:forEach var="review"		items="${REVIEWLIST}">
   		<table style="width: 80%; background-color:#001638;">
    		 <tr>
    		 	<td>
    				<div class="row">
        				<div class="col-sm-2 text-center">
    		 				<img src="../img/GoodsImg/${review.usrimgname}"  height="150" width="150" alt="구매상품사진" align="rigth">
   	 					</div>
 		 				<div class="col-sm-2 text-center">
 		 		<br> 
 		 					<img src="../img/logo/profile.png" class="img-circle" height="100" width="100" alt="회원사진"align="left">
        				</div>
				<br>
          			<h4><small>닉네임 :</small>${review.nickname}  <small>구매한 제품 :</small>${review.gname} &nbsp;&nbsp;</h4>
          <p>${review.content}</p>
	        <br>
  
  
      </div></td>
      </tr>
      </table>
    </c:forEach>


</body>
</html>