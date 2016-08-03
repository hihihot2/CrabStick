<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	function login_do() {
		var id = document.loginform.mem_id.value;
		var pwd = document.loginform.mem_pwd.value

		if (id == "") {
			alert('아이디를 입력해주세요')
		} else if (pwd == "") {
			alert('비밀번호를 입력해주세요')
		} else {
			var params = "mem_id=" + document.loginform.mem_id.value
					+ "&mem_pwd=" + document.loginform.mem_pwd.value;
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
	width: 60%;
	border: 3px solid #f1f1f1;
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
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
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


	<form name="loginform" method="post">



		<div class="container" style="background-color: #f0ffff">
			<h1>FUCK U!</h1>
			<input type="text" placeholder="Enter Your E-mail address"
				name="mem_id" required onkeydown="keyevent()"> <input
				type="password" placeholder="Enter Your Password" name="mem_pwd"
				required onkeydown="keyevent()"> <input type="button"
				value="로그인" onclick="login_do()" onkeypress=""> <input
				type="checkbox" checked="checked"> Remember me
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn" onclick="cancel_do">Cancel</button>
			<span class="psw"><a href="javascript:find_pass">비밀번호를
					잊어버리셨나욤?</a></span>
		</div>


	</form>
</body>
</html>