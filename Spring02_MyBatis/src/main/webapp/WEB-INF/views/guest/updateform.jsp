<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>방명록 글 수정 폼</h1>
		<form action="${pageContext.request.contextPath }/guest/update" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" name="num" value="${dto.num }" readonly/>
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" value="${dto.writer }"/>
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5">${dto.content }</textarea>
			</div>
			<div>
				<label for="pwd">글 작성시 입력했던 비밀번호</label>
				<input type="password" id="pwd" name="pwd"/>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>




