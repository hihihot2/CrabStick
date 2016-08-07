<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=ej3ANIP8b0vPSY8tXHEG"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
	<script type="text/javascript">
	var searchList = [];
	function requestSearch(){
		var data = document.getElementById("autocomplete").value;
		
		var params = "data="+data;
		sendRequest("plancont/searchloc.do", params, setSearchPlace, 'POST');

		/*var params = "query="+data;
		sendSearchRequest("https://openapi.naver.com/v1/search/local.xml", params, setSearchPlace, 'GET');*/
	}

	function setSearchPlace(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var responseList = httpRequest.responseText;
				var search = eval("("+ responseList +")");
				
				for(var i = 0 ; i < search.length ; i++){
					searchList.push(search[i].title);
				}
			}else {
				alert("해당 브라우저에서 지원하는 기능이 아닙니다");
			}
		}
	}
	
	$(document).ready(function(){
		var contentEl = $('<input id="autocomplete" onkeyup="requestSearch()">')
		contentEl.appendTo(document.getElementById("div1"));
		$('#autocomplete').autocomplete({
			source: searchList
		})
	})
	
	</script>
</head>
<body>
<div id="div1"></div>

 
 <script type="text/javascript">
 
 </script>

</body>
</html>
