<jsp:include page="../top.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="http://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<link href="http://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- jQuery  -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!-- jQuery  -->


<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

.panel {
	border: 1px solid #07a3f3;
	border-radius: 0 !important;
	transition: box-shadow 0.5s;
}

.panel:hover {
	box-shadow: 5px 0px 40px rgba(0, 0, 0, .2);
}

.panel-footer .btn:hover {
	border: 1px solid #f4511e;
	background-color: #fff !important;
	color: #f4511e;
}

.panel-heading {
	color: #fff !important;
	background-color: #07a3f3 !important;
	padding: 25px;
	margin-top: 80px;
	border-bottom: 1px solid transparent;
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 0px;
	border-bottom-right-radius: 0px;
}

.panel-footer {
	background-color: white !important;
}

.panel-footer h3 {
	font-size: 32px;
}

.panel-footer h4 {
	color: #aaa;
	font-size: 14px;
}

.panel-footer .btn {
	margin: 15px 0;
	background-color: #07a3f3;
	color: #fff;
}
/* 판넬 부분  css */
</style>


</head>
<body>
<c:choose>
<c:when test="${plan eq null}">
	<div class="col-sm-3 col-xs-10">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<h1>일정을 추가해주세요</h1>
					</div>
					<div class="panel-body">						
							<div class="panel-footer">
							</div>
						 		<button class="btn btn-lg">일정 추가하기</button>								
						</div>
				</div>				
			</div>		
</c:when>
<c:otherwise>
	<c:forEach items="${plan}" var="plan">
			<div class="col-sm-3 col-xs-10">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<h1>${plan.plan_name}</h1>
					</div>
					<div class="panel-body">
						<p>
							<strong>${plan.plan_commt}</strong>
						</p>
						<p>
							<strong>${plan.plan_cost}</strong>원
						</p>
						<p>
							<strong>${plan.plan_persons}</strong>명
						</p>
							<c:forEach items="${plan.pathlist}" var="path">
						<p>
							<strong>${path.path_name}</strong> 
						</p>
						<p>
							<strong>${path.path_date}</strong>
						</p><br>
								<c:forEach items="${path.venuelist}" var="venue">
									<div class="panel-footer">
										<h4>${venue.ven_order}번째 장소</h4><h5> ${venue.ven_name}</h5>
									</div>
								</c:forEach>
									<a href="${pageContext.request.contextPath}/viewcont/viewMyPlanMap.do"><button class="btn btn-lg" >일정 보러가기</button></a>								
							</c:forEach>
						</div>
					</div>				
			</div>		
	</c:forEach>
</c:otherwise>	
</c:choose>
</body>
</html>