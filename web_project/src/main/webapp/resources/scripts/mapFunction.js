/**
 * Naver Api 기능구현
 */
	
	var i = -1;
	var count = 0;
	var pathNum = 0;
	var searchList = [];
	var commentList = new Array();	// 장소 메모 리스트

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
	function hideMarker(map, marker) {
		
	    if (!marker.setMap()) return;
	    marker.setMap(null);
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
		var venue = new Object();
		venue.name = name;
		venue.comment = '';
		venue.type = '1';		// <-- 타입을 임의로 지정해 줬으나 나중에는 장소 타입에 따라 다르게 줘야 함
		pathObj.push(venue);
		updateList(); //화면 업데이트
		
		if(infowindow.getMap()){
			infowindow.close();
		}
	}
	
	//경로 초기화
	function resetPath(){
		var path = polyline.getPath();
		path.clear();
		pathObj = new Array();
		updateList();
		
	}
	function updateList(){
		var pathNum = 0;
		var path = polyline.getPath();
		var venueList = document.getElementById("venueList");
		//기존 리스트 삭제
		deleteList(venueList);
		//변경된 값을 가진 새로운 리스트 생성
		for(var i = 0 ; i < path.length ; i++){
			var newVenue = document.createElement("div");
			newVenue.id = 'venue_'+i;
			var html = "<div id='inputDiv'>"
						+	"<p><input type='text' name='venueName' id='venueName' placeholder='장소 이름을 입력해주세요.' onkeyup='modifyName("+i+",this.form)'></p>"
						+	"<p><input type='text' name='venueComment' id='venueComment' placeholder='장소에 관해 메모해주세요.' onkeyup='modifyComment("+i+",this.form)'></p>"
						+"</div>"
						+"<div id='cancelDiv'>"
						+	"<img id='cancelImg' src='http://plainicon.com/dboard/userprod/2803_dd580/prod_thumb/plainicon.com-43958-32px.png' onclick='delPath("+i+")'/>"
						+"</div>"
						+"<input type='hidden' name='venueLatitude' id='venueLatitude'>"
						+"<input type='hidden' name='venueLongitude' id='venueLongitude'>"
						+"<input type='hidden' name='venueLocation' id='venueLocation'>"
			newVenue.innerHTML = html;
			venueList.appendChild(newVenue);
			var length = document.venueForm.venueName.length;
			if(document.venueForm.venueName.length <= length){
				document.venueForm.venueName[i].value = decodeURIComponent(pathObj[i].name);
				document.venueForm.venueLatitude[i].value = path.getAt(i).lat();
				document.venueForm.venueLongitude[i].value = path.getAt(i).lng();
				document.venueForm.venueComment[i].value = decodeURIComponent(pathObj[i].comment);
			} else {
				document.venueForm.venueName.value = decodeURIComponent(pathObj[i].name);
				document.venueForm.venueLatitude.value = path.getAt(i).lat();
				document.venueForm.venueLongitude.value = path.getAt(i).lng();
				document.venueForm.venueComment.value = decodeURIComponent(pathObj[i].comment);
			}
		}
	}
	
	function modifyName(num, form) {
		if(form.venueName.length < 2) {
			pathObj[num].name = form.venueName.value;
		} else {
			pathObj[num].name = form.venueName[num].value;
		}
	}
	
	function modifyComment(num, form) {
		if(form.venueComment.length < 2) {
			pathObj[num].comment = form.venueComment.value;			
		} else {
			pathObj[num].comment = form.venueComment[num].value;
		}
	}
	
	//추가 경로 삭제
	function delPath(num){
		var iDiv = document.getElementById("venue_"+num);
		var addvenue =  iDiv.parentNode;
		addvenue.removeChild(iDiv);
		var path = polyline.getPath();
		path.removeAt(num);
		pathObj.splice(num, 1);
		updateList();
	}
	
	// 화면의 리스트 삭제
	function deleteList(parentNode){
		while(parentNode.hasChildNodes()){
			var childNode = parentNode.firstChild;
			parentNode.removeChild(childNode);
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
	//마커 삭제
	function deleteMarker(){
		for(var i = 0 ; i < markers.length ; i++){
			markers[i].setMap(null);
			infowindows[i].setMap(null);
		}
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
	//정규식 태그제거 함수
	function regTag(str){
		var reg = /(<([^>]+)>)/gi;
		
		if(reg.test(str)){
			var t = str.replace(reg, "");
		}else{
			var t= str;
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
		/*if(searchList.length != 0){
			searchList = [];
		}*/
		
		var params = "data="+data;
		sendRequest("../plancont/searchloc.do", params, setSearchPlace, 'POST');

		/*var params = "query="+data;
		sendSearchRequest("https://openapi.naver.com/v1/search/local.xml", params, setSearchPlace, 'GET');*/
	}

	function setSearchPlace(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var responseList = httpRequest.responseText;
				var search = eval("("+ responseList +")");
				for(var i = 0 ; i < search.length ; i++){
					var before = search[i].title;
					searchList.push(regTag(before));
				}
			}else {
				alert("해당 브라우저에서 지원하는 기능이 아닙니다");
			}
		}
	}