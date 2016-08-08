/**
 * Naver Api 기능구현
 */
	var i = -1;
	var count = 0;
	var pathNum = 0;
	var searchList = [];

	//위치 생성
	function setPlace(lat, lng, type) {
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
		var subMarker = new Array();
		subMarker.push(type);
		subMarker.push(marker);
		markers.push(subMarker);
		var infowindow = new naver.maps.InfoWindow();
		var subWindow = new Array();
		subWindow.push(type);
		subWindow.push(infowindow);
		infowindows.push(subWindow);
	}	
	//리스너 생성
	function setListener(name, type){
		var enc = encodeURIComponent(name);
		var len = markers.length-1;
		naver.maps.Event.addListener(markers[len][1], 'click', function(e) {
			var marker = markers[len][1], infowindow = infowindows[len][1];
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
		var infowindow = infowindows[len][1];
		infowindow.close();
	}
	function addPath(len, lat, lng, name){
		var infowindow = infowindows[len][1];
		var path = polyline.getPath();
		path.push(new naver.maps.LatLng(lat,lng));
		var venue = new Object();
		venue.name = name;
		venue.comment = '';
		pathObj.push(venue);
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
						+	"<p><input type='text' name='venueName' id='venueName' placeholder='장소 이름을 입력해주세요.'></p>"
						+	"<p><input type='text' name='venueComment' id='venueComment' placeholder='장소에 관해 메모해주세요.'></p>"
						+"</div>"
						+"<div id='cancelDiv'>"
						+	"<img id='cancelImg' src='http://plainicon.com/dboard/userprod/2803_dd580/prod_thumb/plainicon.com-43958-32px.png'/>"
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
			} else {
				document.venueForm.venueName.value = decodeURIComponent(pathObj[i].name);
				document.venueForm.venueLatitude.value = path.getAt(i).lat();
				document.venueForm.venueLongitude.value = path.getAt(i).lng();	
			}
		}
	}
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

	        marker = markers[i][1];
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
	function checkcategory(i,lat,lng){
		var categories = document.getElementsByName("categorychk");
		if(categories[i].checked){
			var params = "branch="+i+"&city_latitude="+lat+"&city_longitude="+lng;
			sendRequest("../placeCont/branch.do", params, markBranch, 'POST');
		}else{
			unmarkBranch(i);
		}
	}
	function markBranch(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var result = httpRequest.responseText;
				var list = eval("("+ result +")");
				for(var i = 0 ; i < result.length ; i++){
					setPlace(list[i].lat, list[i].lng, list[i].type);
					setListener(list[i].name, list[i].type);
				}
			}else {
				alert("해당 브라우저에서 지원하는 기능이 아닙니다");
			}
		}
	}
	function unmarkBranch(type){
		var tmp = new Array();
		for(var i = 0 ; i < markers.length ; i++){
			if(markers[i][0] != type){
				var subTmp = new Array();
				tmp.push(markers[i]);
			}else{
				var marker = markers[i][1];
				marker.setMap(null);
			}
		}
		markers = tmp;
	}