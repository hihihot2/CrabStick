<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
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
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<input type="button" value="플래너 시작하기" onclick="startPlan(${no})">
	<input type="button" value="내가세운 계획 보기" onclick="showMyPlans(${no})">
	<br>

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