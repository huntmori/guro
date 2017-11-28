<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>ȸ�� ����</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	//���̵� �ߺ� �˻�
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
				alert("���̵� �Է��ϼ���");
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
						alert("��밡���� ���̵��Դϴ�.");
					}
					else {
						alert("�̹� ���Ե� ���̵��Դϴ�.");
						var	tr = target.parent();
						tr = tr.prev();
						tr = tr.children();
						tr.val("");
						tr.focus();
					}
				},
				error : function() {
					alert("������ ����");
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

<%-- ȸ�������� --%>
<div class="container" style="background-color:#ffffff">
  
  <h4>ȸ������</h4>
  <form method="POST" id="afrm" action="../Join/JoinProc.storm">
    <div class="form-group">
     	<label id="email" >�̸��� : </label>
    	<input type="text" class="form-control" id="email" name="email" placeholder="�̸����� �Է��ϼ���">
    	<button type="button" class="vBtn1 btn-default" id="vBtn1">�ߺ�Ȯ��</button>
    	<span id="idch" onclick="idch();return false;"></span>
    	<div id="spot" style="position: absolute; background-color: #ffffff; left:50px; top:50px; visibility: hidden;">
    		<div id="test"></div>
    	</div>
    	
    </div>
    <div class="form-group">
    <br>
      	<label id="pw">��й�ȣ : </label>
     	<input type="password" class="form-control" id="pw" name="pw" placeholder="����,����,Ư������ ���� 16�ڸ�">
    	<button type="button" class="vBtn2 btn-default" id="vBtn2">�ߺ�Ȯ��</button>
    </div>
    <div class="form-group">
    <div class="pw_re">
    	<label id="pw_re">��й�ȣ Ȯ�� : </label>
      	<input type="password" class="form-control" id="pw_re" name="pw_re">
    </div>
    <br>
     	<label id="nick">�г��� : </label>
      	<input type="text" class="form-control" id="nick" name="nick" placeholder="�г���">
    </div>
    <br>
	<div class="form-group">
	 	<label id="agr">ȸ�����</label><br>
	  	<textarea class="form-control" rows="5" id="comment">����� �̷��ϴ�.</textarea>
	</div>
    <div class="checkbox">
      	<label><input type="checkbox" name="remember">�����մϴ�. �׸��� ���� 13�� �̻��Դϴ�.</label>
    </div>
    <div>
    	<br><button type="submit" class="btn btn-default" id="sBtn">ȸ������</button>
	</div>
  </form>
 </div>
</body>
</html>
