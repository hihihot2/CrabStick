<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function startPlan(){
		location.href="${pageContext.request.contextPath}/viewcont/startPlan.do";
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<input type="button" value="플래너 시작하기" onclick="startPlan()">
	<input type="button" value="내가세운 계획 보기" onclick="viewPlan()">
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