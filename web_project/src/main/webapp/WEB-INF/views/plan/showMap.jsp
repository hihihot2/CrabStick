<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: 계획 만들기 ::</title>
<style type="text/css">
	.test {
		border:2px solid red;
        background-color: #808080;
	}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/mapFunction.js"></script>
<script type="text/javascript">
	//변수 등록
	var count = 1;
	var map; //지도 저장 객체
	var myPath = []; //선택한 경로 저장 배열
	var markers = [];//생성된 마커를 담을 배열
	var infowindows = [];//생성된 윈도우를 담을 배열
	var zoom; //zoom 상태 판별
	var polyline; //라인 변수
	var pathObj = [];
	var loc_no;
	
	var HOME_PATH = window.HOME_PATH || '.',
    urlPrefix = HOME_PATH +'/',
    urlSuffix = '.json',
    regionGeoJson = [],
    loadCount = 0;	
	
	$(document).ready(function() {
		//넘겨온 선택지 값 판별
		loc_no =  <%= request.getAttribute("loc_no")%>
		var lat = <%= request.getAttribute("lat") %>
		var lng = <%= request.getAttribute("lang")%>
		city_code = <%= request.getAttribute("city_code")%>;
		siguncode = <%= request.getAttribute("siguncode")%>;
		
		// 지도 생성 
		map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(lat, lng),
			zoom : 7
		});
		
		//라인 생성
		polyline = new naver.maps.Polyline({
			map:map, //라인을 표시할 지도 객체
			path: new Array(), //라인 좌표를 저장할 배열
			strokeColor: '#FF9B00', //라인컬러
			strokeWeight: 4 //라인 두깨
		});
		
		var contentEl = $('<div style="width:300px;position:absolute;background-color:#fff;margin:10px;">'
				+ '<input id="searchData" style="width:250px" type="text" onkeyup="requestSearch()" placeholder="장소를 검색하세요">'
				+ '<input style="width:50px" type="button" value="검색" onclick=requestSearch()>'
				+ '</div>');
		contentEl.appendTo(map.getElement());
		$('#searchData').autocomplete({
			source: searchList,
			select: function(event, ui){
				alert("1");
			},
			focus: null
		});
		$(document).ready(function(){
			
		})

		var contentEl2 = $('<div style="border:2px;width:65px;height:100px;position:absolute;top:50px;left:0;background-color:#fff;margin:10px;text-align:center;">'
				+ '<input type="checkbox" name="categorychk" onclick=checkcategory(0,'+lat+','+lng+')> 호텔<br>'
				+ '<input type="checkbox" name="categorychk" onclick=checkcategory(1,'+lat+','+lng+')> 맛집<br>' 
				+ '<input type="checkbox" name="categorychk" onclick=checkcategory(2,'+lat+','+lng+')> 명소<br>' 
				+ '<input type="checkbox" name="categorychk" onclick=checkcategory(3,'+lat+','+lng+')> 휴식<br>' 
				+ '</div>');
		contentEl2.appendTo(map.getElement());

		//화면 최적화 이벤트 -> 화면 경계상의 마커만 표시
		naver.maps.Event.addListener(map, 'idle', function(e) {
			updateMarkers(map, markers);
		});

		//맵 클릭 이벤트
		naver.maps.Event.addListener(map, 'click', function(e) {
			for(var i = 0 ; i < infowindows.length ; i++){
				if(infowindows[i][1].getMap()){
					infowindows[i][1].close();
				}
			}
		});
		
		//맵 우클릭 이벤트
		naver.maps.Event.addListener(map, 'rightclick', function(e) {
			alert(markers.length);
		})
		
		
	});
</script>

<!----------- 동희 작업구역 ------------>
<script type="text/javascript">
	(function($) {
		$(document).ready(function() {
			var date = new Date();
			var defaultPlanName = date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + "의 나의 여행";
			var defaultPlanComment = "여행 넘나 재밌는 것~";
			
			$('input#planName').attr('placeholder', '여행 이름이에요. 예) ' + defaultPlanName);
			$('input#planComment').attr('placeholder', '여행 설명이에요. 예) ' + defaultPlanComment);
			
			// #planCost, #planPersons에 숫자만 입력받게 하기
			$('#planCost, #planPersons').keydown(function(event) {
				var keyId = event.keyCode;
				if( (keyId >= 48 && keyId <= 57) || (keyId >= 96 && keyId <= 105) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 ) {
					return;
				} else {
					return false;
				}
			}).keyup(function(event) {
				var keyId = event.keyCode;
				if( keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 ) {
					return;
				} else {
					$(event.target).val($(event.target).val().replace(/[^0-9]/g, ""));
				}
			})
			
			// 서버에 경로 저장후 왼쪽 리스트에 하나로 묶어서 저장하기.
			$('input#addPath').click(function() {
				var arr = new Array();
				
				for(var i = 0; i < pathObj.length; i++) {
					var object = new Object();
					object.venueName = decodeURIComponent(pathObj[i].name);
					object.venueComment = pathObj[i].comment;
					object.venueType = pathObj[i].type;
					object.lat = polyline.getPath().getAt(i).lat().toString();
					object.lng = polyline.getPath().getAt(i).lng().toString();
					arr.push(object);
				}
				
				var plan = new Object();
				if($('input#planName').val() == '') {
					plan.planName = defaultPlanName;	
				} else {
					plan.planName = $('input#planName').val();					
				}
				
				if($('input#planComment').val() == '') {
					plan.planComment = defaultPlanComment;
				} else {
					plan.planComment = $('input#planComment').val();					
				}
				
				if($('input#planCost').val() == '') {
					plan.planCost = 0;
				} else {
					plan.planCost = $('input#planCost').val() * 1;
				}
				
				if($('input#planPersons').val() == '') {
					plan.planPersons = 1;	
				} else {
					plan.planPersons = $('input#planPersons').val() * 1;					
				}
				plan.planStyle = $('select#planStyle option:selected').val() * 1;
				plan.path = arr;
				
				console.log(JSON.stringify(plan));
				
				$.ajax({
					url:"${pageContext.request.contextPath }/planCont/addPath.do",
					dataType:'json',
					type:'GET',
					data: {plan: JSON.stringify(plan)},
					success:function(result) {
						alert('전송됨')						
					}
				})
			})
		})
	})(jQuery)
</script>

<style type="text/css">
	.SideBar, .Map {
		float: left;
	}
	
	.SideBar {
		width: 20%;
		padding: 10px; 
	}
	
	.Map {
		width: 80%;
		padding: 10px;
	}
	
	#planName, #planComment, #planCost, #planPersons, #planStyle {
		width: 100%;
		height: 30px;
		margin-top: 5px;
	}
	
	.planInfo{
		margin-bottom: 10px;
	}
	
	#addPath, #invalidatePath {
		width: 49%;
	}
	
	#inputDiv {
		float: left;
		width: 80%;
		
	}
	
	#cancelDiv {
		float: left;
		width: 20%;
		height: 100%;
	}
	
	#cancelImg {
		margin: 30%;
		width: 40%;
	}
	
	#venueName, #venueComment {
		width: 100%;
	}
</style>
<!---------------------------------->

<body>
	<jsp:include page="../top.jsp"></jsp:include>
	<div class='AppContainer'>
	<!-- 전체 화면 영역 -->
		<div class='SideBar'>
		<!-- 좌측 사이드바 -->
			<div class='planInfo'>
			<!-- 계획 정보 입력 -->
				<form action="">
					<input type='text' id='planName'>
					<input type='text' id='planComment'>
					<input type="text" id='planCost' placeholder='여행 비용' >
					<input type="text" id='planPersons' placeholder='여행 인원 (기본 값: 1)'>
					<select id='planStyle'>
						<option label='문화 탐방' value='1'>
						<option label='식도락' value='2'>
						<option label='쇼핑' value='3'>
						<option label='휴식' value='4'>
					</select>
				</form>
			</div>
			
			<div class='pathList'>
			<!-- 경로 정보 입력 -->
				<form name="venueForm" action="${pageContext.request.contextPath}/plancont/addplan.do">					
					<div id='venueList'></div>
					<input type="button" id='addPath' value="일정 추가"">					
					<input type="button" id='invalidatePath' value="일정 초기화" onclick="resetPath()">
				</form>
			</div>
		</div>		
		
		<div class='Map'>
		<!-- 네이버 지도 -->
			<div id="map" style="height: 900px;"></div>
		</div>
	</div>
	
</body>
</html>