<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
	<form action="joinProc" method="post" enctype="multipart/form-data">
		아이디 : <span id="idCheck"></span><br>
		<input id="id" type="text" name="id"><br>
		패스워드 : <br>
		<input type="password" name="pw"><br>
		패스워드확인 : <br>
		<input type="password"><br>
		이메일 : <br>
		<input type="email" name="email"><br>
		프로필사진 : <br>
		<input type="file" name="profile"><br><br>
		<input type="submit" value="회원가입">
		<input type="button" value="뒤로가기">
	</form>
	<script>
		$("#id").on("input", function(){
			$.ajax({
				url:"idCheck.do",
				data : { "id" : $("#id").val()},
			}).done(function(resp){
				$("#idCheck").text(resp);
			})
		})
	</script>
	</div>
</body>
</html>