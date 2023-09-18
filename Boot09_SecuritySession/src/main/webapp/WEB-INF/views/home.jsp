<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
	spring security tag 를 이용해서 인증받은 사용자인지 여부를 알아내서 
	만일 인증 받은 사용자라면 el 에서 사용자 이름을 사용할수 있도록 설정 
 --%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username" var="id"/>
</sec:authorize>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다</h1>
		<c:choose>
			<c:when test="${empty id }">
				<a href="/users/loginform">로그인</a>
			</c:when>
			<c:otherwise>
				<strong>${id }</strong> 님 로그인중...
				<a href="/users/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>	
		<ul>
			<li><a href="/play">놀러가기</a></li>
			<li><a href="/admin/user_delete">ADMIN 페이지</a></li>
			<li><a href="/staff/user_list">STAFF 페이지</a></li>
		</ul>
	</div>
</body>
</html>












