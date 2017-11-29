<%@ page contentType="text/html; charset=EUC-KR" %>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {    box-sizing: border-box;	}
body {  margin: 0;	}

/* Style the header */
.header {   	background-color: #f1f1f1;
    				padding: 20px;
    				text-align: center;
}

/* Style the top navigation bar */
.topnav {		overflow: hidden;
    				background-color: #333;
}

/* Style the topnav links */
.topnav a {    	float: left;
    					display: block;
    					color: #f2f2f2;
    					text-align: center;
    					padding: 14px 16px;
    					text-decoration: none;
}

/* Change color on hover */
.topnav a:hover {
    background-color: #ddd;
    color: black;
}

/* Create three unequal columns that floats next to each other */
.column {
    float: left;
    padding: 10px;
}

/* Left and right column */
.column.side {
    width: 15%;
}

/* Middle column */
.column.middle {
    width: 80%;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
    .column.side, .column.middle {
        width: 100%;
    }
}

/* Style the footer */
.footer {
    background-color: #f1f1f1;
    padding: 10px;
    text-align: center;
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#searchBar").load("App/AppSearchForm.storm");
		$("#singup").click(function(){
			//	���Ἲ �˻縦 �Ѵ�.
			
			$("#cFrm").submit();
		});	
	});

</script>
<body>

<div class="header">
  <h1>St0rm2.0</h1>
  <p>Resize the browser window to see the responsive effect.</p>
  <div align="right">
  		<a href="./Join/JoinForm.storm">ȸ������</a>
		<a href="./Login/LoginForm.storm">�α���</a><br>
		<input type="text">
		<input type="button" id="sBtn" value="�˻�"/>
	</div>
</div>

<div class="topnav">
		
		<a href="../storm">Ȩ</a>
		<a id="gameSearch" href="./App/AppList.storm">���Ӱ˻�</a>
		<a href="./Chart/ChartView.storm">���</a>
		<a href="./Commu/CommuMain.storm">Ŀ�´�Ƽ</a>
		<a href="./Mypage/MypageList.storm">����������</a>
		
</div>

<div class="row">
  <div class="column side">
    <h2>Side</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
  </div>
  <div class="column middle" id="mainBody">
  	<div	id="searchBar">
  	</div>
    <h2>�Խ��ǵ��</h2>
    <form method="POST" id="cFrm" action="../Commu/WriteProc.storm">
    	<table align="left">
    		<tr>
	    		<td>Ŀ�´�Ƽ��ȣ</td>
    			<td>
    				<input type="text" id="communo" name="communo" value="${MAP.VIEW.communo }" readonly>
	    		</td>
   				<td>�۾���</td>
				<td>
				<input type="text" id="writer" name="writer" value="${sessionScope.UID}" readonly>
				</td>
    		</tr>
			<tr>
				<td>����</td>
				<td>
					<textarea id="boardname" name="boardname"></textarea>
				</td>
			</tr>
			<tr>
				<td>����</td>
				<td>
					<textarea id="boardtext" name="boardtext"></textarea>
				</td>
			</tr>
    	</table><br><br><br><br><br><br><br><br>
    	<input type="button" id="singup" value="�۾���">
    </form>
  </div>
  <div class="column side">
    
  </div>
</div>

<div class="footer">
  <p>Footer</p>
</div>
  
</body>
</html>
 