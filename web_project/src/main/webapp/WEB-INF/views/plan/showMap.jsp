<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: 계획 만들기 ::</title>
<style type="text/css" src=""></style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/mapFunction.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	//변수 등록
	var count = 1;
	var map; //지도 저장 객체
	var myPath = []; //선택한 경로 저장 배열
	var markers = [];//생성된 마커를 담을 배열
	var infowindows = [];//생성된 윈도우를 담을 배열
	var zoom; //zoom 상태 판별
	var polyline; //라인 변수
	var eventListener = [];
	var pathObj = [];
	var loc_no;
	
	var HOME_PATH = window.HOME_PATH || '.',
    urlPrefix = HOME_PATH +'/',
    urlSuffix = '.json',
    regionGeoJson = [],
    loadCount = 0;
	
	

	function checkcategory_rest(){ //레스토랑
		if(category[1].checked){
			<c:forEach var="group" items="${VENUES }">
			<c:forEach var="venue" items="${group.items }">
				setPlace('${venue.location.lat }', '${venue.location.lng }');
				setListener("${venue.name}".toLowerCase());
			</c:forEach>
		</c:forEach>	
		}else if(!category[1].checked){
			alert('맛집 체크해제')
		}
	}
	
	
	//도시별, 유저별로 시작점 포인트 생성
	function checkcategory_hotel(){ // 호텔
		category = document.getElementsByName("categorychk");		
		
		if(category[0].checked){
		 	<c:forEach var="hotel" items="${HOTELS }">
				setPlace('${hotel.latitude }', '${hotel.longitude }');
				setListener("${hotel.name}".toLowerCase());
			</c:forEach>
		} else if(!category[0].checked){		
			alert('호텔 체크해제')
		} 
	}
	function init() {
		//넘겨온 선택지 값 판별
		loc_no =  <%= request.getAttribute("loc_no")%>
		var lat = <%= request.getAttribute("lat") %>
		var lng = <%= request.getAttribute("lang")%>
		
		// 지도 생성 
		map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(lat, lng),
			zoom : 7
		});
		
		//라인 생성
		polyline = new naver.maps.Polyline({
			map:map, //라인을 표시할 지도 객체
			path: [], //라인 좌표를 저장할 배열
			strokeColor: '#FF9B00', //라인컬러
			strokeWeight: 3 //라인 두깨
		});



		var contentEl = $('<div style="width:300px;position:absolute;background-color:#fff;margin:10px;">'
				+ '<input id="searchData" style="width:250px" type="text" onkeyup="keyEventChk()" placeholder="검색">'
				+ '<input style="width:50px" type="button" value="검색" onclick=requestSearch()>'
				+ '</div>');
		contentEl.appendTo(map.getElement());

		var contentEl2 = $('<div style="width:65px;height:100px;position:absolute;top:50px;left:0;background-color:#fff;margin:10px;text-align:center;">'
				+ '<input type="checkbox" name="categorychk" onclick="checkcategory_hotel()"> 호텔<br>'
				+ '<input type="checkbox" name="categorychk" onclick="checkcategory_rest()"> 맛집<br>' + '</div>');
		contentEl2.appendTo(map.getElement());

		//sendRequest(urlPrefix + "1" + urlSuffix, null, getGeo, 'POST')

		//Map Click이벤트 처리 ->marker 생성 window 생성
		/* naver.maps.Event.addListener(map, 'click', function(e) {
			var path = polyline.getPath();
			path.push(e.coord);
			myPath.push(e.coord.lat(), e.coord.lng());			
			var marker = new naver.maps.Marker({
					position: e.coord,
					map: map
			});		
			
			//마커 클릭시 이벤트 처리
			naver.maps.Event.addListener(marker, 'click', function(e) {
				var infowindow = new naver.maps.InfoWindow({
					content: "마커 클릭 -> 윈도우 생성"
				});
				infowindow.open(map, marker);
			});
		
			var tmp = document.getElementById("plan");
		}); */

		//화면 최적화 이벤트 -> 화면 경계상의 마커만 표시
		naver.maps.Event.addListener(map, 'idle', function(e) {
			updateMarkers(map, markers);
		});

		naver.maps.Event.addListener(map, 'rightclick', function(e) {
			alert(pathObj.toString());
		})
	}

	function pathComplete(form) {
		var arr = new Array();
		var path = polyline.getPath();
		var flag = confirm("일정을 저장하시겠습니까?")
		if (!flag) {
			return;
		} else {
			for (var i = 0; i < path.length; i++) {
				var object = new Object();
				object.ven_name = form.ven_name[i].value;
				object.ven_lati = form.ven_lati[i].value;

				object.ven_long = form.ven_long[i].value;
				object.loc_no = form.loc_no[i].value;
				arr.push(object);
			}
			console.log(arr);
			alert(arr);
			location.href = "${pageContext.request.contextPath }/planCont/addPath.do?json="
					+ JSON.stringify(arr);
		}
	}
</script>

<!----------- 동희 작업구역 ------------>
<script type="text/javascript">
	(function($) {
		$(document).ready(function() {
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
			
			$('#venueComment').change(function() {
				// TODO: 코멘트 추가
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

<body onload="init()">
	<jsp:include page="../top.jsp"></jsp:include>
	<div class='AppContainer'>
	<!-- 전체 화면 영역 -->
		<div class='SideBar'>
		<!-- 좌측 사이드바 -->
			<div class='planInfo'>
			<!-- 계획 정보 입력 -->
				<form action="">
					<input type='text' id='planName' placeholder='계획1'>
					<input type='text' id='planComment' placeholder='계획 설명'>
					<input type="text" id='planCost' placeholder='여행 비용' >
					<input type="text" id='planPersons' placeholder='여행 인원'>
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
					<input type="button" id='addPath' value="일정 추가" onclick="pathComplete(this.form)">					
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