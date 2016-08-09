<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>
<script type="text/javascript">
$(function($){
	var imgpath = $("#loc_imgpath")
})


	function delwindow(){
		window.close()
	}

</script>
</head>
<body>
<c:forEach var="List" items="${detailList}">
	<img src="${pageContext.request.contextPath}/resources/png/city/seoul.png" height="50%"width="100%" id="showimg"><br>
	${List.loc_no }<br>
	${List.loc_name }<br>
</c:forEach>
<input type ="button" value="닫기" onclick="delwindow()"/>
</body>
</html>