<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<div class="container">
		<c:if test="${not empty sessionScope.id }">
			<p>
				<strong>${id }</strong>님 로그인중...
			</p>
		</c:if>
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath }/play">놀러가기</a></li>
			<li><a href="${pageContext.request.contextPath }/users/loginform">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/users/logout">로그아웃</a></li>
		</ul>
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items="${requestScope.noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>










