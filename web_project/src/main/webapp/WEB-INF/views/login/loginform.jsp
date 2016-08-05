<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){
$("#cb_saveId").on("click", function(){
	var _this = this;
	var isRemember;
	if($("#userid").val()=="" || $("#userpw").val()=="" ){
		alert("먼저 아이디와 패스워드를 입력하세요")
		$(_this).attr("checked", false);
	} else {
		if($(_this).is(":checked")) {
			isRemember = confirm("이 PC에 로그인 정보를 저장하시겠습니까? PC방등의 공공장소에서는 개인정보가 유출될 수 있으니 주의해주십시오.");
			if(!isRemember){    
				$(_this).attr("checked", false);
			} else {
				$.cookie('autoID', $("#userid").val(),  { expires: 7, path: '/', secure: false })
				$.cookie('autoPW', $("#userpw").val(), { expires: 7, path: '/', secure: false })
				$.cookie('autoPlug', 'true', { expires: 7, path: '/', secure: false })
			}
		} 
	}
});



});
	function login_do() {
		var id = document.loginform.mem_id.value
		var pwd = document.loginform.mem_pwd.value

		if (id == "") {
			alert('아이디를 입력해주세요')
		} else if (pwd == "") {
			alert('비밀번호를 입력해주세요')
		} else {
			/* if(document.loginform.always_login.checked){
				document.loginform.always_login.value = "auto"
			} else {
				document.loginform.always_login.value = ""
			} */
			var params = "mem_id=" + document.loginform.mem_id.value
					+ "&mem_pwd=" + document.loginform.mem_pwd.value
					/* + "&always_login=" + document.loginform.always_login.value; */
			sendRequest(
					"${pageContext.request.contextPath}/logincont/login.do",
					params, login_result, "POST");
		}
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

	function cancel_do() {
		document.loginform.action = "${pageContext.request.contextPath}/";
		document.loginform.submit();
	}
	function find_pass() {
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
<style>
form {
	margin: auto;
	height: 40%;
	width: 30%;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
	float: right;
}

.container {
	padding: 16px;
}

span.psw {
	float: left;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
<body>
	<h1 align="center">WELCOME</h1>

	<form name="loginform" method="post" style="background-color: #f1f1f1">

		<div class="container">
			<input type="text" placeholder="Enter Your E-mail address" name="mem_id" id="userid" required onkeydown="keyevent()"> 
			<input type="password" placeholder="Enter Your Password" name="mem_pwd" id="userpw" required onkeydown="keyevent()"> 
			<input type="button" value="로그인" onclick="login_do()" name="loginBtn" id = "loginbtn" onkeypress="">  
			<input type="checkbox" name="always_login" id="cb_saveId"> Remember me<br>
			<p class="psw"><a href="javascript:find_pass()">비밀번호를 잊어버리셨나욤?</a></p>
			<p>계정이 없으세요? <a href='${pageContext.request.contextPath}/logincont/joinpage.do'>가입하기 »</a></p>
		</div>

	</form>
</body>
</html>