<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	//변수 등록
	var map; //지도 저장 객체
	var myPath = []; //선택한 경로 저장 배열
	var markers = [];//생성된 마커를 담을 배열
	var infowindows = [];//생성된 윈도우를 담을 배열
	var zoom; //zoom 상태 판별
	var polyline; //라인 변수
	var eventListener = [];
	
	var plan = document.getElementById("plan");
	
	function init() {
		//넘겨온 선택지 값 판별
		var loc_num = <%= request.getAttribute("loc_num")%>
		var lat = <%= request.getAttribute("lat") %>
		var lng = <%= request.getAttribute("lang")%>
		
		// 지도 생성 
		map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(lat, lng),
			zoom : 6
		});
		
		//라인 생성
		polyline = new naver.maps.Polyline({
			map:map, //라인을 표시할 지도 객체
			path: [], //라인 좌표를 저장할 배열
			strokeColor: '#FF9B00', //라인컬러
			strokeWeight: 3 //라인 두깨
		});
		
		//도시별, 유저별로 시작점 포인트 생성
		<c:forEach var="group" items="${VENUES }">
			<c:forEach var="venue" items="${group.items }">
				setPlace('${venue.location.lat }', '${venue.location.lng }');
				//windowPlace('${venue.name}','${venue.location}');
				setListener();
			</c:forEach>
		</c:forEach>
		
		//Map Click이벤트 처리 ->marker 생성 window 생성
		naver.maps.Event.addListener(map, 'click', function(e) {
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
		});
		
		naver.maps.Event.addListener(map, 'rightclick', function(e) {
			var tmp = ">";
			for(var i = 0 ; i < myPath.length ; i++){
				tmp += myPath[i]+",";
			}
			alert(infowindows[0]);
		});
		
		naver.maps.Event.addListener(map, 'idle', function(e) {
			updateMarkers(map, markers);
		});
	}

	

	////////
	function addpath() {
		var sw = document.getElementById("pathbtn").value;
		if(sw == '+일정추가'){
			document.getElementById("path").style.display = ""
			document.getElementById("pathbtn").value="취소"
		}else if(sw == '취소'){
			document.getElementById("path").style.display = "none"
			document.getElementById("pathbtn").value="+일정추가"
		}
		
	}
	function addvenue(){
		var newvenue = document.createElement("div");
		var html = "새 장소 "
		newvenue.innerHTML = html;
		var addvenue =  document.getElementById("addvenue")
		addvenue.appendChild(newvenue);
		return newvenue;
	}
	
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/mapFunction.js"></script>
<body onload="init()">
	<jsp:include page="../top.jsp"></jsp:include>

	<div>
		<!-- 전체 화면 영역 -->
		<table style="width: 100%; height: 100%;">
			<tr>
				<td style="width: 20%;" valign="top">
					<table style="width: 100%; height: 10%;">
						<tr>
							<td><input type="hidden" id="showwifichk" value="0">
								<input type="button"  value="무료 Wifi" onclick="markOnWifi()">
							</td>
						</tr>
						<tr>
							<td><input type="button" id="pathbtn" value="+일정추가" onclick="addpath()">
							</td>
						</tr>
					</table>
					<br>
					<div id="path" style="display: none;">
						<form name="plan_form" style="position: static;">
							<input type="text" name="ven_name" placeholder="장소이름을 입력하세요."style="width: 60%; height: 25px; font-size: 14px;">								
							<br>																														
							<input type="text" name="ven_lato" placeholder="위도" style="width: 60%; height: 25px; font-size: 14px;"><br>								
							<input type="text" name="ven_long" placeholder="경도" style="width: 60%; height: 25px; font-size: 14px;">					
							
							<br>
							<input type="button" value="추가" onclick="addvenue()"><input type="button" value="취소" onclick="cancle_do()">
						</form>
					</div>
					<div id="addvenue">
					
					</div>

				</td>
				<td style="width: 80%;">
					<div id="map" style="height: 900px;"></div>
				</td>
			</tr>
		</table>
	</div>
	<script>
		
	</script>
</body>
</html>