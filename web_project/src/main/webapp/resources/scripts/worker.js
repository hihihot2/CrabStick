/**
 * 멀티쓰레드 생성 파일 적용범위 (사파리, 아이폰 적용불가)
 */
var makers = [];
//메세지 수신하여 시간이 오래 걸리는 작업을 처리한다
onmessage = function(event){
  var receiveData = event.data;
  sendRequest("${pageContext.request.contextPath}/viewcont/markwifi.do", null, setMarkOnMaps, 'POST');
  
  //워커를 호출한 곳으로 결과 메시지를 전송한다
  //var sendData = receiveData + "OK~ I'm Worker"
  postMessage(makers);
}

function setMarkOnMaps(){
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var result = httpRequest.responseText;
			//alert(result);
			var locInfo = eval("("+ result +")");
			for(var i = 0 ; i < locInfo.length ; i++){	
				var marker = new naver.maps.Marker({
				    position: new naver.maps.LatLng(locInfo[i].lat, locInfo[i].lang),
				    map: null,
				    title: 'Click to zoom'
				});
				markers.push(marker);
			}
		}
	}
}

function getXMLHttpRequest() {
	if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e1) { return null; }
		}
	} else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else {
		return null;
	}
}
var httpRequest = null;

function sendRequest(url, params, callback, method) {
	httpRequest = getXMLHttpRequest();
	var httpMethod = method ? method : 'GET';
	if (httpMethod != 'GET' && httpMethod != 'POST') {
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params;
	var httpUrl = url;
	if (httpMethod == 'GET' && httpParams != null) {
		httpUrl = httpUrl + "?" + httpParams;
	}
	httpRequest.open(httpMethod, httpUrl, true);
	httpRequest.setRequestHeader(
		'Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}