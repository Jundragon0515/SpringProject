<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$("#boardWrite").on("click", function() {
			$(location).attr("href", "boardWrite")
		})
        $("#home").on("click", function(){
            $(location).attr("href", "loginProc")
        })
	})
</script>
<title>Board</title>
<style>
.headText {
	text-align: center;
}

.container {
	width: 100%;
}

.etc {
	width: 20%;
}

.title {
	width: 40%
}
.foot {
    position: relative;
    left:80%;
    }
    .navi{
    position: relative;
        left:40%;
    }
</style>
</head>
<body>
	<div class="container">
		<table class="table">
			<thead class="thead-dark">
				<tr class="headText">
					<th scope="col" class="etc">No</th>
					<th scope="col" class="title">제목</th>
					<th scope="col" class="etc">작성자</th>
					<th scope="col" class="etc">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="temp" items="${BoardList }">
					<tr>
						<th scope="row" class="headText">${temp.no }</th>
						<td><a href="boardDetail?no=${temp.no }">${temp.title }</a></td>
						<td class="headText">${temp.id }</td>
						<td class="headText">${temp.click }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
		<div class="btn-toolbar" role="toolbar"
			aria-label="Toolbar with button groups">
			<div class="btn-group navi" role="group" aria-label="Third group">
				${navi }
			</div>
		</div>

		<br>
		<div class="footer">
		    <button class="foot" id="boardWrite">글쓰기</button>
		    <button class="foot" id="home">홈으로</button>
		</div>
	</div>
</body>
</html>