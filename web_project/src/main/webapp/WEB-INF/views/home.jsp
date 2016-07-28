<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
</head>
<body>
<div id="map" style="width:100%;height:400px;"></div>

<script>
<%-- var loc = <%=request.getAttribute("LOC") %>; --%>
//alert(loc);


var mapOptions = {
	    center: new naver.maps.LatLng(37.495472,126.887536),
	    zoom: 10
	};
var map = new naver.maps.Map('map', mapOptions);


var markerOpt = [[37.495472,126.887536], [37.4964314,126.8890366]];

for(var i = 0 ; i < 2 ; i++){
	
	var markerOptions = {
		    position: new naver.maps.LatLng(markerOpt[i][0], markerOpt[i][1]),
		    map: map,
		    title: 'Click to zoom'
		};
	var marker = new naver.maps.Marker(markerOptions);
}


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
<input type="button" value="Wi-Fi 보이기" onclick="markOnWiFi()">

</body>
</html>
