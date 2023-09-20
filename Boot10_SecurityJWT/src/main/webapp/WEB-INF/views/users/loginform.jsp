<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/loginform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>로그인 폼</h3>
		<c:if test="${param.error != null}">
		    <p>
		        Login Error! <br />
		        ${errMsg}
		    </p>
    	</c:if>
		<form action="/login" method="post">
			<input type="text" name="userName" placeholder="사용자명 입력..."/>
			<input type="text" name="password" placeholder="비밀번호 ..."/>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>