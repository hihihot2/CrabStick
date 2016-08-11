
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
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#locSearch').autocomplete({
			source : function(request, response) {
				var params = "loc_name=" + encodeURIComponent(request.term);
				$.ajax({
					url : '../viewCont/search.do?' + params,
					type : 'POST',
					success : function(loc_name) {
						var search = eval("(" + loc_name + ")");
						var input = new Array();
						input = [];
						for (var i = 0; i < search.length; i++) {
							var before = search[i].name;
							input.push(before);
						}
						searchList = input;
						response(input);
					}
				});
			},
			select : function(event, ui) {
				if (event.keyCode == 13) {
					$('locSearch').val(ui.item.value);
				}
			}
		})
	})

	function select_loc(latitude, longitude, locno) {
		location.href = "${pageContext.request.contextPath}/placeCont/showMap.do?city_latitude="
				+ latitude
				+ "&city_longitude="
				+ longitude
				+ "&cityno="
				+ locno;
	}
	/* 
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
							+ "&city_siguncode=" + o[i].siguncode + "'>"
							+ o[i].name + "</a></td>"
					html += "</tr>";
				}
				html += "</table>";
				myDiv.innerHTML = html;
			}
		}
	} */

	function find_loc() {
		var loc_name = document.searchform.searchText.value
		if (loc_name == "") {
			alert('검색을 먼저하세요')
		} else {
			alert('검색한 장소로 이동합니다.')
			document.searchform.action = "${pageContext.request.contextPath}/viewCont/findCity.do?loc_name="
					+ loc_name;
			document.searchform.submit();
		}
	}

	function modal_open(loc_no, loc_commt) {

		var form = document.searchform;
		form.style.visibility = "hidden";

		var modal = document.getElementById('myModal');
		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById(loc_no);

		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		modal.style.display = "block";
		modalImg.src = img.src;
		modalImg.alt = img.alt;
		captionText.innerHTML = loc_commt;
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
			form.style.visibility = "visible";
		}
	}
</script>

<style type="text/css">
.myimg {
	cursor: pointer;
	transition: 0.3s;
}

.myimg:hover {
	opacity: 0.7;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}
/* The Modal (background) */

/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
}

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 150px;
}

/* The Close Button */
.close {
	position: absolute;
	top: 10%;
	right: 10%;
	color: white;
	font-size: 50px;
	font-weight: bold;
	transition: 0.3s;
}

.close:hover, .close:focus {
	color: gray;
	text-decoration: none;
	cursor: pointer;
}

/* The Close Button */
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

				<input type="text" class="form-control" id="locSearch"
					name="searchText" placeholder="원하는 도시명을 검색하세요"> <span
					class="input-group-btn">
					<button class="btn btn-default" type="button" name="searchBtn"
						onclick="find_loc()">
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
						<a
							href="javascript:select_loc('${List.loc_lati}','${List.loc_long}','${List.loc_no}')"><img
							id="${List.loc_no}" class="myimg" src="${List.loc_imgpath }"
							height="300px" width="100%" alt="${List.loc_name}"></a>
						<div class="center2">${List.loc_name}</div>
						<h4>
							<a
								href="javascript:modal_open('${List.loc_no}', '${List.loc_commt}')">${List.loc_name}</a>
						</h4>
						<p>사진을 클릭하면</p>
						<p style="color: #ff0000">${List.loc_name}</p>
						<p>에 대해 더 알 수 있어요!</p>

					</div>
				</c:forEach>
			</div>
			<!-- The Modal -->
			<div id="myModal" class="modal">
				<span class="close">X</span> <img class="modal-content" id="img01">
				<div id="caption"></div>
			</div>
			<!-- The Modal -->

		</form>
	</div>

</body>
</html>