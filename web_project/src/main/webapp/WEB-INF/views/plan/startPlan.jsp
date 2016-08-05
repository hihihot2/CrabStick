<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function select_loc(latitude, longitude, locno) {
		alert("위도"+latitude +"경도"+longitude);
		
		
		location.href = "${pageContext.request.contextPath}/placeCont/getRestaurants.do?city_latitude="
				+latitude+"&city_longitude="+longitude+"&cityno="+locno;
	}
</script>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>
	<h3>회원이 좋아하실만한 여행을 5가지 뽑아봤어요~</h3>
	<br>
	<c:forEach var="List" items="${city_List}">
		<div style="border: 0px; float: left; width: 250px; padding: 20px;">
			<table border="1">
				<tr>
					<td colspan="2" height="160px" width="250px">//이곳엔 사진이 있었으면
						좋겠당</td>
				</tr>
				<tr>
					<td colspan="2"
						onclick="select_loc('${List.loc_lati}','${List.loc_long}','${List.loc_no}')">${List.loc_name}</td>
				</tr>
				<tr>
					<td>${List.loc_no}</td>
					<td>2016년 9월</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</body>
</html>