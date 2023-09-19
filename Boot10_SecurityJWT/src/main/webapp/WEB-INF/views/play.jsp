<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/play.jsp</title>
</head>
<body>
	<div class="container">
		<h3>노는 페이지</h3>
		<p><sec:authentication property="principal.username"/> 님  신나게 놀아요!</p>
		<a href="/users/logout">로그아웃</a>
	</div>
</body>
</html>








