<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

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

<body>
	<div class="header">
  <h1>St0rm2.0</h1>
  <p>Resize the browser window to see the responsive effect.</p>
  <div align="right">
  		<a href="#">ȸ������</a>
		<a href="#">�α���</a><br>
		<input type="text">
		<input type="button" id="sBtn" value="�˻�"/>
	</div>
</div>

<div class="topnav">
		
		<a href="../storm">Ȩ</a>
		<a id="gameSearch">���Ӱ˻�</a>
		<a href="#">���</a>
		<a href="#">Ŀ�´�Ƽ</a>
		<a href="#">����������</a>
		
</div>
</body>
</html>