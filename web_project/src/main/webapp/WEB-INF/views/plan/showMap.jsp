<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="../top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:: 계획 만들기 ::</title>
<style type="text/css">
.loader {
  position: fixed;
  top: 50%;
  left: 50%;
  width: 160px;
  height: 160px;
  margin: -80px 0px 0px -80px;
  background-color: transparent;
  border-radius: 50%;
  border: 2px solid #E01D3C;
}
.loader:before {
  content: '';
  width: 164px;
  height: 164px;
  display: block;
  position: absolute;
  border: 2px solid #EBEDE6;
  border-radius: 50%;
  top: -2px;
  left: -2px;
  box-sizing: border-box;
  clip: rect(0px, 35px, 35px, 0px);
  z-index: 10;
  animation: rotate infinite;
  animation-duration: 3s;
  animation-timing-function: linear;
}
.loader:after {
  content: '';
  width: 164px;
  height: 164px;
  display: block;
  position: absolute;
  border: 2px solid #E8E4E1;
  border-radius: 50%;
  top: -2px;
  left: -2px;
  box-sizing: border-box;
  clip: rect(0px, 164px, 150px, 0px);
  z-index: 9;
  animation: rotate2 3s linear infinite;
}

.hexagon-container {
  position: relative;
  top: 33px;
  left: 41px;
  border-radius: 50%;
}

.hexagon {
  position: absolute;
  width: 40px;
  height: 23px;
  background-color: #FFAB00;
}
.hexagon:before {
  content: "";
  position: absolute;
  top: -11px;
  left: 0;
  width: 0;
  height: 0;
  border-left: 20px solid transparent;
  border-right: 20px solid transparent;
  border-bottom: 11.5px solid #FFAB00;
}
.hexagon:after {
  content: "";
  position: absolute;
  top: 23px;
  left: 0;
  width: 0;
  height: 0;
  border-left: 20px solid transparent;
  border-right: 20px solid transparent;
  border-top: 11.5px solid #FFAB00;
}

.hexagon.hex_1 {
  top: 0px;
  left: 0px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 0.21429s;
}

.hexagon.hex_2 {
  top: 0px;
  left: 42px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 0.42857s;
}

.hexagon.hex_3 {
  top: 36px;
  left: 63px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 0.64286s;
}

.hexagon.hex_4 {
  top: 72px;
  left: 42px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 0.85714s;
}

.hexagon.hex_5 {
  top: 72px;
  left: 0px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 1.07143s;
}

.hexagon.hex_6 {
  top: 36px;
  left: -21px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 1.28571s;
}

.hexagon.hex_7 {
  top: 36px;
  left: 21px;
  animation: Animasearch 3s ease-in-out infinite;
  animation-delay: 1.5s;
}

@keyframes Animasearch {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  15%, 50% {
    transform: scale(0.5);
    opacity: 0;
  }
  65% {
    transform: scale(1);
    opacity: 1;
  }
}
@keyframes rotate {
  0% {
    transform: rotate(0);
    clip: rect(0px, 35px, 35px, 0px);
  }
  50% {
    clip: rect(0px, 40px, 40px, 0px);
  }
  100% {
    transform: rotate(360deg);
    clip: rect(0px, 35px, 35px, 0px);
  }
}
@keyframes rotate2 {
  0% {
    transform: rotate(0deg);
    clip: rect(0px, 164px, 150px, 0px);
  }
  50% {
    clip: rect(0px, 164px, 0px, 0px);
    transform: rotate(360deg);
  }
  100% {
    transform: rotate(720deg);
    clip: rect(0px, 164px, 150px, 0px);
  }
}
@keyframes rotate3 {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

ol, ul {
	list-style: none
}
</style>

<style type="text/css">
.test {
	border: 2px solid red;
	background-color: #808080;
}
</style>
<!-- <link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/mapFunction.js"></script>
<script type="text/javascript">
	//변수 등록
	var count = 1;
	var map; //지도 저장 객체
	var lat, lng; //지도 좌표 변수
	var myPath = []; //선택한 경로 저장 배열
	var allMarkers = new Array();//생성된 마커를 담을 배열
	var myMarkers = new Array();
	var infowindows = [];//생성된 윈도우를 담을 배열
	var userSearch;
	var overlay;
	var olflag;
	var menuLayer;
	var markerLayer;
	var polyline = new Array(); //라인 변수
	var pathObj = [];
	var loc_no;
	var isAddCondition = false;
	var isModifyCondition = false;
	var isFirstAdd = true;
	var pathColors = ["#4A89DC", "#E9573F", "#3BAFDA", "#967ADC", "#434A54", "#37BC9B", "#DA4453", "#D770AD"];
	var pathCount = 0;
	
	var HOME_PATH = window.HOME_PATH || '.',
    urlPrefix = HOME_PATH +'/',
    urlSuffix = '.json',
    regionGeoJson = [],
    loadCount = 0;	
	
	$(document).ready(function() {
		//넘겨온 선택지 값 판별
		loc_no =  <%= request.getAttribute("loc_no")%>
		lat = <%= request.getAttribute("lat") %>
		lng = <%= request.getAttribute("lang")%>
		
		// 지도 생성 
		map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(lat, lng),
			zoom : 7
		});
		//검색창 생성 및 기능 설정
		var contentEl = $('<div style="width:250px;position:absolute;background-color:#fff;margin:10px;">'
				+ '<input id="searchData" style="width:250px" type="text" onkeyup="keyEventChk()" placeholder="장소를 검색하세요">'
				+ '</div>');
		contentEl.appendTo(map.getElement());
		$('#searchData').autocomplete({
			source: function(request, response){
				var params = "data="+encodeURIComponent(request.term);
				//sendRequest("../plancont/searchloc.do", params, setSearchPlace, 'POST');
				$.ajax({
					url: '../plancont/searchloc.do?'+params,
					type: 'POST',
					success: function(data){
						var search = eval("("+ data +")");
						var tmp = [];var tmp2 = new Array();
						for(var i = 0 ; i < search.length ; i++){
							var before = search[i].title;
							tmp.push(regTag(before));
							var sub_tmp2 = new Array();
							sub_tmp2.push(regTag(before));
							sub_tmp2.push(search[i].addr);
							tmp2.push(sub_tmp2);
						}
						searchList = tmp2;
						response(tmp);
					}
				});
			},
			select: function(event, ui){
				if(event.keyCode == 13){
					$('searchData').val(ui.item.value);
				}
			}
		});
		//체크박스 생성 및 기능 설정
		var contentEl2 = $('<div style="border:2px solid;width:70px;height:180px;position:absolute;top:50px;left:0;background-color:#fff;margin:10px;text-align:center;">'
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_hotel.png" /> 호텔<br>'
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_food.png" /> 맛집<br>' 
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_castle.png" /> 명소<br>' 
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_shopping.png" /> 쇼핑<br>' 
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_rest.png" /> 휴식<br>' 
				+ '<img style="width:30px;height:30px;" src="../resources/png/cover_attraction.png" /> 자연<br>' 
				+ '</div>');
		contentEl2.appendTo(map.getElement());
		
		getRecommandPlaces(lat, lng, 1000);
		
		function getRecommandPlaces(latitude, longitude, radius) {
			var loading;
			$.ajax({
				url: '${pageContext.request.contextPath}/placeCont/getRecommandPlaces.do',
				type: 'POST',
				dataType: 'text',
				data: {
					lat: latitude,
					lng: longitude,
					radius: radius
				},
				success: function(result) {
					//console.log(result);
					var venueList = eval('('+result+')');
					for(var i = 0; i < venueList.length; i++) {
						setPlace(venueList[i]);
					}
				},
				beforeSend: function(){
					loading = $('<div class="wrap-loading">'
							+'<div class="loader">'
							+'<ul class="hexagon-container">'
							+'<li class="hexagon hex_1"></li>'
							+'<li class="hexagon hex_2"></li>'
							+'<li class="hexagon hex_3"></li>'
							+'<li class="hexagon hex_4"></li>'
							+'<li class="hexagon hex_5"></li>'
							+'<li class="hexagon hex_6"></li>'
							+'<li class="hexagon hex_7"></li>'
						+'</ul>'
						+'</div></div> ');
					loading.appendTo(map.getElement());
					$('.wrap-loading').removeClass('display-none');
				},
				complete: function(){
					$('.wrap-loading').addClass('display-none');
				}
			})
		}
		
		function setPlace(venue) {
			var markerImgUrl;
			switch(venue.type){
				case 0: markerImgUrl = "../resources/png/hotel.png";break;
				case 1: markerImgUrl = "../resources/png/food.png";break;
				case 2: markerImgUrl = "../resources/png/castle.png";break;
				case 3: markerImgUrl = "../resources/png/shopping.png";break;
				case 4: markerImgUrl = "../resources/png/rest.png";break;
				case 5: markerImgUrl = "../resources/png/attraction.png";break;
				default: markerImgUrl = "../resources/png/search.png";break;
			}
			
			// marker 변수: 지도에 찍히는 '핀'의 위치와 아이콘을 정함
			var marker = new naver.maps.Marker({
				position : new naver.maps.LatLng(venue.lat, venue.lng),
				icon : {
					url: markerImgUrl
				},
				animation: naver.maps.Animation.DROP,
				clickable: true,
				map: map,
				title: venue.name,
				zIndex: 100
			});
			
			// infoWindow 변수: 핀에 대한 정보를 담는 윈도우
			var windowForm = $('div#infoWindowForm').clone().removeClass('hiddenDiv').attr('id', 'infoWindowDiv');
			if(venue.img != '') {
				windowForm.find('img#venueThumbnail').attr('src', venue.img);	// venue.img에서 이미지 주소 가져옴
			}
			windowForm.find('p#venueName').text(venue.name);
			windowForm.find('p#venueAddress').text(venue.address);
			var infoWindow = new naver.maps.InfoWindow({
				content: windowForm[0]
			})
			
			naver.maps.Event.addListener(marker, 'click', function() {
				if(confirm("일정에 추가하시겠습니까?")){
					addPath(venue);
				}
			});
			
			naver.maps.Event.addListener(marker, 'rightclick', function(e) {
				markerLayer.show().css({
		            left: e.offset.x,
		            top: e.offset.y
		        }).html('<input id="ovl2" style="width:106px" type="button" value="일정에 추가2" onclick="addPath('+venue+')">');
		        /* olflag = 1;
				showoverlay(marker.getPosition(), 1); */
				$('#ovl2').on('click', function() {
					if(confirm("일정에 추가하시겠습니까?")){
						addPath(venue);
						marker.setAnimation(naver.maps.Animation.BOUNCE);
						marker.setIcon("../resources/png/check.png");
						markerLayer.hide();
						myMarkers.push(marker);
						allMarkers.splice(allMarkers.indexOf(marker), 1);
					}
				});
			});
			
			naver.maps.Event.addListener(marker, 'mouseover', function() {
				infoWindow.open(map, marker);
			});
			
			naver.maps.Event.addListener(marker, 'mouseout', function() {
				infoWindow.close();
			});
			
			allMarkers.push(marker);
		}
		
		function addPath(venue) {
			alert("1");
			if(!isAddCondition && !isModifyCondition) {
				alert('왼쪽에서 일정 만들기를 눌러주세요~');
			} else {
				if(isModifyCondition) {
					console.log("일정 수정하기");
					// TODO: 일정 수정에서 추가 가능하게 코딩
				} else if(isAddCondition) {
					console.log("일정 저장하기");
					console.log(venue);
					var path = polyline[pathCount].getPath();
					path.push(new naver.maps.LatLng(venue.lat, venue.lng));
					pathObj.push(venue);
					updateList();
				}
			}
		}
		
		menuLayer = $('<div style="position:absolute;left:0;top:0;width:110px;background-color:#F2F0EA;text-align:center;border:2px solid #6C483B;">' +
                '</div>');
		map.getPanes().floatPane.appendChild(menuLayer[0]);
		menuLayer.hide();
		
		markerLayer = $('<div style="position:absolute;left:0;top:0;width:110px;background-color:#F2F0EA;text-align:center;border:2px solid #6C483B;">' +
            '</div>');
		map.getPanes().floatPane.appendChild(markerLayer[0]);
		markerLayer.hide();
		
		//지도의 움직임이 종료 되었을때 실행되는 리스너
		naver.maps.Event.addListener(map, 'idle', function(e) {
			markerLayer.hide();
			menuLayer.hide();
			/* if(overlay != null){
				overlay.setMap(null);
			} */
			updateMarkers(map, myPath);
		});
		
		//지도에서 마우스 버튼을 누를때 실행되는 리스너
		/* naver.maps.Event.addListener(map, 'mousedown', function(e) {
			markerLayer.hide();
			menuLayer.hide();
			if(overlay != null){
				overlay.setMap(null);
			} 
		}); */

		//지도의 한 지점을 클릭했을 때 실행되는 리스너
		naver.maps.Event.addListener(map, 'click', function(e) {
			for(var i = 0 ; i < infowindows.length ; i++){
				if(infowindows[i][1].getMap()){
					infowindows[i][1].close();
				}
			}
		});
		
		//맵 우클릭 이벤트
		naver.maps.Event.addListener(map, 'rightclick', function(e) {
	        menuLayer.show().css({
	            left: e.offset.x,
	            top: e.offset.y
	        }).html('<input id="ovl" style="width:106px" type="button" value="일정에 추가">');
	        /* if(olflag != 1){
	        	showoverlay(e.coord, 0);
	        } */
	        $('#ovl').on('click', function() {
	        	if(confirm("해당 위치를 새로운 경로로 설정하시겠습니까?")){
					//addPath(venue);
					markerLayer.hide();
				}
	        })
		});
		
	});
	
</script>



<!----------- 동희 작업구역 ------------>
<script type="text/javascript">
	(function($) {
		$(document).ready(function() {
			/**
			 * 선택한 폼을 수정 폼으로 변환하는 메서드
			 */
			var showEditForm = function() {
				var thisElement = $(this);
				var pathCountOfThisElement = thisElement.parent().find('div#pathDiv').index(this);
				
				if(isAddCondition) {
					// 일정 추가하기가 열려있을 때
					if(confirm('추가중인 일정을 취소할까요?')) {
						// 취소
						$('div#addPathDiv').find('input#cancelPath').click();
					} else {
						return;
					}
					
				} else if (isModifyCondition) {
					// 다른 일정 수정하기가 열려있을 때
					if(confirm('수정중인 일정을 취소할까요?')) {
						// 취소
						$('div.editing').find('input#cancelPathBtn').click();
					} else {
						return;
					}
				}
				
				isModifyCondition = true;
				$.ajax({
					url: "${pageContext.request.contextPath }/planCont/getPathDetails.do",
					dataType: 'text',
					type: 'POST',
					data: {
							'pathNo': $(this).find('input#pathNo').val()
						},
					success: function(result) {
						var path = eval('('+result+')');
						console.log(path)
						var pathEditDiv = $('div#pathEditDivForm').clone().removeClass('hiddenDiv').addClass('editing').attr('id', 'pathEditDiv');
						pathEditDiv.find('input#pathName').val(path.path_name);
						pathEditDiv.find('input#pathNo').val(path.path_no);

						var mapPinList = new Array();
						
						for(var i = 0; i < path.venues.length; i++) {
							var venue = path.venues[i];
							var venueDiv = $('div#venueDivForm').clone().appendTo(pathEditDiv.find('div#venueListInSavedPath')).removeClass('hiddenDiv').attr('id', 'venueDiv');
							venueDiv.find('input#venueName').val(venue.ven_name);
							venueDiv.find('input#venueComment').val(venue.ven_commt);
							venueDiv.find('input#venueNo').val(venue.ven_no);
							venueDiv.find('input#venueLatitude').val(venue.ven_lati);
							venueDiv.find('input#venueLongitude').val(venue.ven_long);
							venueDiv.find('input#venueOrder').val(venue.ven_order);
							
							var pin = new Object();
							pin.name = venue.ven_name;
							pin.comment = venue.ven_commt;
							pin.lat = venue.ven_lati;
							pin.lng = venue.ven_long;
							mapPinList.push(pin);
																			
							venueDiv.find('img#cancelImg').click(function() {
								// x 버튼 누를때 하는 일
								var pathLineOnMap = polyline[pathCountOfThisElement].getPath();
								pathLineOnMap.splice($(this).parent().parent().parent().find('img#cancelImg').index(this), 1);
								$(this).parent().parent().remove();
							})
						}
						thisElement.addClass('hiddenDiv').after(pathEditDiv);
						
						pathEditDiv.find('input#modifyPathBtn').click(function() {
							// TODO: 수정 버튼 누를 때 할 일 정의
							var path = new Object();
							path.no = pathEditDiv.find('input#pathNo').val() * 1;
							path.name = pathEditDiv.find('input#pathName').val();
							path.venues = new Array();
							
							var venueDiv = pathEditDiv.find('div#venueDiv').first();
							do {
								var venue = new Object();
								venue.no = venueDiv.find('input#venueNo').val() * 1;
								venue.name = venueDiv.find('input#venueName').val();
								venue.comment = venueDiv.find('input#venueComment').val();
								venue.lat = venueDiv.find('input#venueLatitude').val();
								venue.lng = venueDiv.find('input#venueLongitude').val();
								path.venues.push(venue);
							} while((venueDiv = venueDiv.next()).length > 0);
							
							$.ajax({
								url: "${pageContext.request.contextPath }/planCont/editPath.do",
								dataType: 'text',
								type: 'POST',
								data: {
									'path': JSON.stringify(path)
								},
								success: function(result) {
									var pathSummary = eval('('+result+')');
									console.log(pathSummary);
									
									thisElement.removeClass('hiddenDiv');
									thisElement.find('p#pathName').text(pathSummary.path_name);
									thisElement.find('p#pathSummary').text(pathSummary.path_summary);
									pathEditDiv.remove();
								}
							})
						});
						
						pathEditDiv.find('input#cancelPathBtn').click(function() {
							// 취소 버튼 누를 때 할 일
							thisElement.removeClass('hiddenDiv');
							pathEditDiv.remove();
							
							var tempPathLine = polyline[pathCountOfThisElement].getPath();
							tempPathLine.splice(0, tempPathLine.length);
							for(var i = 0; i < mapPinList.length; i++) {
								tempPathLine.push(new naver.maps.LatLng(mapPinList[i].lat, mapPinList[i].lng));
							}
							
							isModifyCondition = false;
						});
						
						pathEditDiv.find('input#removePathBtn').click(function() {
							// 삭제 버튼 누를 때 할 일
							// TODO: 경로 저장 방법에 의해 보류중
							for(var i = 0; i < polyline.length; i++) {
								console.log(polyline[i]);
							}
							console.log(pathCountOfThisElement);
							polyline.splice(pathCountOfThisElement, 1);
							$.ajax({
								url: "${pageContext.request.contextPath }/planCont/removePath.do",
								dataType: 'text',
								type: 'POST',
								data: {
									'pathNo': pathEditDiv.find('input#pathNo').val()
								},
								success: function(result) {
									thisElement.remove();
									pathEditDiv.remove();
								}
							})
							pathCount -= 1;
							
							if(pathCount == 0) {
								$('input[type="button"]#addPath').val('일정 만들기');
							}
						});
						
					}
				});
			}
			
			
			if('${PLAN}' != '') {
				// 기존의 존재하는 플랜을 불러오기 할 때 자동으로 플랜 정보, 경로 정보를 불러옴
				var planInfo = $('div.planInfo');
				planInfo.find('input#planNo').val('${PLAN.plan_no}');
				planInfo.find('input#planName').val('${PLAN.plan_name}');
				planInfo.find('input#planComment').val('${PLAN.plan_commt}');
				planInfo.find('input#planCost').val('${PLAN.plan_cost}');
				planInfo.find('input#planPersons').val('${PLAN.plan_persons}');
				planInfo.find('select#planStyle').val('${PLAN.plan_style}');
				
				<c:forEach var="path" items="${PLAN.pathlist }">
					var arr = new Array();
					<c:forEach var="venue" items="${path.venuelist}">
						console.log('latitude: ${venue.ven_lati}, longitude: ${venue.ven_long}');
						arr.push(new naver.maps.LatLng('${venue.ven_lati}','${venue.ven_long}'));
					</c:forEach>
					polyline.push(new naver.maps.Polyline({
						map: map, //라인을 표시할 지도 객체
						path: arr, //라인 좌표를 저장할 배열
						strokeColor: pathColors[(pathCount % 10)], //라인컬러
						strokeWeight: 5, //라인 두깨
						strokeStyle: 'longdash'
					}));
					
					// TODO: 핀 찍어야댐!
					var pathDiv = $('div#pathDivForm').clone().appendTo('div#pathList').removeClass('hiddenDiv').attr('id', 'pathDiv');
					pathDiv.find('p#pathName').html('${path.path_name}');
					pathDiv.find('p#pathSummary').html('${path.path_summary}');
					pathDiv.find('input#pathNo').val('${path.path_no}');
					pathDiv.find('input#pathCount').val(pathCount);
					pathCount++;
					pathDiv.click(showEditForm);
				</c:forEach>	
			}
			
			var date = new Date();
			var defaultPlanName = date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate() + "에 저장된 나의 여행";
			var defaultPlanComment = "여행 넘나 재밌는 것~";
			
			$('input#planName').attr('placeholder', '여행 이름이에요. 예) ' + defaultPlanName);
			$('input#planComment').attr('placeholder', '여행 설명이에요. 예) ' + defaultPlanComment);
			
			/**
			 * #planCost, #planPersons에 숫자만 입력받게 하기
			 */
			$('#planCost, #planPersons').keydown(function(event) {
				var keyId = event.keyCode;
				if( (keyId >= 48 && keyId <= 57) || (keyId >= 96 && keyId <= 105) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 || keyId == 9 ) {
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
			
			/**
			 * 서버에 하나의 path를 저장하고 왼쪽에 경로 제목 + 경로요약으로 묶어서 표시
			 */
			$('input#savePath').click(function() {
				if('${sessionScope.no}' == '') {
					alert('로그인 하세요')
					action_do('login');
				} else {
					if(pathObj.length > 1) {
						var arr = new Array();
						
						for(var i = 0; i < pathObj.length; i++) {
							var object = new Object();
							object.venueName = decodeURIComponent(pathObj[i].name);
							object.venueComment = pathObj[i].comment;
							object.venueType = pathObj[i].type;
							object.lat = polyline[pathCount].getPath().getAt(i).lat().toString();
							object.lng = polyline[pathCount].getPath().getAt(i).lng().toString();
							arr.push(object);
						}
						
						var plan = new Object();
						plan.planNo = $('input#planNo').val() * 1;
						if($('input#planName').val() == '') {
							plan.planName = defaultPlanName;	
						} else {
							plan.planName = $('input#planName').val();					
						}
						
						if($('input#planComment').val() == '') {
							plan.planComment = defaultPlanComment;
						} else {
							plan.planComment = $('input#planComment').val();					
						}
						
						if($('input#planCost').val() == '') {
							plan.planCost = 0;
						} else {
							plan.planCost = $('input#planCost').val() * 1;
						}
						
						if($('input#planPersons').val() == '') {
							plan.planPersons = 1;	
						} else {
							plan.planPersons = $('input#planPersons').val() * 1;					
						}
						plan.planStyle = $('select#planStyle option:selected').val() * 1;
						plan.locationNo = loc_no;
						plan.path = arr;
						
						console.log(JSON.stringify(plan));
						
						$.ajax({
							url: "${pageContext.request.contextPath }/planCont/savePath.do",
							dataType: 'text',
							type: 'POST',
							data: {
									'plan': JSON.stringify(plan),
									'isFirstAdd': isFirstAdd
								},
							success: function(result) {
								var planAndPath = eval('('+result+')');
								var plan = planAndPath.plan;
								var path = planAndPath.path;
								
								$('input#planNo').val(plan.plan_no);
								$('input#planName').val(plan.plan_name);
								$('input#planComment').val(plan.plan_commt);
								$('input#planCost').val(plan.plan_cost);
								$('input#planPersons').val(plan.plan_persons);
								$('select#planStyle').val(plan.plan_style);
								
								var pathDiv = $('div#pathDivForm').clone().appendTo('div#pathList').removeClass('hiddenDiv').attr('id', 'pathDiv');
								pathDiv.find('p#pathName').html(path.path_name);
								pathDiv.find('p#pathSummary').html(path.path_summary);
								pathDiv.find('input#pathNo').val(path.path_no);
								pathDiv.find('input#pathCount').val(pathCount);
								pathDiv.click(showEditForm);
								
								isAddCondition = false;
								isFirstAdd = false;
								$('div#defaultAddDiv').removeClass('hiddenDiv')
								$('div#addPathDiv').addClass('hiddenDiv')
								$('input[type="button"]#addPath').val('일정 추가하기');
								
								pathCount += 1;
								pathObj = new Array();
							},
							error: function(request, error) {
								alert('message: ' + request.responseText);
							}
						})
					} else if (pathObj.length == 0){
						alert('오른쪽 맵에서 가고 싶은 곳을 두 개 이상 선택해 주세요.');
					} else if (pathObj.length == 1){
						alert('하나 더 선택해 주세요.');
					}
				}
			})
			
			/**
			 * 일정 생성 버튼을 누르면 일정이 추가될 수 있도록 폼을 바꿔줌
			 */
			$('input[type="button"]#addPath').click(function() {
				if (isModifyCondition) {
					// 다른 일정 수정하기가 열려있을 때
					if(confirm('수정중인 일정을 취소할까요?')) {
						// 취소
						$('div.editing').find('input#cancelPathBtn').click();
					} else {
						return;
					}
				}
				
				isAddCondition = true;
				$('div#defaultAddDiv').addClass('hiddenDiv');
				$('div#addPathDiv').removeClass('hiddenDiv');
				
				//라인 생성
				polyline.push(new naver.maps.Polyline({
					map: map, //라인을 표시할 지도 객체
					path: new Array(), //라인 좌표를 저장할 배열
					strokeColor: pathColors[(pathCount % 10)], //라인컬러
					strokeWeight: 5, //라인 두깨
					strokeStyle: 'longdash'
				}));
				updateList();
			})
			
			/**
			 * 경로 초기화
			 */
			$('input[type="button"]#cancelPath').click(function() {
				pathObj = new Array();
				polyline.pop().getPath().clear();
				
				isAddCondition = false;
				$('div#defaultAddDiv').removeClass('hiddenDiv')
				$('div#addPathDiv').addClass('hiddenDiv')
				deleteMarker();
			});
			
			// 새로고침 시 플랜 새로 만들기가 아닌 내 플랜 보기로 옮겨간다 (아직 안됨)
			$(window).bind('beforeunload', function() {
				console.log('새로고침!')
				$(location).attr('href', '${pageContext.request.contextPath}/myapp');
				/* location.href='${pageContext.request.contextPath}/myapp'; */
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
	clear: none;
}

.SideBar input, select {
	height: 30px;
	margin-top: 2px;
	margin-bottom: 2px;
	width: 100%;
}

.Map {
	width: 80%;
	padding: 10px;
}

/* 	#planName, #planComment, #planCost, #planPersons, #planStyle {
		width: 100%;
	} */
.planInfo {
	margin-bottom: 10px;
}

/* #addPath {
		width: 100%;
	} */
#savePath, #cancelPath {
	width: 49%;
}

#inputDiv {
	float: left;
	width: 80%;
}

#cancelDiv {
	float: left;
	width: 20%;
	/* height: 100%; */
}

#cancelImg {
	margin: 30%;
	width: 40%;
}

/* #venueName, #venueComment {
		width: 100%;
	} */
.hiddenDiv {
	display: none;
}

#pathDiv {
	padding: 10px;
}

#pathDiv:hover {
	background-color: rgb(235, 242, 255);
}

#defaultAddDiv {
	margin-top: 5px;
	margin-bottom: 5px;
}

#pathEditDiv {
	margin-top: 10px;
	margin-bottom: 10px;
	padding: 10px;
	background-color: rgb(243, 216, 218);
}

/* input#pathName {
		margin-top: 5px;
		width: 100%;
	} */
#modifyPathBtn, #cancelPathBtn {
	/* margin-top: 5px; */
	width: 49%;
}

#removePathBtn {
	margin-top: 5px;
	background-color: rgb(255, 0, 78);
	width: 100%;
	color: white;
}

#infoWindowDiv {
	width: 300px;
	height: 170px;
}

#venueThumbnailDiv {
	width: 40%;
	float: left;
	padding: 5px;
}

#venueThumbnail {
	width: 100%;
}

#venueInfoDiv {
	/* width: 60%; */
	padding: 5px;
}

#venueName{
	font-size: 20px;
}

#venueAddressDiv{
	clear: both;
}

.wrap-loading{ /*화면 전체를 어둡게 합니다.*/
    position: fixed;
    left:0;
    right:0;
    top:0;
    bottom:0;
    background: rgba(0,0,0,0.2); /*not in ie */
    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000');    /* ie */
}
    .display-none{ /*감추기*/
        display:none;
    }

</style>
<!---------------------------------->
<body>
<!-- <div class="wrap-loading display-none">
    <div><img src="../resources/png/loading.gif" /></div>
</div>  -->
	<br>
	<br>
	<br>
	<div class='AppContainer'>
		<!-- 전체 화면 영역 -->
		<div class='SideBar'>
			<!-- 좌측 사이드바 -->
			<div class='planInfo'>
				<!-- 계획 정보 입력 -->
				<input type='hidden' id='planNo' value='0'>
				<input type='text' id='planName'>
				<input type='text' id='planComment'>
				<input type="text" id='planCost' placeholder='여행 비용'> 
				<input type="text" id='planPersons'	placeholder='여행 인원 (기본 값: 1)'> 
				<select id='planStyle'>
					<option label='문화 탐방' value='1'>
					<option label='식도락' value='2'>
					<option label='쇼핑' value='3'>
					<option label='휴식' value='4'>
				</select>
			</div>
			<div class='pathInfo'>
				<!-- 경로 정보 입력 -->
				<div id='pathList'></div>
				<div id='defaultAddDiv'>
					<input type='button' id='addPath' value='일정 만들기'>
				</div>
				<div id='addPathDiv' class='hiddenDiv'>
					<form name="venueForm"	action="${pageContext.request.contextPath}/plancont/addplan.do">
						<div id='venueList'></div>
						<input type="button" id='savePath' value="일정 저장"> <input
							type="button" id='cancelPath' value="일정 취소">
					</form>
				</div>
			</div>
		</div>

		<div class='Map'>
			<!-- 네이버 지도 -->
			<div id="map" style="height: 900px;"></div>
		</div>
	</div>
	<div id='pathDivForm' class='hiddenDiv'>
		<p id='pathName'></p>
		<p id='pathSummary'></p>
		<input type='hidden' id='pathNo'> <input type='hidden'
			id='pathCount'>
	</div>

	<div id='venueDivForm' class='hiddenDiv'>
		<div id='inputDiv'>
			<p>
				<input type='text' name='venueName' id='venueName'
					placeholder='장소 이름을 입력해주세요.'>
			</p>
			<p>
				<input type='text' name='venueComment' id='venueComment'
					placeholder='장소에 관해 메모해주세요.'>
			</p>
		</div>
		<div id='cancelDiv'>
			<img id='cancelImg'
				src='http://plainicon.com/dboard/userprod/2803_dd580/prod_thumb/plainicon.com-43958-32px.png' />
		</div>
		<input type='hidden' name='venueNo' id='venueNo'> 
		<input type='hidden' name='venueLatitude' id='venueLatitude'> 
		<input type='hidden' name='venueLongitude' id='venueLongitude'> 
		<input type='hidden' name='venueOrder' id='venueOrder'>
	</div>

	<div id='pathEditDivForm' class='hiddenDiv'>
		<input type='text' name='pathName' id='pathName' placeholder='경로 이름을 입력해주세요.'>
		<input type='hidden' name='pathNo' id='pathNo'>
		<div id='venueListInSavedPath'></div>

		<div id='btnsDiv'>
			<input type='button' id='modifyPathBtn' value='수정할게요'>
			<input type='button' id='cancelPathBtn' value='취소할게요'> 
			<input type='button' id='removePathBtn' value='없애주세요'>
		</div>
	</div>
	
	<div id='infoWindowForm' class='hiddenDiv'>
	
		<div id='venueThumbnailDiv'>
			<img id='venueThumbnail' src='${pageContext.request.contextPath}/resources/png/noImage.jpg'/>
		</div>
		<div id='venueInfoDiv'>
			<p id='venueName'></p>
		</div>
	
		<div id='venueAddressDiv'>
			<p id='venueAddress'></p>
		</div>
		<p>아이콘을 클릭하면 내 일정에 추가됩니다.</p>
	</div>
</body>
</html>