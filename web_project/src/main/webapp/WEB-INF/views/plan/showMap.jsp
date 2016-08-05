<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
	<style type="text/css" src=""></style>
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
	
	function init() {
		//넘겨온 선택지 값 판별
		loc_no = <%= request.getAttribute("loc_no")%>
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
		
		//도시별, 유저별로 시작점 포인트 생성
		<c:forEach var="group" items="${VENUES }">
			<c:forEach var="venue" items="${group.items }">
				setPlace('${venue.location.lat }', '${venue.location.lng }');
				setListener("${venue.name}".toLowerCase());
				//setListener("${venue.name}".toLowerCase());
			</c:forEach>
		</c:forEach>
		
		var contentEl = $('<div style="width:300px;position:absolute;background-color:#fff;margin:10px;">'
		        + '<input id="searchData" style="width:250px" type="text" onkeyup="keyEventChk()" placeholder="검색">' 
		        + '<input style="width:50px" type="button" value="검색" onclick=requestSearch()>'
				+ '</div>');
		contentEl.appendTo(map.getElement());

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
		alert(path.length)
		var flag = confirm("일정을 저장하시겠습니까?")
		
		if(!flag){
			return;
		}else{
			for(var i = 0 ; i < path.length ; i++){
				var object = new Object();
				object.ven_name = ven_form.ven_name[i].value;
				object.ven_lati = ven_form.ven_lati[i].value;
				object.ven_long = ven_form.ven_long[i].value;
				object.ven_order = ven_form.ven_order[i].value;
				object.loc_no = ven_form.loc_no[i].value;
				arr.push(object);
			}
			console.log(arr);
			alert(arr);
			
			location.href = "${pageContext.request.contextPath }/placeCont/addPath.do?json="+JSON.stringify(arr);
		}		
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
								<input type="button"  value="일정 초기화" onclick="resetPath()">
							</td>
						</tr>					
					</table>
					<br>
					
				<form name="ven_form" style="position: static;" action="${pageContext.request.contextPath}/plancont/addplan.do">					
					<div id="addvenue">					
					</div>
					<input type="button" value="완료" onclick="pathComplete(this.form)">					
				</form>

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