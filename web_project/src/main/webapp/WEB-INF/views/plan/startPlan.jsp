<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>
<script type="text/javascript">

	function detailView(loc_name){
		window.open("${pageContext.request.contextPath}/viewCont/detailDOC.do?loc_name=" + loc_name, "_blank", "width=800, height=800")
	}
	function search_loc(){
		var loc_name = document.searchform.locSearch.value;
		var params = "loc_name=" + loc_name;
		sendRequest("${pageContext.request.contextPath}/viewCont/search.do", params, search_result, 'POST');
	}
	
	function search_result(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var myDiv = document.getElementById("resultView");
				var html = "<table border='1'><tr><th>no</th><th>name</th></tr>"
					for(i=0;i<o.length;i++){
						html += "<tr>";
						html += "<td>"+o[i].num+"</td>";
						html += "<td>";
						html += "<a href='${pageContext.request.contextPath }"
						+"/placeCont/getRestaurants.do?city_latitude="
						+o[i].lati+"&city_longitude="+o[i].long2+"&cityno="+o[i].num+"'>" + o[i].name + "</a></td>"
						html += "</tr>";
					}
					html += "</table>";
					myDiv.innerHTML = html;
			}
		}
	}
	
	function keyevent() {
		if (event.keyCode == 13) {
			search_loc();
		}
	}

	function select_loc(latitude, longitude, locno, code, siguncode) {
		location.href = "${pageContext.request.contextPath}/placeCont/showMap.do?city_latitude="
				+latitude+"&city_longitude="+longitude+"&cityno="+locno+"&city_code="+code+"&city_siguncode="+siguncode;
	}
	
</script>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>
	<h3>회원이 좋아하실만한 여행을 5가지 뽑아봤어요~</h3>
	<br>
	<c:forEach var="List" items="${city_List}">
		<div style="border: 0px; float: left; width: 250px; padding: 20px;">
			<table border="1">
				<tr>
					<td colspan="2" height="160px" width="250px">
					<div id = "div1">
						<img src="http://i.imgur.com/qzRNO6m.png" height="160px" width="250px" onclick="detailView('${List.loc_name}')">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"
						onclick="select_loc('${List.loc_lati}','${List.loc_long}','${List.loc_no}','${List.loc_code}','${List.loc_siguncode}')">${List.loc_name}</td>
				</tr>
				<tr>
					<td>${List.loc_no}</td>
					<td>2016년 9월</td>
				</tr>
			</table>
		</div> 
	</c:forEach>
	<br>
	
	<form name="searchform">
		<input type="text" name="locSearch" onkeyup="search_loc()" />
		<div id="resultView"></div>
	</form>
</body>
</html>