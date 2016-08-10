
<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="../top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>

<script type="text/javascript">
	function start_() {
		var form = document.survey_form;
		form.survey_Level.value = 1;
		form.survey_Answer.value = "";
		form.submit();
	}

	function Question_(value) {
		var form = document.survey_form;

		if (form.survey_Level.value == "1") {

			form.survey_Level.value = 2;
			form.survey_Answer.value += value + ":";

		} else if (form.survey_Level.value == "2") {

			form.survey_Level.value = 3;
			form.survey_Answer.value += value + ":";

		} else if (form.survey_Level.value == "3") {

			form.survey_Level.value = 4;
			form.survey_Answer.value += value + ":";

		}

		form.submit();
	}

	function Question_2() {
		var form = document.survey_form;
		if (form.cost.value == "") {
			alert("원하시는 여행 경비를 입력하세요!")
			return false;
		} else {
			form.survey_Level.value = "finish";
			form.survey_Answer.value += form.cost.value;
			form.submit();
		}
	}
	function Question_3() {
		var form = document.survey_form;
		if (form.cost.value == "") {
			alert("원하시는 여행 경비를 입력하세요!")
			return false;
		} else {
			form.survey_Level.value = "finish";
			form.survey_Answer.value += form.cost.value;
			form.submit();
		}
	}
	
	$(document).ready(function() {
		$('input#cost').keydown(function(event) {
			var keyId = event.keyCode;
			if( (keyId >= 48 && keyId <= 57) || (keyId >= 96 && keyId <= 105) || keyId == 8 || keyId == 46 || keyId == 37 || keyId == 39 ) {
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
	})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 선호도 조사</title>
<meta charset="utf-8">
<style type="text/css">
.unact:hover {
	background-color: #2ECCFA !important;
	border-style: none;
}

.txtform {
	width: 30%;
}
</style>
</head>
<body>

	<form method="POST" name="survey_form"
		action="${pageContext.request.contextPath }/survey/survey.do">

		<c:set value="${survey_Level}" var="level"></c:set>
		<c:set value="${survey_Answer}" var="answer"></c:set>

		<c:choose>
			<c:when test="${level eq null}">

				<div class="jumbotron text-center"
					style="background-color: #2ECCFA !important">
					<ul class="nav nav-tabs">
						<li class="active"><a>01 추천 시스템이란?</a></li>
						<li><a class="unact">02 설문</a></li>
						<li><a class="unact">03 가입 양식</a></li>

					</ul>
					<div class="tab-content">
						<h2>저희 OOO 추천 시스템은</h2>
						<h2>간단한 성향 판단을 통해서 보다 편리하게 여행 계획을</h2>
						<h2>수립하실 수 있도록 맞춤형 선택지를 추천해드립니다.</h2>
						<br>
						<div class="btn-group">
							<button type="button" class="btn btn-primary" onclick="start_()">자신의
								여행 성향 측정하러 가기 >></button>
						</div>
					</div>
				</div>
				<input type="hidden" name="survey_Level">
				<input type="hidden" name="survey_Answer">
			</c:when>

			<c:when test="${level eq 1}">
				<div class="jumbotron text-center"
					style="background-color: #2ECCFA !important">
					<ul class="nav nav-tabs">
						<li><a class="unact">01 추천 시스템이란?</a></li>
						<li class="active"><a>02 설문</a></li>
						<li><a class="unact">03 가입 양식</a></li>
					</ul>
					<div class="tab-content">
						<h2>Q1. 주 여행 목적은 무엇입니까?</h2>
						

						<br>
						<div class="btn-group">
							<button type="button" class="btn btn-primary"
								onclick="Question_('지역 문화 탐방')">지역 문화 탐방</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('지역 음식 체험')">지역 음식 체험</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('쇼핑')">쇼핑</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('휴식')">휴식</button>
						</div>
					</div>
				</div>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


			<c:when test="${level eq 2}">
				<div class="jumbotron text-center"
					style="background-color: #2ECCFA !important">
					<ul class="nav nav-tabs">
						<li><a class="unact">01 추천 시스템이란?</a></li>
						<li class="active"><a>02 설문</a></li>
						<li><a class="unact">03 가입 양식</a></li>
					</ul>
					<div class="tab-content">
						<h2>Q2. 주로 누구와 여행을 즐기십니까?</h2>
						<br>
						<div class="btn-group">
							<button type="button" class="btn btn-primary"
								onclick="Question_('혼자')">혼자</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('가족')">가족</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('친구')">친구</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('연인')">연인</button>
						</div>
					</div>
				</div>

				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 3}">
				<div class="jumbotron text-center"
					style="background-color: #2ECCFA !important">
					<ul class="nav nav-tabs">
						<li><a class="unact">01 추천 시스템이란?</a></li>
						<li class="active"><a>02 설문</a></li>
						<li><a class="unact">03 가입 양식</a></li>
					</ul>
					<div class="tab-content">
						<h2>Q3. 주로 어느 지역에 여행 다니는 것을 선호합니까?</h2>
						<br>
						<div class="btn-group">
							<button type="button" class="btn btn-primary"
								onclick="Question_('대도시')">대도시</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('유적지')">역사 문화 유적지</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('자연경관')">자연경관</button>
							<button type="button" class="btn btn-primary"
								onclick="Question_('상관없음')">상관 없음</button>
						</div>
					</div>
				</div>

				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 4}">
				<div class="jumbotron text-center"
					style="background-color: #2ECCFA !important">
					<ul class="nav nav-tabs">
						<li><a class="unact">01 추천 시스템이란?</a></li>
						<li class="active"><a>02 설문</a></li>
						<li><a class="unact">03 가입 양식</a></li>
					</ul>
					<div class="tab-content">
						<h2>Q4. 하루 평균 얼마 정도의 여행 경비를 선호합니까?</h2>
						<h3>예시> 10만원 입력 시 '10' 으로 입력</h3>
						<br>
						<input type="text" id="cost" style="color: #000000"> 만 (KRW)
						<br>
						<br>
						<div class="btn-group">
						<c:if test="${no ne '-1'}">
							<button type="button" class="btn btn-primary"
							onclick="Question_3()">회원 설문수정</button>
						</c:if>
						<c:if test="${no eq '-1'}">
							<button type="button" class="btn btn-primary"
							onclick="Question_2()">설문 종료 후 다음 페이지 이동</button>
						</c:if>
					</div>
					</div>
				</div>
				
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


		</c:choose>
		<input type="hidden" name="switch" value="${no }">
	</form>

</body>


</html>