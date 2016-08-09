<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" type="text/css" />

</head>
<body>
<c:forEach items="${plan}" var="plan">
	${plan.plan_no}<br>
	계획이름 : ${plan.plan_name}<br>
	계획내용 : ${plan.plan_commt}<br>
	<c:forEach items="${plan. pathlist}" var="path">
		일정 이름 : ${path.path_name}<br>
		작성일자 : ${path.path_date}<br>	
	</c:forEach>				
</c:forEach>
</body>
</html>