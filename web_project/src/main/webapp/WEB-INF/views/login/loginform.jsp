<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	function login_do() {
		var params = "mem_id=" + document.loginform.mem_id.value + "&mem_pwd="+ document.loginform.mem_pwd.value;
		sendRequest("${pageContext.request.contextPath}/logincont/login.do", params, login_result, "POST");
	}
	function login_result() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var rep = eval("(" + str + ")");
				if (rep == 0) {
					alert("아이디나 패스워드가 일치하지 않습니다.")
				} else {
					document.loginform.action = "${pageContext.request.contextPath}/";
					document.loginform.submit();
				}
			}
		}
	}
	function join_go() {
		document.loginform.action = "${pageContext.request.contextPath}/logincont/joinpage.do";
		document.loginform.submit();
	}
	function cancel_do(){
		document.loginform.action = "${pageContext.request.contextPath}/";
		document.loginform.submit();
	}
	function find_pass(){
		document.loginform.action = "${pageContext.request.contextPath}/logincont/findpass.do";
		document.loginform.submit();
	}
	
	function keyevent() {
		if (event.keyCode == 13) {
			login_do();
		}
	}
	
</script>


<title>Insert title here</title>
</head>
<body>


<form name="loginform" method="post">
id : <input type="text" name="mem_id" onkeydown="keyevent()">
password : <input type="password" name="mem_pwd" onkeydown="keyevent()"><br>
<input type="button" value="로그인" onclick="login_do()" onkeypress=""><input type="button" value="cancel" onclick="cancel_do()"> <input type="button" value="회원가입" onclick="join_go()"> <input type="button" value="회원비밀번호 찾기" onclick="find_pass()">
</form> 
</body>
</html>