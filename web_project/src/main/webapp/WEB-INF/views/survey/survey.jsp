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

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>고객 선호도 조사</title>
<style>
div {
	background-color: #5882FA;
	color: white;
	padding: 25px;
	text-align: center;
}

div:hover {
	background-color: #FE2E2E;
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

				<h1>설문 시작</h1>
				<div onclick="start_()">측정하러 가기</div>
				<input type="hidden" name="survey_Level">
				<input type="hidden" name="survey_Answer">
			</c:when>

			<c:when test="${level eq 1}">
				<h1>Q1. 주 여행 목적은 무엇입니까?</h1>
				<table>
					<tbody>
						<tr>
							<td><div onclick="Question_('지역 문화 탐방')">지역 문화 탐방</div></td>
							<td><div onclick="Question_('지역 음식 체험')">지역 음식 체험</div></td>
							<td><div onclick="Question_('쇼핑')">쇼핑</div></td>
							<td><div onclick="Question_('휴식')">휴식</div></td>

						</tr>
					</tbody>
				</table>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


			<c:when test="${level eq 2}">
				<h1>Q2. 주로 누구와 여행을 즐기십니까?</h1>
				<table>
					<tbody>
						<tr>
							<td><div onclick="Question_('혼자')">혼자</div></td>
							<td><div onclick="Question_('가족')">가족</div></td>
							<td><div onclick="Question_('친구')">친구</div></td>
							<td><div onclick="Question_('연인')">연인</div></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 3}">
				<h1>Q3. 주로 어느 지역에 여행 다니는 것을 선호합니까?</h1>
				<table>
					<tbody>
						<tr>
							<td><div onclick="Question_('대도시')">대도시</div></td>
							<td><div onclick="Question_('유적지')">역사 문화 유적지</div></td>
							<td><div onclick="Question_('자연경관')">자연경관</div></td>
							<td><div onclick="Question_('상관없음')">상관 없음</div></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>

			<c:when test="${level eq 4}">
				<h1>Q4. 하루 평균 얼마 정도의 여행 경비를 선호합니까?</h1>
				<table>
					<tbody>
						<tr>
							<td><input type="text" name="cost" placeholder="금액" /></td>
						</tr>
						<tr>
							<td><input type="button" onclick="Question_2()"
								value="입력 완료" /></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="survey_Level" value="${level}">
				<input type="hidden" name="survey_Answer" value="${answer}">
			</c:when>


		</c:choose>
	</form>

</body>


</html>