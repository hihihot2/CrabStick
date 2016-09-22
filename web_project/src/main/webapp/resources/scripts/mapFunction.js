/**
 * Naver Api 기능구현
 */
	
	var i = -1;
	var count = 0;
	var pathNum = 0;
	var searchList;
	
	function closeWindow(len){
		var infowindow = infowindows[len][2];
		infowindow.close();
	}

	function showoverlay(latlng, type){
		if(overlay != null){
			overlay.setMap(null);
		}
		if(olflag == 0 && type == 0){
			//마우스 오른쪽 클릭 후 보이는 tab 창 초기화
			mapOverlay = function(options) {
				this._element = $('<div style="position:absolute;left:0;top:0;width:110px;background-color:#F2F0EA;text-align:center;border:2px solid #6C483B;">' +
	                    '<input id="ovl" style="width:106px" type="button" value="일정에 추가">' +
	                    '</div>')
			    this.setPosition(options.position);
			    this.setMap(options.map || null);
			};
			mapOverlay.prototype = new naver.maps.OverlayView();
			mapOverlay.prototype.constructor = mapOverlay;
			mapOverlay.prototype.setPosition = function(position) {
			    this._position = position;
			    this.draw();
			};
			mapOverlay.prototype.getPosition = function() {
			    return this._position;
			};
			mapOverlay.prototype.onAdd = function() {
			    var floatPane = this.getPanes().floatPane;

			    this._element.appendTo(floatPane);
			};
			mapOverlay.prototype.draw = function() {
			    if (!this.getMap()) {
			        return;
			    }
			    var projection = this.getProjection(),
			        position = this.getPosition(),
			        pixelPosition = projection.fromCoordToOffset(position);
			    this._element.css('left', pixelPosition.x);
			    this._element.css('top', pixelPosition.y);
			};
			mapOverlay.prototype.onRemove = function() {
			    var floatPane = this.getPanes().floatPane;
			    this._element.remove();
			    this._element.off();
			};
			if((type == 1 && olflag == 1) || (type == 0 && olflag == 0)){
				overlay = new mapOverlay({
					position: latlng,
					map: map
				});
			}
		}else if(olflag == 1 && type == 1){
			//마우스 오른쪽 클릭 후 보이는 tab 창 초기화
			mapOverlay = function(options) {
				this._element = $('<div style="position:absolute;left:0;top:0;width:110px;background-color:#F2F0EA;text-align:center;border:2px solid #6C483B;">' +
	                    '<input id="ovl" style="width:106px" type="button" value="일정에 추가">' +
	                    '</div>')
			    this.setPosition(options.position);
			    this.setMap(options.map || null);
			};
			mapOverlay.prototype = new naver.maps.OverlayView();
			mapOverlay.prototype.constructor = mapOverlay;
			mapOverlay.prototype.setPosition = function(position) {
			    this._position = position;
			    this.draw();
			};
			mapOverlay.prototype.getPosition = function() {
			    return this._position;
			};
			mapOverlay.prototype.onAdd = function() {
			    var floatPane = this.getPanes().floatPane;

			    this._element.appendTo(floatPane);
			};
			mapOverlay.prototype.draw = function() {
			    if (!this.getMap()) {
			        return;
			    }
			    var projection = this.getProjection(),
			        position = this.getPosition(),
			        pixelPosition = projection.fromCoordToOffset(position);
			    this._element.css('left', pixelPosition.x);
			    this._element.css('top', pixelPosition.y);
			};
			mapOverlay.prototype.onRemove = function() {
			    var floatPane = this.getPanes().floatPane;
			    this._element.remove();
			    this._element.off();
			};
			if((type == 1 && olflag == 1) || (type == 0 && olflag == 0)){
				overlay = new mapOverlay({
					position: latlng,
					map: map
				});
			}
		}		
	}
	function showoverlay2(latlng, type){
		if(overlay != null){
			overlay.setMap(null);
		}
		alert('ismarker');
		//마우스 오른쪽 클릭 후 보이는 tab 창 초기화
		mapOverlay = function(options) {
			this._element = $('<div style="position:absolute;left:0;top:0;width:110px;background-color:#F2F0EA;text-align:center;border:2px solid #6C483B;">' +
                    '<input id="ovl" style="width:106px" type="button" value="일정에 추가2">' +
                    '</div>')
		    this.setPosition(options.position);
		    this.setMap(options.map || null);
		};
		mapOverlay.prototype = new naver.maps.OverlayView();
		mapOverlay.prototype.constructor = mapOverlay;
		mapOverlay.prototype.setPosition = function(position) {
		    this._position = position;
		    this.draw();
		};
		mapOverlay.prototype.getPosition = function() {
		    return this._position;
		};
		mapOverlay.prototype.onAdd = function() {
		    var overlayLayer = this.getPanes().overlayLayer;

		    this._element.appendTo(overlayLayer);
		};
		mapOverlay.prototype.draw = function() {
		    if (!this.getMap()) {
		        return;
		    }
		    var projection = this.getProjection(),
		        position = this.getPosition(),
		        pixelPosition = projection.fromCoordToOffset(position);
		    this._element.css('left', pixelPosition.x);
		    this._element.css('top', pixelPosition.y);
		};
		mapOverlay.prototype.onRemove = function() {
		    var overlayLayer = this.getPanes().overlayLayer;
		    this._element.remove();
		    this._element.off();
		};
		overlay = new mapOverlay({
			position: latlng,
			map: map
		});
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
	
	
	
	// 화면의 리스트 삭제
	function deleteList(parentNode){
		while(parentNode.hasChildNodes()){
			var childNode = parentNode.firstChild;
			parentNode.removeChild(childNode);
		}
	}
	
	function updateList(){
		var pathNum = 0;
		var path = polyline[pathCount].getPath();
		var venueList = document.getElementById("venueList");
		//기존 리스트 삭제
		deleteList(venueList);
		//변경된 값을 가진 새로운 리스트 생성
		console.log('path 길이: ' + path.length);
		console.log('path: ' + path);
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
				if(pathObj[i].comment != null) {
					document.venueForm.venueComment[i].value = decodeURIComponent(pathObj[i].comment);
				}
			} else {
				document.venueForm.venueName.value = decodeURIComponent(pathObj[i].name);
				document.venueForm.venueLatitude.value = path.getAt(i).lat();
				document.venueForm.venueLongitude.value = path.getAt(i).lng();
				if(pathObj[i].comment != null) {
					document.venueForm.venueComment.value = decodeURIComponent(pathObj[i].comment);
				}
			}
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
		str = encodeURIComponent(str);
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
				
				var venue = {
						name: data,
						address: loc.address,
						lat: loc.lat,
						lng: loc.lng,
						img: null,
						type: 7
				};
				setPlace(venue);
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
