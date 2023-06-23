<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>파일 업로드 폼1</h3>
		<form action="${pageContext.request.contextPath}/file/upload" method="post" 
			enctype="multipart/form-data">
			제목 <input type="text" name="title"/><br />
			첨부파일 <input type="file" name="myFile"/><br/>
			<button type="submit">업로드</button>	
		</form>
		
		<h3>파일 업로드 폼2</h3>
		<form action="${pageContext.request.contextPath}/file/upload2" method="post" 
			enctype="multipart/form-data">
			제목 <input type="text" name="title"/><br />
			첨부파일 <input type="file" name="myFile"/><br/>
			<button type="submit">업로드</button>	
		</form>
		<!-- 
			이미지를 선택해서 업로드 버튼을 누르면 페이지 전환없이 이미지를 업로드하고
			업로드된 파일의 정보를 응답(json) 받아서 
			id 가 imageWrapper 인 div 의 자식요소에 img 요소를 추가해서 업로드된 이미지가 바로 보이도록 프로그래밍 해보세요
			
			- webapp/resources/upload 폴더에 이미지를 저장하세요
			- gura_util.js 를  webapp/resource/js 폴더에 넣어놓고 로딩해서 쓰면 됩니다. 
			- /image/upload 요청 처리는 FileController 에서 하면 됩니다.
		 -->
		<h3>이미지 업로드폼  </h3>
		<form action="${pageContext.request.contextPath }/image/upload" method="post" enctype="multipart/form-data" id="uploadForm">
			이미지 <input type="file" name="image" accept=".jpg, .jpeg, .JPG, .JPEG, .gif, .png, .PNG">
			<button type="submit">업로드</button>
		</form>
		<br>
		<div id="imageWrapper"></div>
		<script src="${pageContext.request.contextPath }/resources/js/gura_util.js"></script>
		<script>
		
			document.querySelector("#uploadForm").addEventListener("submit", (e)=>{
				//폼 전송 막기 
				e.preventDefault();
				//gura_util.js 에 있는 함수를 호출하면서 폼의 참조값 전달
				/*
				ajaxFormPromise(e.target)
				.then(res=>res.json())
				.then(data=>{
					
				});
				*/
				//만일 gura_util 을 사용하지 않는다면
				
				//서버에 전송할 data 를 구성한다.
				let data=new FormData(e.target);
				// fetch() 함수가 리턴하는 Promise 객체를 
				fetch("${pageContext.request.contextPath }/image/upload",{
					method:"post",
					body:data
				})
				.then(res=>res.json())
				.then(data=>{
					console.log(data);
					//data 는 {imagePath:"/resources/upload/xxx"} 형식의 object 이다.
					const imgString=`<img src="${pageContext.request.contextPath }\${data.imagePath}">`;
					// img 요소를 표현하고 있는 문자열을 HTML 형식으로 해석이 되도록 대입해준다.
					document.querySelector("#imageWrapper").innerHTML=imgString;
				});
			});
		</script>
	</div>
</body>
</html>









