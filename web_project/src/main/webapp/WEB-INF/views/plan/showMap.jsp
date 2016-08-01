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
	var map;
	function init(){
		// 지도 생성 
		map = new naver.maps.Map('map', {
		    center: new naver.maps.LatLng(37.5666102,126.9783881), //서울역 기준
		    zoom: 6
		});
		
		<c:forEach var="group" items="${VENUES }">
			<c:forEach var="venue" items="${group.items }">
				markPlace('${venue.location.lat }', '${venue.location.lng }');
			</c:forEach>
		</c:forEach>
	}
	
	function markPlace(lat, lng) {
		var marker = new naver.maps.Marker({
			position: new naver.maps.LatLng(lat, lng),
			map: map
			//position: 마커의 위치를 나타내는 지도 좌표
			//map: 마커를 표시할 map 객체
			//icon: 모양 설정, 문자열로 입력시 사용할 이미지 경로
			//animaion: 지도에 마커 추가시에 시작할 에니메이션 설정
			//title: 마우스 오버시 나타나는 문자열
			//clickable: 기본 true, 마커 클릭 허용 여부
		});
	}
</script>
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

</body>
</html>