<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>회원 가입</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	//아이디 중복 검사
	$(document).ready(function(){
		$("#sBtn").click(function(){
			$("#afrm").submit();
		});
	});
	$(document).ready(function(){
		$("#vBtn1").click(function(){
			var	target = $(this);
			var	id = $("#email").val();
			if(id == "") {
				alert("아이디를 입력하세요");
				return;
			}
			$.ajax({
				url : '../Join/Idcheck.storm',
				data : 'email=' + email,
				dataType : 'json',
				type : 'get',
				success : function(data) {
					var	result = data.result;
					if(result == "0") {
						alert("사용가능한 아이디입니다.");
					}
					else {
						alert("이미 가입된 아이디입니다.");
						var	tr = target.parent();
						tr = tr.prev();
						tr = tr.children();
						tr.val("");
						tr.focus();
					}
				},
				error : function() {
					alert("나오지 마라");
				}
			});
		});
	});

<!--	
	function idch() {
		var x=0;
		var divTest = document.getElementById("test");
		divTest.innerHTML = x;
		var layer = document.getElementById("spot");
		if(document.all.spot.style.visibility=="hidden") {
			document.all.spot.style.visibility="visible";
			return false;
		}
		if(document.all.spot.style.visibility=="visible") {
			document.all.spot.style.visibility="hidden";
			return false;
		}
		
	}
	
-->	
	
	
	
	
	
	
	
	
</script>

<body>

<%-- 회원가입폼 --%>
<div class="container" style="background-color:#ffffff">
  
  <h4>회원가입</h4>
  <form method="POST" id="afrm" action="../Join/JoinProc.storm">
    <div class="form-group">
     	<label id="email" >이메일 : </label>
    	<input type="text" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요">
    	<button type="button" class="vBtn1 btn-default" id="vBtn1">중복확인</button>
    	<span id="idch" onclick="idch();return false;"></span>
    	<div id="spot" style="position: absolute; background-color: #ffffff; left:50px; top:50px; visibility: hidden;">
    		<div id="test"></div>
    	</div>
    	
    </div>
    <div class="form-group">
    <br>
      	<label id="pw">비밀번호 : </label>
     	<input type="password" class="form-control" id="pw" name="pw" placeholder="영문,숫자,특수문자 포함 16자리">
    	<button type="button" class="vBtn2 btn-default" id="vBtn2">중복확인</button>
    </div>
    <div class="form-group">
    <div class="pw_re">
    	<label id="pw_re">비밀번호 확인 : </label>
      	<input type="password" class="form-control" id="pw_re" name="pw_re">
    </div>
    <br>
     	<label id="nick">닉네임 : </label>
      	<input type="text" class="form-control" id="nick" name="nick" placeholder="닉네임">
    </div>
    <br>
	<div class="form-group">
	 	<label id="agr">회원약관</label><br>
	  	<textarea class="form-control" rows="5" id="comment">약관은 이러하다.</textarea>
	</div>
    <div class="checkbox">
      	<label><input type="checkbox" name="remember">동의합니다. 그리고 저는 13세 이상입니다.</label>
    </div>
    <div>
    	<br><button type="submit" class="btn btn-default" id="sBtn">회원가입</button>
	</div>
  </form>
 </div>
</body>
</html>
