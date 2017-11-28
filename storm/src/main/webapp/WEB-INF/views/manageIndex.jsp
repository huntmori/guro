<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<P>The time on the server is ${serverTime}.</P>	
	<a href="./Test.storm">test</a><br>
	<a href="./UserManager/UserList.storm">User Management(사용자관리)</a><br>
	<a href="./BoardManager/BoardList.storm">Board Management(게시판관리)</a><br>
</body>
</html>
