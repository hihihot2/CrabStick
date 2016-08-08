<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><%-- 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>
<script type="text/javascript">
	function home(){
		location.href="${pageContext.request.contextPath}/";
	}
	function action_do(type){		
		if(type == 'login'){
			document.loginpage.action="${pageContext.request.contextPath}/logincont/loginpage.do";
			document.loginpage.submit();
		}else if(type=='logout'){
			/* 알림 안띄우고 로그아웃
			var flag = confirm("로그아웃하시겠습니까?");
			if(!flag){
				return
			}else{			
				document.loginpage.action="${pageContext.request.contextPath}/logincont/logout.do";
				document.loginpage.submit();
			} */
			document.loginpage.action="${pageContext.request.contextPath}/logincont/logout.do";
			document.loginpage.submit();
		}	
	}
	function mypage_do(){
		document.loginpage.action="${pageContext.request.contextPath}/logincont/mypage.do";
		document.loginpage.submit();
	}

	function join_go() {
		document.loginpage.action = "${pageContext.request.contextPath}/logincont/joinpage.do";
		document.loginpage.submit();
	}

</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Note: Try to remove the following lines to see the effect of CSS positioning */
.affix {
	top: 0;
	width: 100%;
}
</style>

<title>Insert title here</title>

</head>
<body>
	<form name="loginpage">
		
		<nav class="navbar navbar-default" data-spy="affix"
			data-offset-top="30" style="background-color: #FFFFFF" >
			<ul class="nav navbar-nav">
				<li><a href="javascript:home()">LOGO, Our Web site Name</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="padding-right: 30px">
				<c:choose>
					<c:when test="${no ne null}">
						<li><a href="javascript:action_do('logout')" id="logout"><span class="glyphicon glyphicon-log-out" style="padding-right: 5px"></span>Log Out</a></li>
						<li><a href="javascript:mypage_do()"><span class="glyphicon glyphicon-user" style="padding-right: 5px"></span>My page<span class="badge" style="padding-left: 5px">5</span></a></li>
					</c:when>
					<c:otherwise> 
						<li><a href="javascript:action_do('login')"><span class="glyphicon glyphicon-log-in" style="padding-right: 5px"></span>Log In</a></li>
						<li><a href="javascript:join_go()"><span class="glyphicon glyphicon-globe" style="padding-right: 5px"></span>Join Us</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</form>
</body>
</html>