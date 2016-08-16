/**
 * Naver Api 기능구현
 */
	
	var i = -1;
	var count = 0;
	var pathNum = 0;
	var searchList;
	var city_code;
	var siguncode;

	//위치 생성
	function setPlace(lat, lng, type) {
		var url;
		switch(type){
		case 0: url = "../resources/png/hotel.png";break;
		case 1: url = "../resources/png/food.png";break;
		case 2: url = "../resources/png/castle.png";break;
		case 3: url = "../resources/png/shopping.png";break;
		case 4: url = "../resources/png/attraction.png";break;
		default: url= "../resources/png/search.png";break;
		}
		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(lat, lng),
			icon : {
				url: url
			}
		});
		if(type == 's'){
			marker.setMap(map);
		}
		var subMarker = new Array();
		subMarker.push(type);//flag
		subMarker.push(type);//type
		subMarker.push(marker);//marker
		markers.push(subMarker);
		var infowindow = new naver.maps.InfoWindow();
		var subWindow = new Array();
		subWindow.push(type);
		subWindow.push(type);
		subWindow.push(infowindow);
		infowindows.push(subWindow);
	}	
	//리스너 생성
	function setListener(obj, type){
		var enc = encodeURIComponent(obj[0]);
		var len = markers.length-1;
		var marker = markers[len][2], infowindow = infowindows[len][2];
		
		infowindow.setContent('<div style="width:300px;height:170px;"><div style="width:300px;height:88%;text-align:top;">'
				+'<table style="width:100%;height:auto;"><tr>'
				+'<td style="width:70%;text-align:center;"><h3>'+obj[0]+'</h3></td>'
				+'<td style="width:30%;"><img src="'+obj[2]+'" style="width:110px;height:70px;"></td>'
				+'</tr></table>'
				+'Address : '+obj[1]+'<br>'
				+'</div>'
				+'<footer style="height:12%;text-align:center;color:blue;">아이콘을 클릭하면 내 일정에 추가됩니다</footer></div>'
				);

		naver.maps.Event.addListener(markers[len][2], 'click', function(e) {
			if(confirm("일정에 추가하시겠습니까?")){
				addPath(len, marker.getPosition().lat(), marker.getPosition().lng(), enc);
			}
		});
		naver.maps.Event.addListener(markers[len][2], 'mouseover', function(e) {
			infowindow.open(map, marker);
		});
		naver.maps.Event.addListener(markers[len][2], 'mouseout', function(e) {
			infowindow.close();
		});
	}
	function closeWindow(len){
		var infowindow = infowindows[len][2];
		infowindow.close();
	}
	
	function addPath(len, lat, lng, name){
		if(!isAddCondition) {
			alert('왼쪽에서 일정 만들기를 눌러주세요~');
		} else {
			var infowindow = infowindows[len][2];		// ??
			var marker = markers[len][2];				// ??
			var path = polyline[pathCount].getPath();
			path.push(new naver.maps.LatLng(lat,lng));
			var venue = new Object();
			venue.name = name;
			venue.comment = '';
			venue.type = '1';	
			venue.markerNum = len;// <-- 타입을 임의로 지정해 줬으나 나중에는 장소 타입에 따라 다르게 줘야 함
			pathObj.push(venue);
			updateList(); //화면 업데이트
			
			var sub = new Array();
			sub.push(marker);
			sub.push(infowindow);
			myPath.push(sub);
		}
	}
	
	function updateList(){
		var pathNum = 0;
		var path = polyline[pathCount].getPath();
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
						+	"<img id='cancelImg' src='http://plainicon.com/dboard/userprod/2803_dd580/prod_thumb/plainicon.com-43958-32px.png' onclick='delPath("+i+","+pathObj[i].markerNum+")'/>"
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
	function delPath(num, len){
		var marker = markers[len][2];
		var iDiv = document.getElementById("venue_"+num);
		var addvenue =  iDiv.parentNode;
		addvenue.removeChild(iDiv);
		var path = polyline[pathCount].getPath();
		path.removeAt(num);
		pathObj.splice(num, 1);
		myPath[num][0].setMap(null);
		myPath.splice(num, 1);
		/*markers[len][0] = markers[len][1];
		marker.setMap(null);*/
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

	        //marker = markers[i][2];
	    	marker = markers[i][0];
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
			if(markers[i][0] == 'm'){
				markers[i][0] = markers[i][1];
				markers[i][2].setMap(null);
			}
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
			//unmarkBranch('s');
			var data = document.getElementById("searchData").value;
			for(var i = 0 ; i < searchList.length ; i++){
				if(searchList[i][0] == data){
					var params = "addr="+searchList[i][1];
				}
			}
			sendRequest("../plancont/searchlatlng.do", params, setSearchPlace, 'POST');
		}
	}
	function setSearchPlace(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var result = httpRequest.responseText;
				var loc = eval("("+ result +")");
				var data = document.getElementById("searchData").value;
				map.setCenter(new naver.maps.LatLng(loc.lat, loc.lng));
				setPlace(loc.lat, loc.lng, 's');
				var tmp = new Array();
				tmp.push(data);
				tmp.push(regTag(loc.address));
				if(loc.img != ''){
					tmp.push(loc.img);
				}else{
					tmp.push("../resources/png/noImage.jpg");
				}
				setListener(tmp, 's');
			}else {
				alert("해당 브라우저에서 지원하는 기능이 아닙니다");
			}
		}
	}
	//카테고리
	function checkcategory(i,lat,lng){
		var categories = document.getElementsByName("categorychk");
		if(categories[i].checked){
			for(var j = 0 ; j < markers.length ; j++){
				if(markers[j][0] == i){
					markers[j][2].setMap(map);
				}
			}
		}else{
			for(var j = 0 ; j < markers.length ; j++){
				if(markers[j][0] == i){
					markers[j][2].setMap(null);
				}
			}
		}
	}
	function markBranch(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var result = httpRequest.responseText;
				var list = eval("("+ result +")");
				for(var i = 0 ; i < list.length ; i++){
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
				var marker = markers[i][2];
				marker.setMap(null);
			}
		}
		markers = tmp;
	}
