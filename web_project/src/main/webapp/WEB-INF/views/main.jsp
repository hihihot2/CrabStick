
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

<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js"
	type="text/javascript"></script>
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
			location.href="${pageContext.request.contextPath}/logincont/loginpage.do?sw=1"	
		} else {
			// 로그인 되어있을시
			location.href="${pageContext.request.contextPath}/viewcont/startPlan.do";
		}
	}
	
	function showMyPlans(sessionNo) {
		if(sessionNo == null){
			// 로그인 안 되어있을시 로그인 페이지로 이동
			location.href="${pageContext.request.contextPath}/logincont/loginpage.do?sw=2"
		} else {
			// 로그인 되어있을시
			// TODO: 내 플랜 보기 코드 수행
			location.href="${pageContext.request.contextPath}/viewcont/showMyPlan.do";

		}
	}
	
	function select_loc(latitude, longitude, locno, code, siguncode) {
		location.href = "${pageContext.request.contextPath}/placeCont/showMap.do?city_latitude="
				+ latitude
				+ "&city_longitude="
				+ longitude
				+ "&cityno="
				+ locno + "&city_code=" + code + "&city_siguncode=" + siguncode;
	}
	
</script>
<style type="text/css">



</style>
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
	<div class="container-fluid text-center">
		<h2>한국의 도시를 소개합니다</h2>
		<br>
		<h4>밑의 도시들을 만날 수 있습니다.</h4>
		<div class="row text-center">
			<div class="col-sm-4">
				<div class="thumbnail">
					<a	href="javascript:select_loc('37.566343','126.977934','1','1','0')"><img src="http://i.imgur.com/cwePpLM.jpg" width="400px" height="300px"></a>
						<div class="center">SEOUL</div></a>
					<p>
						<strong><a	href="javascript:select_loc('37.566343','126.977934','1','1','0')">SEOUL</a></strong>
					</p>
					<p></p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<a	href="javascript:select_loc('35.179770','129.074959','2','6','0')"><img src="http://i.imgur.com/eIYw6CJ.jpg" width="400px" height="300px"></a>
						<div class="center">BUSAN</div>
					<p>
						<strong><a	href="javascript:select_loc('35.179770','129.074959','2','6','0')">BUSAN</a></strong>
					</p>
					<p></p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<a	href="javascript:select_loc('33.489026','126.498351','16','39','4')"><img src="http://i.imgur.com/rJe3t02.jpg" width="400px" height="300px"></a>
						<div class="center">JEJU</div>
					<p>
						<strong><a	href="javascript:select_loc('33.489026','126.498351','16','39','4')">JEJU</a></strong>
					</p>
					<p></p>
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


	<!-- Container (호텔 업데이트 정보 요런거?) -->
	<div class="container-fluid bg-grey">
		<div class="text-center">
			<h2>최신 등록된 여행계획 정보</h2>
			<h4>원하는 것을 선택하세요</h4>
		</div>
		<div class="row">
			<c:forEach var="list" items="${recentPlan }">
			<div class="col-sm-4 col-xs-12">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<h1>${list.plan_name }</h1>
					</div>
					<div class="panel-body">
						<p>
							<strong>${list.plan_no }</strong>
						</p>
						<p>
							<strong>${list.plan_commt }</strong>
						</p>
						<p>
							<strong>${list.plan_persons }</strong> 명 동행
						</p>
					</div>
					<div class="panel-footer">
						<h3>${list.plan_cost }</h3> 원
						<h4>${list.plan_writedate }</h4>
						<button class="btn btn-lg">상세보기</button>
					</div>
				</div>
			</div>
			</c:forEach>
			<!-- <div class="col-sm-4 col-xs-12">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<h1>정보 1</h1>
					</div>
					<div class="panel-body">
						<p>
							<strong>20</strong> 년
						</p>
						<p>
							<strong>15</strong> 객실
						</p>
						<p>
							<strong>5</strong> 층
						</p>
						<p>
							<strong>2</strong> 인실
						</p>
						<p>
							<strong>Endless</strong> Amet
						</p>
					</div>
					<div class="panel-footer">
						<h3>$19</h3>
						<h4>per day</h4>
						<button class="btn btn-lg">예약</button>
					</div>
				</div>
			</div>
			<div class="col-sm-4 col-xs-12">
				<div class="panel panel-default text-center">
					<div class="panel-heading">
						<h1>정보 1</h1>
					</div>
					<div class="panel-body">
						<p>
							<strong>20</strong> 년
						</p>
						<p>
							<strong>15</strong> 객실
						</p>
						<p>
							<strong>5</strong> 층
						</p>
						<p>
							<strong>2</strong> 인실
						</p>
						<p>
							<strong>Endless</strong> Amet
						</p>
					</div>
					<div class="panel-footer">
						<h3>$19</h3>
						<h4>per day</h4>
						<button class="btn btn-lg">예약</button>
					</div>
				</div>
			</div> -->
		</div>
	</div>
	<!-- Container (호텔 업데이트 정보 요런거?) -->
	
	
</body>
</html>