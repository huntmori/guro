<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- 		<link rel="stylesheet" href="../css/commu/css/main.css" /> -->
		<style>#change:hover{opacity:0.6}
		</style>
</head>
<body>
	<jsp:include page="../Main/header.jsp" flush="true" />
	<section class="main clearfix" style="background-color:black;">
		<jsp:include page="../Main/logo.jsp" flush="true" />
		<div>
			<div style="font-size:30px; color:white; text-align: center; font-weight:bold;"><br>최신게임<br><br></div>

<!-- 	     		<c:forEach	var="data" items="${RECENT_LIST}" begin="0" end="4">
  					<div align="center"  style="float:left">
  						<div class="box" style="background-color:#001638; width:280px;">
							<a	href="../Goods/GoodsDetailView.ust?GOODS_NO=${data.no}">
								<div id="change"> 
								<img src="../img/GoodsImg/${data.img_name}" alt="게임사진" width="280"/>
								<div class="inner">
									<h3>${data.name}</h3>
									<p>${data.price}원</p>
									<p>출시일 (${data.releaseDate})</p>
								</div>
								</div>
							</a>&nbsp;&nbsp;
							</div>	
						</div>
					
				</c:forEach>  --> 
	
				
<div><br>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div>
			<div style="position: absolute; top:830px; left:47%;"><div style="font-size:30px; color:white; text-align:center; font-weight:bold;"><br>추천게임<br><br></div></div>
				 <div style="position:absolute; top:950px;">
				 <div style="margin-left:outo; margin-right:outo;">
<!-- 	 		 	 <c:forEach	var="data1" items="${RANDOM_LIST}">
  					<div align="center" style="float:left">
  						<div class="box" style="background-color:#001638; width:280px;">
							<a	href="../Goods/GoodsDetailView.ust?GOODS_NO=${data1.no}">
								<div id="change"> 
								<img src="../img/GoodsImg/${data1.img_name}" alt="게임사진" width="280"/>
								<div class="inner">
									<h3>${data1.name}</h3>
									<p>${data1.price}원</p>
									<p>출시일 (${data1.releaseDate})</p>
								</div>
								</div>
							</a>
							</div>
						</div>
				</c:forEach> -->
				</div>
			</div>
		</div>
	</section>
</body>
</html>




