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
		
		var params = "mem_id=" + document.loginform.mem_id.value+"@"+document.loginform.email2.value + "&mem_pwd="+ document.loginform.mem_pwd.value;
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
	function checkemailaddy() {
		var mail = document.loginform.email_select.value;

		if (loginform.email_select.value == 'self') {
			loginform.email2.readOnly = false;
			loginform.email2.value = '';
			loginform.email2.focus();
		} else {
			loginform.email2.readOnly = "readonly";
			if (mail == 0) {
				document.loginform.email2.value = ""
			} else {
				loginform.email2.value = loginform.email_select.value;

			}
		}
	}
	
</script>


<title>Insert title here</title>
</head>
<body>


<form name="loginform" method="post">
id : <input type="text" name="mem_id" onkeydown="keyevent()">@<input name="email2" type="text" class="box" id="email2" size="20" readonly="readonly">
<select name="email_select" id="email_select" onChange="checkemailaddy()">
 <option value="0">선택하세요</option>
 <option value="naver.com">naver.com</option>
 <option value="daum.com">daum.com</option>
 <option value="google.com">google.com</option>
 <option value="yahoo.com">yahoo.com</option>
 <option value="self">직접입력</option>
</select><br>


password : <input type="password" name="mem_pwd" onkeydown="keyevent()"><br>
<input type="button" value="로그인" onclick="login_do()" onkeypress=""><input type="button" value="cancel" onclick="cancel_do()"> <input type="button" value="회원가입" onclick="join_go()"> <input type="button" value="회원비밀번호 찾기" onclick="find_pass()">
</form> 
</body>
</html>