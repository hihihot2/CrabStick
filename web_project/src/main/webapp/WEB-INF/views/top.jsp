<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ID ne null}"><input type="button" value="logout" onclick="action_do('logout')"></c:when>
		<c:otherwise><input type="button" value="login" onclick="action_do('login')"></c:otherwise>
	</c:choose>
	<hr>
</body>
</html>