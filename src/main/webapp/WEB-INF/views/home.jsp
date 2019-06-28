<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="loginProc">
	<input type = "text" name="id">
	<input type = "text" name="pw">
	<input id="login" type="submit" value="로그인">
	<input id="join" type="button" value="회원가입">
</form>
</body>
<script>
	document.getElementById("join").onclick = function(){
		location.href="joinForm"
	}
</script>
</html>