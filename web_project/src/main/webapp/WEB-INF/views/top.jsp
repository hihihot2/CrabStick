<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	function home() {
		location.href = "${pageContext.request.contextPath}/";
	}

	function action_do(type) {
		if (type == 'login') {
			location.href = "${pageContext.request.contextPath}/logincont/loginpage.do?sw=0";
		/* 	var sw = 0;
			document.loginpage.action = "${pageContext.request.contextPath}/logincont/loginpage.do?sw=" + sw
			document.loginpage.submit(); */
		} else if (type == 'logout') {
			/* 알림 안띄우고 로그아웃
			var flag = confirm("로그아웃하시겠습니까?");
			if(!flag){
				return
			}else{			
				document.loginpage.action="${pageContext.request.contextPath}/logincont/logout.do";
				document.loginpage.submit();
			} */
			document.loginpage.action = "${pageContext.request.contextPath}/logincont/logout.do";
			document.loginpage.submit();
		}
	}

	function mypage_do() {
		document.loginpage.action = "${pageContext.request.contextPath}/logincont/mypage.do";
		document.loginpage.submit();
	}

	function join_go() {
		document.loginpage.action = "${pageContext.request.contextPath}/logincont/joinpage.do";
		document.loginpage.submit();
	}
</script>


<title>Planner</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="http://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<link href="http://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<!-- jQuery  -->
<script src="../resources/scripts/jquery-3.1.0.js"></script>
<script src="../resources/scripts/jquery-ui.js"></script>
<!-- jQuery  -->


<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
h2 {
	font-size: 24px;
	text-transform: uppercase;
	color: #303030;
	font-weight: 600;
	margin-bottom: 30px;
}

h4 {
	font-size: 19px;
	line-height: 1.375em;
	color: #303030;
	font-weight: 400;
	margin-bottom: 30px;
}

.bg-grey {
	background-color: #f6f6f6;
}

/* 네비게이션 바 css */
.navbar {
	margin-bottom: 0;
	background-color: #07a3f3; /*네비게이션 바 색상*/
	/* 	z-index: 9999; */
	border: 0;
	font-size: 15px !important; /* 글씨 크기 인듯?  */
	line-height: 1.42857143 !important;
	letter-spacing: 4px;
	border-radius: 0;
	font-family: Montserrat, sans-serif;
	font-size: 15px !important;
}

.navbar li a, .navbar .navbar-brand {
	color: #fff !important;
}

.navbar-nav li a:hover, .navbar-nav li.active a {
	color: #07a3f3 !important;
	background-color: #fff !important;
}

.navbar-right {
	font-size: 12px !important;
}

/* 네비게이션 바 css */
.jumbotron {
	background-color: #07a3f3;
	color: #ffffff;
	padding: 100px 25px;
}


.thumbnail {
	padding: 0 0 15px 0;
	border: none;
	border-radius: 0;
}

.thumbnail img {
	width: 100%;
	height: 100%;
	margin-bottom: 10px;
	opacity: 0.8;
}
.center {
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 70px;
    color: white;
    opacity: 0.8;
   	font-weight: bold;
}

.center2 {
    position: absolute;
    top: 30%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 40px;
    color: white;
    opacity: 0.8;
    font-weight: bold;

    
    
}



/* 슬라이드 부분 css */
.carousel-control.right, .carousel-control.left {
	background-image: none;
	color: #f4511e;
}

.carousel-indicators li {
	border-color: #f4511e;
}

.carousel-indicators li.active {
	background-color: #f4511e;
}
/* 슬라이드 부분 css */

/* 판넬 부분  css */
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
<body data-spy="scroll" data-target=".navbar" data-offset="60">
	<form name="loginpage">
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="javascript:home()">LOGO, Our Web
						site Name</a>


				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right" style="padding-right: 5%;">

						<c:choose>
							<c:when test="${no ne null}">
								
								<li><a href="javascript:action_do('logout')" id="logout"><span
										class="glyphicon glyphicon-log-out" style="padding-right: 5px"></span>Log
										Out</a></li>
								<li><a href="javascript:mypage_do()"><span
										class="glyphicon glyphicon-user" style="padding-right: 5px"></span>My
										page</a></li>
										${no} 값
							</c:when>
							
							<c:otherwise>
								<li><a href="javascript:action_do('login')"><span
										class="glyphicon glyphicon-log-in" style="padding-right: 5px"></span>Log
										In</a></li>
								<li><a href="javascript:join_go()"><span
										class="glyphicon glyphicon-globe" style="padding-right: 5px"></span>Join
										Us</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</form>
</body>
</html>