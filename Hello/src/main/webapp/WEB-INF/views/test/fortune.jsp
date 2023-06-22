<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/test/fortune.jsp</title>
</head>
<body>
	<div class="container">
		<p>
			오늘의 운세 : <strong>${fortuneToday }</strong>
			<a href="/hello/">인덱스로 가기</a>
		</p>
	</div>
</body>
</html>