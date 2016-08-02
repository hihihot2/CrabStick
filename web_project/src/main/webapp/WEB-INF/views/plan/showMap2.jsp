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
	var map;
	function init() {
		// 지도 생성 
		map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.5666102, 126.9783881), //서울역 기준
			zoom : 6
		});

		<c:forEach var="group" items="${VENUES }">
		<c:forEach var="venue" items="${group.items }">
		markPlace('${venue.location.lat }', '${venue.location.lng }');
		</c:forEach>
		</c:forEach>
	}

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

	function addvenue() {
		var sw = document.plan_form.sw.value;
		if(sw == '0'){
			document.getElementById("plan").style.display = ""
			document.getElementById("planbtn").value="취소"
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
								<input type="button"  value="무료 Wifi" onclick="markOnWifi()">
							</td>
						</tr>
						<tr>
							<td><input type="button" id="planbtn" name="plan" value="+일정추가" onclick="addvenue()">
							</td>
						</tr>
					</table>
					<br>
					<div id="plan" style="display: none;">
						<form name="plan_form" style="position: static;">
							<input type="text" name="plan_name" placeholder="계획이름을 입력하세요."style="width: 60%; height: 25px; font-size: 14px;">								
							<textarea rows="5" cols="30" placeholder="내용을 입력하세요" name="plan_commt"></textarea>								
							<br>
							<input type="text" name="plan_cost" placeholder="예상금액을 입력하세요."style="width: 40%; height: 25px; font-size: 14px;">원
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
							<br>																														
								<input type="radio" name="plan_style" value="1">맛집
								<input type="radio" name="plan_style" value="2">자연
								<input type="radio" name="plan_style" value="3">쇼핑
								<input type="radio" name="plan_style" value="4">관광																
							<input type="hidden" name="sw" value="0">
							<br>
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

</body>
</html>