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
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 선호도 조사</title>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>

	<form method="POST" name="survey_form"
		action="${pageContext.request.contextPath }/survey/survey.do">

		<c:set value="${survey_Level}" var="level"></c:set>
		<c:set value="${survey_Answer}" var="answer"></c:set>
		
		<c:choose>
			<c:when test="${level eq null}">
				<div class="container">
					<h2>너무 많은 여행 정보때문에 피곤하셨죠?</h2>
					<h3>저희가 최적화시켜 맞춤형으로 추천해드립니다.</h3>
					<div class="btn-group">
						<button type="button" class="btn btn-primary" onclick="start_()">자신의
							여행 성향 측정하러 가기 >></button>
					</div>
				</div>
				<input type="hidden" name="survey_Level">
				<input type="hidden" name="survey_Answer">
			</c:when>

			<c:when test="${level eq 1}">

				<div class="container">
					<h2>Q1. 주 여행 목적은 무엇입니까?</h2>
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
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


			<c:when test="${level eq 2}">

				<div class="container">
					<h2>Q2. 주로 누구와 여행을 즐기십니까?</h2>
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
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 3}">

				<div class="container">
					<h2>Q3. 주로 어느 지역에 여행 다니는 것을 선호합니까?</h2>
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
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 4}">

				<div class="container">
					<h2>Q4. 하루 평균 얼마 정도의 여행 경비를 선호합니까?</h2>
					<input type="text" class="form-control" name="cost"> 만원
					<div class="btn-group">
						<button type="button" class="btn btn-primary"
							onclick="Question_2()">설문 종료 후 다음 페이지 이동</button>
					</div>

				</div>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


		</c:choose>
	</form>

</body>


</html>