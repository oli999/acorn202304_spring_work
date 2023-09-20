<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/login.jsp</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="username"/>
	</sec:authorize>
	
	
	<p>로그인 되었습니다.</p>
	<p><strong>${username }</strong> 님 반갑습니다. <a href="/play">놀러가기</a></p> 

	<br>
	<a href="/">인덱스로</a>
</body>
</html>










