
<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

<!-- Main.jsp 부분 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Planner</title>


<script src="http://code.jquery.com/jquery-2.0.3.min.js" ></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>

<script type="text/javascript">
 $(function($){
     $("#logout").on("click", function(){
         $.cookie('autoPlug', 'true', { expires: -1, path: '/myapp/', secure: false })
         $.removeCookie('autoNo', { expires: -1, path: '/myapp/', secure: false })
     }) 
 })

	function startPlan(sessionNo){	
		if(sessionNo == null){
			// 로그인 안 되어있을시 로그인 페이지로 이동
			action_do('login')	
		} else {
			// 로그인 되어있을시
			location.href="${pageContext.request.contextPath}/viewcont/startPlan.do";
		}
	}
	
	function showMyPlans(sessionNo) {
		if(sessionNo == null){
			// 로그인 안 되어있을시 로그인 페이지로 이동
			action_do('login')
		} else {
			// 로그인 되어있을시
			// TODO: 내 플랜 보기 코드 수행
		}
	}
	



</script>
</head>
<body>
	<!-- 네비게이션 밑 부분 -->
	<div class="jumbotron text-center">
		<h1>여행을 떠나보세요</h1>
		<p>Find the city you love. Discover new place.</p>
		<form class="form-inline">
			<button type="button" class="btn btn-success"
				onclick="startPlan(${no})">플래너 시작하기 >></button>
			<button type="button" class="btn btn-warning"
				onclick="showMyPlans(${no})">내가 세운 계획 보기 >></button>
		</form>
	</div>
	<!-- 네비게이션 밑 부분 -->


	<!-- Container (BEST 도시 or 플래너 Section) -->
	<div id="Menu_1" class="container-fluid text-center">
		<h2>한국의 도시를 소개합니다</h2>
		<br>
		<h4>밑의 도시들을 만날 수 있습니다.</h4>
		<div class="row text-center slideanim">
			<div class="col-sm-4">
				<div class="thumbnail">
					<img
						src="http://slidesjs.com/examples/standard/img/example-slide-1.jpg"
						width="400" height="300">
					<p>
						<strong>도시 1</strong>
					</p>
					<p>도시 1의 사진</p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img
						src="http://slidesjs.com/examples/standard/img/example-slide-2.jpg"
						width="400" height="300">
					<p>
						<strong>도시 2</strong>
					</p>
					<p>도시 2의 사진</p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img
						src="http://slidesjs.com/examples/standard/img/example-slide-3.jpg"
						width="400" height="300">
					<p>
						<strong>도시 3</strong>
					</p>
					<p>도시 3의 사진</p>
				</div>
			</div>
		</div>
		<br>

		<h2>최근 여행 수기입니다</h2>
		<div id="myCarousel" class="carousel slide text-center"
			data-ride="carousel">
			<!-- Indicators -->

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<h4>
						"씨발!!! 서울 존나 좋앙!!"<br> <span style="font-style: normal;">최재훈
							님의 평가</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"청주에서 올라왔습니다. 너무 좋아요!!"<br> <span style="font-style: normal;">정봉기
							님의 평가</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"제주도 가고싶어요"<br> <span style="font-style: normal;">윤정오
							님의 평가</span>
					</h4>
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<!-- Container (BEST 도시 or 플래너 Section) -->





</body>
</html>