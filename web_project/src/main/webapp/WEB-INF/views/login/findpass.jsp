<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	function find_pass(){
		var mem_id = document.findpass.mem_id.value;
		var mem_name = document.findpass.mem_name.value;
		var params = "mem_id="+document.findpass.mem_id.value+"&mem_name="+document.findpass.mem_name.value;		
		if(mem_id == ""){
			alert('아이디를 입력하세요')
		} else if(mem_name == ""){
			alert('이름을 입력하세요')
		} else{
			sendRequest("${pageContext.request.contextPath}/logincont/searchpass.do", params, pass_findresult, 'POST')	
		}		
	}
	function pass_findresult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var rep = eval("(" + str + ")");
				if(rep == 0){
					document.getElementById("findpass").innerHTML = "<font color=red>사용자의 비밀번호를 찾을 수 없습니다 </font>"
				} else {
					document.getElementById("findpass").innerHTML = "<font color=blue>사용자의 비밀번호가 이메일로 전송되었습니다.</font>"
				}
			}
		}
	}
	function send_pass(){
		document.findpass.action = "${pageContext.request.contextPath}/emailCont/mail.do";
		document.findpass.submit();	
	}
 	function cancle_do(){
		document.findpass.action = "${pageContext.request.contextPath}/";
		document.findpass.submit();	
	} 
	
</script>
<title>Insert title here</title>
</head>
<body>
<form name="findpass" method="post">
아이디 : <input type="text" name="mem_id" placeholder="이메일주소를 입력하세요"> <br>
이름 : <input type="text" name="mem_name" placeholder="이름을 입력하세요"><br>
<input type="button" value="찾기" onclick="find_pass()">
<!-- <input type="button" value="취소" onclick="cancle_do()"> -->
<input type="button" value="보내기" onclick="send_pass()">
<div id="findpass"></div>
</form>



</body>
</html>