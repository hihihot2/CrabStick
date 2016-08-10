
<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="../top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js"
	type="text/javascript"></script>
<script type="text/javascript">

	function detailView(loc_name) {
		window.open(
				"${pageContext.request.contextPath}/viewCont/detailDOC.do?loc_name="
						+ loc_name, "_blank", "width=800, height=800")
	}
	
	function search_loc() {
		var loc_name = document.searchform.locSearch.value;
		var params = "loc_name=" + loc_name;
		sendRequest("${pageContext.request.contextPath}/viewCont/search.do",
				params, search_result, 'POST');
	}

	function search_result() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var o = eval("(" + str + ")");
				var myDiv = document.getElementById("resultView");
				var html = "<table border='0'><tr><th>   no   </th>"
						+ "<th>    name    </th></tr>";
				for (i = 0; i < o.length; i++) {
					html += "<tr>";
					html += "<td>" + o[i].num + "</td>";
					html += "<td>";
					html += "<a href='${pageContext.request.contextPath }"
							+ "/placeCont/getRestaurants.do?city_latitude="
							+ o[i].lati + "&city_longitude=" + o[i].long2
							+ "&cityno=" + o[i].num + "&city_code=" + o[i].code 
							+ "&city_siguncode=" + o[i].siguncode + "'>" + o[i].name
							+ "</a></td>"
					html += "</tr>";
				}
				html += "</table>";
				myDiv.innerHTML = html;
			}
		}
	}

	function select_loc(latitude, longitude, locno, code, siguncode) {
		location.href = "${pageContext.request.contextPath}/placeCont/showMap.do?city_latitude="
				+ latitude
				+ "&city_longitude="
				+ longitude
				+ "&cityno="
				+ locno + "&city_code=" + code + "&city_siguncode=" + siguncode;
	}
	
</script>
<style type="text/css">
.dropdown {
	position: inherit;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: relative;
	background-color: white;
	width: 100%
}

.dropdown:hover .dropdown-content {
	display: block;
}

.desc {
	padding: 15px;
	text-align: center;
}
</style>
</head>
<body>


	<div class="container-fluid text-center">
		<br> <br> <br>
		<h2>SEARCH you want to visit city</h2>
		<form name="searchform">
		<div class="form-group input-group">
		
			<input type="text" class="form-control" name="locSearch" 
			placeholder="원하는 도시명을 검색하세요" onkeyup="search_loc()"> 
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" name="searchBtn" onclick="search_loc()">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
		
			<br>
			<div id="resultView"></div>
		</form>


		<br> <br> <br>
		<h4>here is what we recommend</h4>
		<br>

		<form name="imageform">
			<div class="row">
				<c:forEach var="List" items="${city_List}">
					<div class="col-sm-4">
						<img id="${List.loc_no}"
							src="${pageContext.request.contextPath}/resources/png/city/seoul.png"
							height="300px" width="100%" onclick="detailView('${List.loc_name}')">
						<h4>
							<a
								href="javascript:select_loc('${List.loc_lati}','${List.loc_long}','${List.loc_no}','${List.loc_code}','${List.loc_siguncode}')">${List.loc_name}</a>
						</h4>
						<p>사진을 클릭하면</p>
						<p style="color: #ff0000">${List.loc_name}</p>
						<p>에 대해 더 알 수 있어요!</p>

					</div>

				</c:forEach>


			</div>
		</form>
	</div>

</body>
</html>