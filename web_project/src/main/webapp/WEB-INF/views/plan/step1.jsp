<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게맛살</title>
<script type="text/javascript">
var select1;
var select2;
var select3;

	function survey(num,q){
		if(num == 1){
			
		}else if(num == 2){
			
		}else if(num == 3){
			
		}
	}
</script>
</head>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<div id="quest1">
	<h3>여행목적</h3>
	<input type="button" value="구경" onclick="survey(1,'1')">
	<input type="button" value="식도락" onclick="survey(1,'2')">
	<input type="button" value="휴식" onclick="survey(1,'3')">
</div>
</body>
</html>