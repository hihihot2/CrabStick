<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function select_loc(city, num){
		location.href="${pageContext.request.contextPath}/placeCont/getRestaurants.do?city="+city+"&loc_num="+num;
	}
</script>
</head>
<body>
locations<br>
<table>
	<tr><td onclick="select_loc('seoul', 0)">서울</td></tr>
	<tr><td onclick="select_loc('busan', 1)">부산</td></tr>
	<tr><td onclick="select_loc('jeju', 2)">제주도</td></tr>
	<tr><td onclick="select_loc('gwangju', 3)">광주</td></tr>
	<tr><td onclick="select_loc('daegu', 4)">대구</td></tr>
<!-- 	<tr><td>
	<tr><td onclick="select_loc(1)">부산</td></tr>
	<tr><td onclick="select_loc(2)">제주도</td></tr>
	<tr><td onclick="select_loc(3)">속초</td></tr>
	<tr><td onclick="select_loc(4)">대구</td></tr> -->
</table>
</body>
</html>