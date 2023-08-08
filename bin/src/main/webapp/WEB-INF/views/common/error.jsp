<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	에러페이지 설정 
	- page지시어 isErrorPage="true"
	- 발생한 예외객체에 exception변수명으로 선언없이 접근 가능
--%>
<c:set var="exception" value="<%= exception %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<style>
body {text-align: center;}
h1 {font-size: 250px; margin: 0;}
#message {color: red;}
</style>
</head>
<body>
	<h1>ㅠ</h1>
	<p>이용에 불편을 드려 죄송합니다.</p>
	<p id="message">${exception.message}</p>
	<p><a href="<%= request.getContextPath() %>/">홈으로</a></p> 
</body>
</html>


