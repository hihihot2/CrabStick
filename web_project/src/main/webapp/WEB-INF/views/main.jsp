<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function startPlan(){
		location.href="${pageContext.request.contextPath}/viewcont/plan.do";
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<input type="button" value="플래너 시작하기" onclick="startPlan()">
	
	<a href="location.href=${pageContext.request.contextPath }/viewcont/goToCity.do?city=seoul">서울로 가기</a>

</body>
</html>