<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/info.jsp</title>
</head>
<body>
	<div class="container">
		<h1>개인 정보 페이지</h1>
		<table>
			<tr>
				<th>아이디</th>
				<td>${sessionScope.id }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${requestScope.address }</td>
			</tr>
		</table>
	</div>
</body>
</html>