<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
var worker;

	function markOnWifi(){
		var chk = document.getElementById("showwifichk").value;
		if(chk == 0){
			/* if(worker) worker.terminate();
			worker = new Worker("${pageContext.request.contextPath}/resources/scripts/worker.js");
			worker.postMessage("${pageContext.request.contextPath}/viewcont/markwifi.do");
			
			worker.onmessage = function(event){
				alert(event.data);
			} */
			sendRequest("${pageContext.request.contextPath}/viewcont/markwifi.do", null, setMarkOnMaps, 'POST');
			chk = 1;
			//alert(chk);
			
		}else if(chk == 1){
			alert(chk);
			marker.setMap(null);
		}
		
	}
	function setMarkOnMaps(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var result = httpRequest.responseText;
				//alert(result);
				var locInfo = eval("("+ result +")");
				for(var i = 0 ; i < locInfo.length ; i++){	
					var marker = new naver.maps.Marker({
					    position: new naver.maps.LatLng(locInfo[i].lat, locInfo[i].lang),
					    map: null,
					    title: 'Click to zoom'
					});
					markers.push(marker);
				}
			}
		}
	}
</script>
</head>
<body onload="init()">
<jsp:include page="../top.jsp"></jsp:include>

<div><!-- 전체 화면 영역 -->
	<table style="width:100%;height:100%;">
		<tr>
			<td style="width:15%;">
				<table style="width:100%;height:100%;">
					<tr>
						<td>
							<input type="hidden" id="showwifichk" value="0">
							<input type="button" value="무료 Wifi" onclick="markOnWifi()" onkeyup="">
						</td>
					</tr>
				</table>
			</td>
			<td style="width:85%;">
				<div id="map" style="height:900px;"></div>
			</td>
		</tr>
	</table>
</div>
<script>
//변수 등록
var map; //지도 저장 객체
var myPath = []; //선택한 경로 저장 배열
var markers = [];//생성된 마커를 담을 배열
var zoom; //zoom 상태 판별
var cities = [["서울", 37.5666102, 126.9783881, 1],
              ["부산", 35.1798159, 129.0750222, 2]];

//라인 생성
var polyline = new naver.maps.Polyline({
	map:map,
	path: [],
	strokeColor: '#FF9B00',
	strokeWeight: 2
});

//초기화 함수
function init(){
	//넘겨온 선택지 값 판별
	var loc_num = <%= request.getAttribute("loc_num")%>
	var lat = <%= request.getAttribute("lat") %>
	var lang = <%= request.getAttribute("lang")%>
	
	// 지도 생성 
	map = new naver.maps.Map('map', {
	    center: new naver.maps.LatLng(37.5666102,126.9783881), //서울역 기준
	    zoom: 6
	});
	
	for(var i = 0 ; i < cities.length ; i++){
		var marker = new naver.maps.Marker({
			title: cities[i][0],
			position: new naver.maps.LatLng(cities[i][1], cities[i][2]),
			map: map
		});
		markers.push(marker);
	}
}
</script>
<c:forEach var="group" items="${VENUES}">
	<c:forEach var="venue" items="${group.items}">
		<script>var marker = new naver.maps.Marker({
			position: new naver.maps.LatLng('${venue.location.lat}', '${venue.location.lng}'),
			map: map
		});</script>
	</c:forEach>
</c:forEach>
<script type="text/javascript">var marker = new naver.maps.Marker({
	position: new naver.maps.LatLng(37.5666102,126.555),
	map: map
});
</script>
</body>
</html>