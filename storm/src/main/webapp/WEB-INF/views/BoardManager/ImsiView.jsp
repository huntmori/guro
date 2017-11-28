<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 	상세보기로 리디렉트 시킨다. -->
	<!-- 
	redirect는 안된다.
	왜냐하면 redirect를 시키면 이전에 사용한 모든 기능이 무효화 되고 리다이렉트 된다.
	
	redirct 방식이 아니고 일반 요청 방식으로 처리해야 한다.			

		수정이면 수정 여부에 관계없이 상세보기로 보내면 된다.
		
		삭제이면 	삭제가 성공되면 사용자리스트보기로 보내면 되고
					삭제가 실패되면 사용자상세보기로 보내고 싶다. 

	 -->
	<c:if test="${from eq 'update'}">
		<script>
			alert("수정이 완료되었습니다.");
			document.location.href = "../BoardManager/BoardView.storm?nowPage=${nowPage}&boardNo=${boardNo}";
		</script>
	</c:if>
	<c:if test="${from eq 'delete'}">
		<script>
			alert("삭제 처리되었습니다.");
			document.location.href = "../BoardManager/BoardList.storm";
		</script>
	</c:if>
</body>
</html>