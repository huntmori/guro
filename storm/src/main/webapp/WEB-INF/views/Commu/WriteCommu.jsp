<%@ page contentType="text/html; charset=UTF-8" %>
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
		$("#signup").attr("href","../Commu/WriteProc.storm");	
	});

</script>
<body>

<div class="header">
  <h1>St0rm2.0</h1>
  <p>Resize the browser window to see the responsive effect.</p>
  <div align="right">
  		<a href="./Join/JoinForm.storm">회원가입</a>
		<a href="./Login/LoginForm.storm">로그인</a><br>
		<input type="text">
		<input type="button" id="sBtn" value="검색"/>
	</div>
</div>

<div class="topnav">
		
		<a href="../storm">홈</a>
		<a id="gameSearch" href="./App/AppList.storm">게임검색</a>
		<a href="./Chart/ChartView.storm">통계</a>
		<a href="./Commu/CommuMain.storm">커뮤니티</a>
		<a href="./Mypage/MypageList.storm">마이페이지</a>
		
</div>

<div class="row">
  <div class="column side">
    <h2>Side</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
  </div>
  <div class="column middle"	id="mainBody">
  	<div	id="searchBar">
  	</div>
    <h2>게시판등록</h2>
    <table align="left">
    	<tr>
    		<td>
    			커뮤니티번호
    		</td>
    		<td>
    			${MAP.VIEW.communo }
    		</td>
    		<td>
				아이디
			</td>
			<td>	
				
			</td>
    	</tr>
		<tr>
			<td>
				번호
			</td>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>
				제목
			</td>
			<td>
				<textarea></textarea>
			</td>
		</tr>
		<tr>
			<td>
				내용
			</td>
			<td>
				<textarea></textarea>
			</td>
		</tr>
    </table>
    <input type="button" id="singup" value="글쓰기">
  </div>
  <div class="column side">
    
  </div>
</div>

<div class="footer">
  <p>Footer</p>
</div>
  
</body>
</html>
 