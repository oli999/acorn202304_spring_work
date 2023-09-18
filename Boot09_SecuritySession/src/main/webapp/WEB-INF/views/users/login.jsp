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
	
	<c:choose>
		<c:when test="${empty username }">
			<p>아이디 혹은 비밀번호가 틀려요! <a href="/users/loginform">다시 시도</a></p>
		</c:when>
		<c:otherwise>
			<p>로그인 되었습니다.</p>
			<p><strong>${username }</strong> 님 반갑습니다. <a href="/play">놀러가기</a></p> 
		</c:otherwise>
	</c:choose>
	
</body>
</html>










