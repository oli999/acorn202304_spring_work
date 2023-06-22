<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/loginform.jsp</title>
</head>
<body>
	<h1>로그인 폼 입니다.</h1>
	<form action="/users/login" method="post">
		<input type="text" name="email" placeholder="이메일 입력..."/>
		<input type="password" name="password" placeholder="비밀번호 입력" />
		<button type="submit">로그인</button>
	</form>
</body>
</html>