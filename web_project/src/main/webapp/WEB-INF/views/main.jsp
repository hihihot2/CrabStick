<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
/* Prevents the slideshow from flashing on load */
#slides {
display:none;
}

#slides .slidesjs-container {

}

#slides .slidesjs-previous {
margin-right: 5px;
float:left;
}
#slides .slidesjs-next {
margin-right: 5px;
float:left;
}

.slidesjs-pagination {
margin:6px 0 0;
float: right;
list-style: none;
}

.slidesjs-pagination li {
float: left;
margin: 0 1px;
}

.slidesjs-pagination li a {
display: block;
width: 13px;
height: 0;
padding-top: 13px;
background-image: url(http://slidesjs.com/img/pagination.png);
background-position: 0 0;
float: left;
overflow: hidden;
}

.slidesjs-pagination li a.active, .slidesjs-pagination li a:hover.active { background-position: 0 -13px }

.slidesjs-pagination li a:hover { background-position: 0 -26px }

#slides a:link, #slides a:visited { color: #333 }

#slides a:hover, #slides a:active { color: #9e2020 }


a.slidesjs-play,
a.slidesjs-stop {
  background-image: url(http://slidesjs.com/examples/playing/img/btns-next-prev.png);
  background-repeat: no-repeat;
  display:block;
  width:12px;
  height:18px;
  overflow: hidden;
  text-indent: -9999px;
  float: left;
  margin-right:5px;
}


a.slidesjs-play {
  width:15px;
  background-position: -25px 0;
}

a:hover.slidesjs-play {
  background-position: -25px -18px;
}

a.slidesjs-stop {
  width:18px;
  background-position: -41px 0;
}

a:hover.slidesjs-stop {
  background-position: -41px -18px;
}

</style>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.0.3.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://slidesjs.com/js/jquery.slides.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>
<script type="text/javascript">


$(function($){
	$("#logout").on("click", function(){
		$.cookie('autoPlug', 'true', { expires: -1, path: '/myapp/', secure: false })
		$.removeCookie('autoNo', { expires: -1, path: '/myapp/', secure: false })
	}) 
})
	function startPlan(sessionNo){	
		if(sessionNo == null){
			// 로그인 안 되어있을시 로그인 페이지로 이동
			action_do('login')	
		} else {
			// 로그인 되어있을시
			location.href="${pageContext.request.contextPath}/viewcont/startPlan.do";
		}
	}
	
	function showMyPlans(sessionNo) {
		if(sessionNo == null){
			// 로그인 안 되어있을시 로그인 페이지로 이동
			action_do('login')
		} else {
			// 로그인 되어있을시
			// TODO: 내 플랜 보기 코드 수행
		}
	}
	
	
	$(function(){
		$("#slides").slidesjs({
			width: 300, //넓이
			height: 200, //높이
			
			start: 3, //시작 사진 번호
			navigation: {
				active: false,
				//네비게이션 사용 유무(이전 다음 보기 버튼)
				effect: "slide" 
				//이전 다음 버튼 눌렀을때 효과 슬라이드(slide) 페이드효과(fade)
				//<a href="#" class="slidesjs-previous slidesjs-navigation">이전</a>
			    //<a href="#" class="slidesjs-next slidesjs-navigation">다음</a>
				//false 후 이런식으로 넣으면 커스터마이징 가능함

			},
			pagination: {
			  active: true, //페이징
			  effect: "slide" //숫자 눌렀을때 효과 슬라이드(slide) 페이드효과(fade)
			  //css slidesjs-pagination 이부분 수정으로 커스터마이징 가능함
			},
			play: {
				active: true, //플레이 스탑버튼 사용유무(버튼변경불가)
				effect: "slide",//효과 slide, fade
				interval: 2000,//밀리세컨드 단위 5000 이면 5초
				auto: true, //시작시 자동 재생 사용유무
				swap: true, //플레이스 스탑버튼 둘다보임 false, 하나로 보임 true
				pauseOnHover: false,//마우스 올렸을때 슬라이드 멈춤할껀지 말껀지
				restartDelay: 2500//마우스 올렸다가 벗어 났을때 재 작동 시간 밀리세컨드 단위
				//css slidesjs-play, slidesjs-stop 이부분을 이용해서 커스터마이징 가능함
			},

			effect: {
				slide: {
				// 슬라이드 효과
					speed: 800
					// 0.2초만에 바뀜
				},
				fade: {
				// 페이드 효과
					speed: 400,
					// 0.3초만에 바뀜
					crossfade: true
					// 다음이미지와 겹쳐서 나타남 유무
				}
			},


			callback: {
				loaded: function(number) {
				//처음 화면 로드될때 번호
				//	alert('loaded : ' + number);
				 $('#slidesjs-slide-number').text(number);
				},
				start: function(number) {
				//변경전 사진번호
				//	alert('start : ' + number);
				},
				complete: function(number) {
				//변경후 사진번호
				// 	alert('complete : ' + number);
				 $('#slidesjs-slide-number').text(number);
				}
			}

		});
	});
	
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<input type="button" value="플래너 시작하기" onclick="startPlan(${no})">
	<input type="button" value="내가세운 계획 보기" onclick="showMyPlans(${no})">
	<br>
	
	 
 <div id="slides" style="width: 40%;height: 40%">
    <img src="http://i.imgur.com/yUrVqoB.jpg"><!--1번 사진-->
    <img src="http://i.imgur.com/yUrVqoB.jpg"><!--2번 사진-->
    <img src="http://i.imgur.com/yUrVqoB.jpg"><!--3번 사진-->
    <img src="http://i.imgur.com/yUrVqoB.jpg"><!--4번 사진-->
  </div>
  <div id="slidesjs-log"></div>

<div style="border: 0px; float: left; width: 250px; padding:20px;">
  <table border="1" >
      <tr>
          <td colspan="2" height="160px" width="250px" >지도</td>
      </tr>
      <tr>
          <td colspan="2">봉기의 서울나들이</td>
      </tr>
      <tr>
          <td>3일간</td><td>2016년 9월</td>
      </tr>
  </table>
</div>
<div style="border: 0px; float: left; width: 250px; padding:20px;">
  <table border="1" >
      <tr>
          <td colspan="2" height="160px" width="250px" >지도</td>
      </tr>
      <tr>
          <td colspan="2">봉기의 서울나들이</td>
      </tr>
      <tr>
          <td>3일간</td><td>2016년 9월</td>
      </tr>
  </table>
</div>
<div style="border: 0px; float: left; width: 250px; padding:20px;">
  <table border="1" >
      <tr>
          <td colspan="2" height="160px" width="250px" >지도</td>
      </tr>
      <tr>
          <td colspan="2">봉기의 서울나들이</td>
      </tr>
      <tr>
          <td>3일간</td><td>2016년 9월</td>
      </tr>
  </table>
</div>
<div style="border: 0px; float: left; width: 250px; padding:20px;">
  <table border="1" >
      <tr>
          <td colspan="2" height="160px" width="250px" >지도</td>
      </tr>
      <tr>
          <td colspan="2">봉기의 서울나들이</td>
      </tr>
      <tr>
          <td>3일간</td><td>2016년 9월</td>
      </tr>
  </table>
</div>


</body>
</html>