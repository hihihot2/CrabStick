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
	    center: new naver.maps.LatLng(lat,lang), //서울역 기준
	    zoom: 6
	});
	
	/* for(var i = 0 ; i < cities.length ; i++){
		var marker = new naver.maps.Marker({
			title: cities[i][0],
			position: new naver.maps.LatLng(cities[i][1], cities[i][2]),
			map: null
		});
		markers.push(marker);
	} */
}
//이벤트 리스너
//화면 invalidate() -> 화면 경계상의 마커 재표시
naver.maps.Event.addListener(map, 'idle', function() {
    //updateMarkers(map, markers);
});

//줌 상태 처리
naver.maps.Event.addListener(map, 'zoom_changed', function() {
	zoom = map.getZoom();
	//alert(zoom);
	updateMaps(map, markers, zoom);
});


//지도 상 클릭 이벤트 처리
naver.maps.Event.addListener(map, 'click', function(e) {
	//alert(e.coord);
	var path = polyline.getPath();
	path.push(e.coord);
	
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
})


// 지도 좌표 경계 객체 생성
var bounds = map.getBounds(),
southWest = bounds.getSW(),
northEast = bounds.getNE(),
lngSpan = northEast.lng() - southWest.lng(),
latSpan = northEast.lat() - southWest.lat();


function updateMaps(map, markers, zoom){
	var mapBounds = map.getBounds();
	var marker, position;
	
	for(var i = 0 ; i < markers.length ; i++){
		marker = markers[i];
		position = marker.getPosition();
		
		if(mapBounds.hasLatLng(position)){
			if(zoom >= cities[i][3]){
				showMarker(map, marker);
			}else {
				hideMarker(map, marker);
			}
		}else {
			hideMarker(map, marker);
		}
	}
}

function updateMarkers(map, markers) {

    var mapBounds = map.getBounds();
    var marker, position;

    for (var i = 0; i < markers.length; i++) {

        marker = markers[i]
        position = marker.getPosition();

        if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
        } else {
            hideMarker(map, marker);
        }
    }
}

function showMarker(map, marker) {

    if (marker.setMap()) return;
    marker.setMap(map);
}

function hideMarker(map, marker) {

    if (!marker.setMap()) return;
    marker.setMap(null);
}

/* for(var i = 0 ; i < 2 ; i++){
	
	var markerOptions = {
		    position: new naver.maps.LatLng(markerOpt[i][0], markerOpt[i][1]),
		    map: map,
		    title: 'Click to zoom'
		}; 
	var marker = new naver.maps.Marker(markerOptions);
}*/


/* var markerOptions = {
	    position: new naver.maps.LatLng(37.495472,126.887536),
	    map: map,
	    title: 'Click to zoom'
	}; */



/* var mapElement = map.getElement();

var listener = naver.maps.Event.addDOMListener(mapElement, 'click', function() {
	    map.setZoom(8);
	    map.setCenter(marker.getPosition());

	    naver.maps.Event.removeDOMListener(listener);
	});
	
var markerList = [];

	    naver.maps.Event.addListener(map, 'click', function(e) {
	        var marker = new naver.maps.Marker({
	            position: e.coord,
	            map: map
	        });

	        markerList.push(marker);
	    });

	    naver.maps.Event.addListener(map, 'keydown', function(e) {
	        var keyboardEvent = e.keyboardEvent,
	            keyCode = keyboardEvent.keyCode || keyboardEvent.which;

	        var ESC = 27;

	        if (keyCode === ESC) {
	            keyboardEvent.preventDefault();

	            for (var i=0, ii=markerList.length; i<ii; i++) {
	                markerList[i].setMap(null);
	            }

	            markerList = [];

	            menuLayer.hide();
	        }
	    });

	    naver.maps.Event.addListener(map, 'mousedown', function(e) {
	        menuLayer.hide();
	    });

	    naver.maps.Event.addListener(map, 'rightclick', function(e) {
	        var coordHtml = 'Coord: '+ e.coord +'<br />Point: '+ e.point +'<br />Offset: '+ e.offset;

	        menuLayer.show().css({
	            left: e.offset.x,
	            top: e.offset.y
	        }).html(coordHtml);
	    }); */
	    /* function markOnWiFi(){
	    	sendRequest("${pageContext.request.contextPath}/test", null, markOn, 'POST');
	    }
	    function markOn(){
			if (httpRequest.readyState == 4) {
				if (httpRequest.status == 200) {
					if(httpRequest.responseText == 0) {
						document.getElementById("idcheckspan").innerHTML = "<font color=blue>사용가능한 아이디</font>";
					} else if(httpRequest.responseText == 1) {
						document.getElementById("idcheckspan").innerHTML = "<font color=red>중복된 아이디</font>";
					}
					
				} else {
					alert("실패: " + httpRequest.status);
				}
			}
		} */
</script>
</body>
</html>