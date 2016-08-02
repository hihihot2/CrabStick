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
			strokeWeight: 2 //라인 두깨
		});
		
		//도시별, 유저별로 시작점 포인트 생성
		<c:forEach var="group" items="${VENUES }">
			<c:forEach var="venue" items="${group.items }">
				markPlace('${venue.location.lat }', '${venue.location.lng }');
			</c:forEach>
		</c:forEach>
		
		var mapEvent = new naver.maps.Event.addListener(map, 'click', function(e) {
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
		//eventListener.push(mapEvent);
		
		var mapEvent = new naver.maps.Event.addListener(map, 'rightclick', function(e) {
			var tmp = ">";
			for(var i = 0 ; i < myPath.length ; i++){
				tmp += myPath[i]+",";
			}
			alert(tmp);
		})
	}

	//마커 생성
	function markPlace(lat, lng) {
		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(lat, lng),
			map : map
		//position: 마커의 위치를 나타내는 지도 좌표
		//map: 마커를 표시할 map 객체
		//icon: 모양 설정, 문자열로 입력시 사용할 이미지 경로
		//animaion: 지도에 마커 추가시에 시작할 에니메이션 설정
		//title: 마우스 오버시 나타나는 문자열
		//clickable: 기본 true, 마커 클릭 허용 여부
		});
	}

	function addplan() {
		var sw = document.plan_form.sw.value;
		if(sw == '0'){
			document.getElementById("plan").style.display = ""
			sw = '1';
		}else if(sw == '1'){
			document.getElementById("plan").style.display = "none"
			sw = '0';
		}
		
	}
	
	
</script>
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
								<input type="button" value="무료 Wifi" onclick="markOnWifi()">
							</td>
						</tr>
						<tr>
							<td><input type="button" value="+계획추가" onclick="addplan()">
							</td>
						</tr>
					</table>
					<br>
					
					<div id="plan" style="display: none;">
						<form name="plan_form">
							<table style="width: 100%; height: 50%;" border="1">
								<tr>
									<td colspan="2">
										<input type="text" name="plan_name" placeholder="계획이름을 입력하세요."style="width: 100%; height: 25px; font-size: 14px;">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<textarea rows="5" cols="50" placeholder="내용을 입력하세요" name="plan_commt"></textarea>
									</td>
								</tr>
								<tr>
									<td width="60%">
										<input type="text" name="plan_cost" placeholder="예상금액을 입력하세요."style="width: 80%; height: 25px; font-size: 14px;">원
									</td>
									<td width="40%">
										<select style="margin-left: 40px" name="plan_persons">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select>명									
									</td>
								</tr>									
									<tr>
										<td colspan="2">
											<input type="radio" name="plan_style" value="1">맛집
											<input type="radio" name="plan_style" value="2">자연
											<input type="radio" name="plan_style" value="3">쇼핑
											<input type="radio" name="plan_style" value="4">관광											
										</td>
									</tr>
							</table>
							<input type="hidden" name="sw" value="0">
							<input type="button" value="완료" onclick="plan_do()"><input type="button" value="취소" onclick="cancle_do()">
						</form>
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