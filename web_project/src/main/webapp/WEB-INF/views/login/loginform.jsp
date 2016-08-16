
<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="../top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

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
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$("#cb_saveId").on("click", function() {
			var _this = this;
			var isRemember;
			if ($("#userid").val() == "" || $("#userpw").val() == "") {
				alert("먼저 아이디와 패스워드를 입력하세요")
				$(_this).attr("checked", false);
			} else {
				if ($(_this).is(":checked")) {
					isRemember = confirm("이 PC에 로그인 정보를 저장하시겠습니까? PC방등의 공공장소에서는 개인정보가 유출될 수 있으니 주의해주십시오.");
					if (!isRemember) {
						$(_this).attr("checked", false);
					} 
				}
			}
		});
		$("#loginbtn").on("click", function(){
			if($("#cb_saveId").is(":checked")){
				$.cookie('autoPlug', 'true', { expires: 7, path: '/myapp/', secure: false })
			} else {
				$.cookie('autoPlug', 'true', { expires: -1, path: '/myapp/', secure: false })
			}
		})
	});
	
	
	function login_do() {
		var id = document.loginform.mem_id.value
		var pwd = document.loginform.mem_pwd.value

		if (id == "") {
			alert('아이디를 입력해주세요')
		} else if (pwd == "") {
			alert('비밀번호를 입력해주세요')
		} else {
			if (document.loginform.always_login.checked) {
				document.loginform.always_login.value = "auto"
			} else {
				document.loginform.always_login.value = ""
			}
			var id =  encodeURIComponent(document.loginform.mem_id.value);
			var params = "mem_id=" + id
					+ "&mem_pwd=" + document.loginform.mem_pwd.value
					+ "&always_login=" + document.loginform.always_login.value;
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
					var isPlanpage = document.loginform.gotoPlan.value
					if(isPlanpage=="1"){
						document.loginform.action = "${pageContext.request.contextPath}/viewcont/startPlan.do";						
					} else if(isPlanpage=="2"){
						document.loginform.action = "${pageContext.request.contextPath}/viewcont/showMyPlan.do";
					} else {
						document.loginform.action = "${pageContext.request.contextPath}/";
					}
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
.lgcss {
	margin-top: 10%;
	margin-left: 30%;
	margin-right: 30%;
	width: 40%;
	min-width: 300px;
	min-height: 500px;
}
</style>
<body>


	<form name="loginform" method="post" class="lgcss">

		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>WELCOME</h1>
			</div>

			<div class="panel-body">


				<input type="text" class="form-control"
					placeholder="Enter Your E-mail address" name="mem_id" id="userid"
					required onkeydown="keyevent()"> <br> <input
					type="password" class="form-control"
					placeholder="Enter Your Password" name="mem_pwd" id="userpw"
					required onkeydown="keyevent()"> <br> <input
					type="button" class="btn btn-info" value="로그인" onclick="login_do()"
					name="loginBtn" id="loginbtn" onkeypress=""> <input
					type="checkbox" name="always_login" id="cb_saveId"
					style="margin-left: 2%"> Remember me<br>
			</div>

			<div class="panel-footer">
				<p>
					<a href="javascript:find_pass()">비밀번호를 잊어버리셨나욤?</a>
				</p>
				<p>
					계정이 없으세요? <a
						href='${pageContext.request.contextPath}/survey/main.do?sw=0'>가입하기»</a>
				</p>
			</div>
		</div>

		<input type="hidden" value="${sw }" name="gotoPlan">
	</form>

</body>
</html>