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
<body>
<jsp:include page="../top.jsp"></jsp:include>

<div><!-- 전체 화면 영역 -->
	<table style="width:100%;height:100%;">
		<tr>
			<td style="width:15%;">
				<table style="width:100%;height:100%;">
					<tr>
						<td>
							<input type="hidden" id="showwifichk" value="0">
							<input type="button" value="무료 Wifi" onclick="markOnWifi()">
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
<%-- var loc = <%=request.getAttribute("LOC") %>; --%>
//alert(loc);
var mapOptions = {
	    center: new naver.maps.LatLng(37.5545373,126.9706856), //서울역 기준
	    zoom: 8
	};
var map = new naver.maps.Map('map', mapOptions);

naver.maps.Event.addListener(map, 'idle', function() {
    updateMarkers(map, markers);
});

var bounds = map.getBounds(),
southWest = bounds.getSW(),
northEast = bounds.getNE(),
lngSpan = northEast.lng() - southWest.lng(),
latSpan = northEast.lat() - southWest.lat();

var markers = [];

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
	    function markOnWiFi(){
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
		}
</script>
</body>
</html>