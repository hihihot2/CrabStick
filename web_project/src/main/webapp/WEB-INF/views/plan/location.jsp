<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function select_loc(num){
		location.href="${pageContext.request.contextPath}/plancont/sel_loc.do?loc_num="+num;
	}
</script>
</head>
<body>
locations<br>
<table>
	<tr><td onclick="select_loc(0)">서울</td></tr>
	<tr><td onclick="select_loc(1)">부산</td></tr>
	<tr><td onclick="select_loc(2)">제주도</td></tr>
	<tr><td onclick="select_loc(3)">속초</td></tr>
	<tr><td onclick="select_loc(4)">대구</td></tr>
</table>
</body>
</html>