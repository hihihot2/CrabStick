/**
 * Naver Api 기능구현
 */
	var i = -1;
	var count = 1;
	var pathNum = 0;
	

	//위치 생성
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
		var infowindow = new naver.maps.InfoWindow();
		infowindows.push(infowindow);
	}
	//리스너 생성
	function setListener(name){
		var enc = encodeURIComponent(name);
		var len = markers.length-1;
		naver.maps.Event.addListener(markers[len], 'click', function(e) {
			var marker = markers[len], infowindow = infowindows[len];
			infowindow.setContent('<div style="width:400px;height:200px;text-align:top;">'
					+'<span><h3>'+name+'</h3><img style="width:30px;height:30px;" src="../resources/png/cancel.png" onclick="closeWindow('+len+')"/>'
					+'<input type="button" value="+" onclick=addPath('+len+','+marker.getPosition().lat()+','+marker.getPosition().lng()+',"'+enc+'")>'
					+'</div>'
					);
			if(infowindow.getMap()){
				infowindow.close();
			}else {
				infowindow.open(map, marker);
			}
		})
	}
	function closeWindow(len){
		var infowindow = infowindows[len];
		infowindow.close();
	}
	function addPath(len, lat, lng, name){
		var infowindow = infowindows[len];
		var path = polyline.getPath();
		path.push(new naver.maps.LatLng(lat,lng));
		pathObj.push(decodeURIComponent(name));
		
		updateList(); //화면 업데이트
		
		if(infowindow.getMap()){
			infowindow.close();
		}
	}
	//추가 경로 삭제
	function delPath(num){
		var iDiv = document.getElementById("path"+num);
		var addvenue =  iDiv.parentNode;
		addvenue.removeChild(iDiv);
		var path = polyline.getPath();
		path.removeAt(num);
		pathObj.splice(num, 1);
		updateList();
	}
	//경로 초기화
	function resetPath(){
		var path = polyline.getPath();
		path.clear();
		for(var i = 0 ; i <= pathObj.length ; i++){
			pathObj.shift();
		}
		updateList();
		
	}
	function updateList(){
		var pathNum = 0;
		var path = polyline.getPath();
		var addvenue =  document.getElementById("addvenue");
		//기존 리스트 삭제
		deleteList(addvenue);
		//변경된 값을 가진 새로운 리스트 생성
		for(var i = 0 ; i < path.length ; i++){
			var newvenue = document.createElement("div");
			newvenue.id = 'path'+i;
			var html = "<input type='text' name='ven_name' placeholder='장소이름을 입력하세요' readonly='readonly'><input type='button' value='cancel' onclick=delPath("+i+")><br>"
			+"<input type='hidden' name='ven_lati' placeholder='위도'><input type='hidden' name='ven_long' placeholder='경도'>"
			+"<input type='hidden' name='ven_order' value='count'><br>";		
			newvenue.innerHTML = html;
			addvenue.appendChild(newvenue);
			var length = document.ven_form.ven_name.length;
			if(document.ven_form.ven_name.length <= length){
				document.ven_form.ven_name[i].value = decodeURIComponent(pathObj[i]);
				document.ven_form.ven_lati[i].value = path.getAt(i).lat();
				document.ven_form.ven_long[i].value = path.getAt(i).lng();		
			}else{
				document.ven_form.ven_name.value = decodeURIComponent(pathObj[i]);
				document.ven_form.ven_lati.value = path.getAt(i).lat();
				document.ven_form.ven_long.value = path.getAt(i).lng();	
			}
		}
	}
	function deleteList(parentNode){
		while(parentNode.hasChildNodes()){
			parentNode.removeChild(parentNode.firstChild);
		}
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
	//정규식 변환 함수
	function regExp(str){
		var reg = /\s[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		
		if(reg.test(str)){
			//특수문자 제거
			var t = str.replace(reg, "");
		}else{
			var t = str;
		}
		return t;
	}
	//키 이벤트 처리 함수 -> 엔터 확인
	function keyEventChk(){
		if(event.keyCode == 13){
			requestSearch();
		}
	}
	//지역 검색
	function requestSearch(){
		var data = document.getElementById("searchData").value;
		var params = "query="+data;
		/*var params = "data="+data;
		sendRequest("../plancont/searchloc.do", params, setSearchPlace, 'POST');*/
		sendSearchRequest("https://openapi.naver.com/v1/search/local.xml", params, setSearchPlace(), 'GET');
	}
	function setSearchPlace(){
		alert(httpRequest.readyState);
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				alert(str);
			}
		}
	}