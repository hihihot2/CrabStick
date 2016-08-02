/**
 * Naver Api 사용한 기능구현
 */
//마커 생성
	function setPlace(lat, lng) {
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
		markers.push(marker);
		var infowindow = new naver.maps.InfoWindow({
			//content: '<div style="width:400px;height:300px;text-align:top;">'+loc+'<input type="button" value="+" onclick="addPath()"></div>'
		});
		infowindows.push(infowindow);
	}
	//리스너 생성
	function setListener(name){
		var len = markers.length-1;
		naver.maps.Event.addListener(markers[len], 'click', function(e) {
			var marker = markers[len], infowindow = infowindows[len];
			infowindow.setContent('<div style="width:400px;height:200px;text-align:top;">'
					+name+'<input type="button" value="+" onclick=addPath('+marker.getPosition().lat()+','+marker.getPosition().lng()+',"'+name+'")></div>');//
			if(infowindow.getMap()){
				infowindow.close();
			}else {
				infowindow.open(map, marker);
			}
		})
	}
	function addPath(lat, lng, name){
		//venue폼에 위도 경도 저장

			document.ven_form[i].ven_name.value = name;
			document.ven_form[i].ven_lati.value = lat;
			document.ven_form[i].ven_long.value = lng;
		

		
		
		var path = polyline.getPath();
		path.push(new naver.maps.LatLng(lat,lng));
		

		
	}
	
	//화면 업데이트 경계내부의 marker만 표시
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
	//마커 표시
	function showMarker(map, marker) {

	    if (marker.setMap()) return;
	    marker.setMap(map);
	}
	//마커 숨기기
	function hideMarker(map, marker) {

	    if (!marker.setMap()) return;
	    marker.setMap(null);
	}
	
	