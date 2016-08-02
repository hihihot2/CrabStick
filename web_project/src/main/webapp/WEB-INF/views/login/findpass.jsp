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
			sendRequest("${pageContext.request.contextPath}/emailCont/mail.do", params, pass_findresult, 'POST')	
		}		
	}
	function pass_findresult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var rep = eval("(" + str + ")");
				var html = "<input type='text' name='certify'/> "+
				           "<input type='button' value='인증하기' onclick='sumbit_cert()'/>"
				if(rep == 0){
					document.getElementById("resultpass").innerHTML = 
						"<font color=red>사용자의 비밀번호를 찾을 수 없습니다 </font>"
				} else {
					document.getElementById("resultpass").innerHTML =
						"<font color=red>사용자의 비밀번호를 찾았당</font><br>" + html
				}
			}
		}
	}
	function sumbit_cert(){
		document.findpass.action = "${pageContext.request.contextPath}/emailCont/chkPass.do";
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
<input type="button" value="취소" onclick="cancle_do()">
<input type="button" value="보내기" onclick="find_pass()">
<div id="resultpass"></div>
</form>

</body>
</html>