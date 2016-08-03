<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function select_loc(latitude, longitude) {
		location.href = "${pageContext.request.contextPath}/placeCont/getRestaurants.do?city="
				+ city + "&loc_num=" + num;
	}
</script>
</head>
<body>
	locations
	<br>


	<table>

		<c:forEach var="List" items="${city_List}">
			<tr>
				<td onclick="select_loc()">${List.loc_name}</td>
			</tr>
		</c:forEach>


	</table>
</body>
</html>