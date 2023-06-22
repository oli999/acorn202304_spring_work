<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>방명록 작성 폼</h1>
		<form action="${pageContext.request.contextPath }/guest/insert" method="post">
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" />
			</div>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="10"></textarea>
			</div>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" id="pwd" name="pwd"/>
			</div>
			<button type="submit">등록</button>
		</form>
	</div>
</body>
</html>





