<%-- <%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<body>
	<br>
	<div>STORM 2.0 대문입니다.</div><br><br>
	<div>
		<input type="text">
		<input type="button" id="sBtn" value="검색"></button>
		<a href="#">회원가입</a><span>
		<a href="#">로그인</a><br><br>
		<a href="#">홈</a>
		<a href="./App/AppList.storm">게임검색</a>
		<a href="#">통계</a>
		<a href="#">커뮤니티</a>
		<a href="#">마이페이지</a>
	</div>
</body>
</html>
 --%>
 <%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
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
	$(document).ready(function()
	{
		$("#searchBar").load("App/AppSearchForm.storm");
		 /* $("#gameSearch").click(function()
		{
			$("#mainBody").load("./App/AppList.storm");
		});  */
    	//$("#mainBody").load("가져올페이지");
	});

</script>
<body>

<div class="header">
  <h1>St0rm2.0</h1>
  <p>Resize the browser window to see the responsive effect.</p>
  <div align="right">
  		<a href="./Member/MemberForm.storm">회원가입</a>
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
    <h2>Main Content</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique. Quisque vehicula, risus eget aliquam placerat, purus leo tincidunt eros, eget luctus quam orci in velit. Praesent scelerisque tortor sed accumsan convallis.</p>
  </div>
  <div class="column side">
    <h2>Side</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit..</p>
  </div>
</div>

<div class="footer">
  <p>Footer</p>
</div>
  
</body>
</html>
 