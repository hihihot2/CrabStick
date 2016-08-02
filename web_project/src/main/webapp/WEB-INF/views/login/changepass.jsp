<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>
<form name="changepass" action="${pageContext.request.contextPath}/logincont/editpass.do" method="POST">
<input type="text" name="mem_pwd" />
<input type="hidden" name="mem_no" value="${mem_no }">
<input type="submit" value="비밀번호변경">
</form>
</body>
</html>