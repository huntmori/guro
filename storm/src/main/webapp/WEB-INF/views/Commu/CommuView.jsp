<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>Document</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$(".fbt").click(function() {
			var whatdo = $(this).attr("data-1");
			$(location).attr("href", "../Commu/CommuFollow.storm?whatdo="+whatdo+"&data2=${MAP.VIEW.communo}&nowpage=${nowPage}");
			alert(whatdo+"&data2=${MAP.VIEW.communo}&nowpage=${nowPage}");
		});
		$("#clbtn").click(function(){
			//	��Ϻ��� ��û�� �ϸ� �ȴ�.
			$(location).attr("href", "../Commu/CommuMain.storm?nowPage=${nowPage}");
		});
		$("#Mbtn").click(function(){
			//	��Ϻ��� ��û�� �ϸ� �ȴ�.
			$(location).attr("href", "../Commu/WriteCommu.storm?nowPage=${nowPage}&communo=${MAP.VIEW.communo}");
		});
	});
</script>
<body>
	<table width="500" border="1">
		<tr>
				<%--	��Ʈ�ѷο��� �����ޱ�	--%>
			<td><img src="http://ww2.sjkoreancatholic.org/files/testing_image.jpg" width="300"></td>
		</tr>
		<tr>
	 		<td><h1>Ŀ�´�Ƽ�� : ${MAP.VIEW.communame}</h1>${MAP.VIEW.commutext}</td>
	 	</tr>
	 	<tr>
			<td>����� : ${MAP.VIEW.commudate}<br>
			���� : ${MAP.VIEW.commuprice}
			</td>
			 
		</tr>
	</table>
	<div align="left">
		<c:if test="${not empty sessionScope.UID and empty SHOWLIST}">
			<input type="button" data-1="add" value="�ȷο�" class="fbt" align="center" style="color:black">
		</c:if>
		<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'N'}">
			<input type="button" data-1="follow" value="�ȷο�����" class="fbt" align="center" style="color:black" >
		</c:if>
		<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'Y'}">
			<input type="button" data-1="unfollow" value="�ȷο����" class="fbt" align="center" style="color:black">
		</c:if>
		<c:if test="${not empty sessionScope.UID and SHOWLIST eq 'Y'}">
			<input type="button" id="Mbtn" value="�Խ��ǵ��" align="center" style="color:black">
		</c:if>
		<input type="button" id="clbtn" value="Ŀ�´�Ƽ��Ϻ���" align="center" style="color:black">
	</div>
	<div>
		<br><br><br>
	</div>
	
	<c:if test="${not empty BLIST}">
	 <table width="500" border="1" align="center">
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�۾���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
		</tr>
		<c:forEach var="data" items="${BLIST}">
			<tr>
				<td>	</td>
				<td>
					<a href=
					"../Commu/BoardView.storm?nowPage=
					${PINFO.nowPage }&barodno=${data.boardno}">
					${data.boardname }</a>
				</td>
				<td></td>
				<td>${data.boardtext }</td>
		</c:forEach>
	</table> 
	</c:if>
	
	
	
	
	<%-- <c:forEach var="review"		items="${REVIEWLIST}">
   		<table style="width: 80%; background-color:#001638;">
    		 <tr>
    		 	<td>
    				<div class="row">
        				<div class="col-sm-2 text-center">
    		 				<img src="../img/GoodsImg/${review.usrimgname}"  height="150" width="150" alt="���Ż�ǰ����" align="rigth">
   	 					</div>
 		 				<div class="col-sm-2 text-center">
 		 		<br> 
 		 					<img src="../img/logo/profile.png" class="img-circle" height="100" width="100" alt="ȸ������"align="left">
        				</div>
				<br>
          			<h4><small>�г��� :</small>${review.nickname}  <small>������ ��ǰ :</small>${review.gname} &nbsp;&nbsp;</h4>
          <p>${review.content}</p>
	        <br>
  
  
      </div></td>
      </tr>
      </table>
    </c:forEach> --%>


</body>
</html>