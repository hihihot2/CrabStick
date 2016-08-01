/**
 *  Naver Map Api References
 *  
 *  네이버 개발자 도구 가입 -> 인증 -> key값 생성 -> 스크립트 경로로 지정
 *  <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=key값"/>
 */

/**
 * Map 클래스는 애플리케이션에서 지도 인스턴스를 정의합니다. 
 * 이 객체를 생성함으로써 개발자는 지정한 DOM 엘리먼트에 지도를 삽입할 수 있습니다.
 * var map = new naver.maps.Map(객체를 삽입할 div의 id, 객체의 Options)
 */
var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5545373,126.9706856), // 지도의 초기 중심 좌표 설정 ->현재 서울역
    zoom: 8,                                               // 지도의 초기 줌 설정
    zoomControl: true,
    //size: (설정안할시 HTML 엘리먼트의 사이즈대로 설정
    //bounds: (지도의 초기 좌표 경계, 설정시 지정한 좌표 경계에 맞게 지도 설정)
    //mapTypes: (지도 유형, 설정 안할시 기본 지도 유형)
});

/**
 * Marker 클래스는 지도 위에 올리는 마커를 정의합니다.
 * var marker = new naver.maps.Marker(options)
*/
var marker = new naver.maps.Marker({
	//position: 마커의 위치를 나타내는 지도 좌표
	//map: 마커를 표시할 map 객체
	//icon: 모양 설정, 문자열로 입력시 사용할 이미지 경로
	//animaion: 지도에 마커 추가시에 시작할 에니메이션 설정
	//title: 마우스 오버시 나타나는 문자열
	//clickable: 기본 true, 마커 클릭 허용 여부
});

/**
 * Event 클래스는 이벤트 패턴을 정의하는 클래스입니다.
 * 이 클래스는 Event의 구현 일부를 상속받으며, 
 * NAVER 지도 API v3의 주요 클래스는 이 클래스를 상속받아 구현되었습니다.
 * naver.maps.Event.addListener(targetObject, eventName, listener);
 * eventName
 * -idle: 지도의 움직임이 종료되었을 때(유휴 상태) 이벤트가 발생합니다.
 * -click: 
 * -zoom_changed: 지도 줌 레벨이 변경되면 이벤트가 발생합니다.
 */
naver.maps.Event.addListener(markers, 'click', function(e) {
	alert('hi');
});

