<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
	$(function(){
		 $("#delete").on("click", function(){
			var seq=$("input[name=no]").val();
		 $(location).attr("href", "boardDelete?no="+ seq);
	 })
})
</script>
</head>
<body>
	<div class="container">
		<form action="boardModifyProc">
			<input type="hidden" value="${loginId }" name="id">
			<input type="hidden" value="${listDetail[0].no }" name="no">
			<lable>제목</lable>
			<input class="form-control" type="text" name="title" value="${listDetail[0].title}">
			<div class="form-group">
				<label for="exampleFormControlTextarea1">내용</label>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="3" name="text">${listDetail[0].text}</textarea>
			</div>
				<input type="submit" value="수정하기"> 
				<input type="button" id="delete" value="글삭제">
		</form>
	</div>
</body>
</html>