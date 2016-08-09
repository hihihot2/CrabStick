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

<script type="text/javascript">
	function id_chk() {
		var params = "mem_id=" + document.joinform.mem_id.value;
		sendRequest("${pageContext.request.contextPath}/logincont/idchk.do",
				params, id_chkresult, 'POST')

	}

	function id_chkresult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var rep = eval("(" + str + ")");
				if (rep == 0) {
					document.getElementById("checkid").innerHTML = "<font color=blue>사용 가능한 아이디</font>"
					document.joinform.chk.value = "1";
				} else {
					document.getElementById("checkid").innerHTML = "<font color=red>사용 불가능한 아이디</font>"
				}
			}
		}
	}

	function join_do() {
		var chk = document.joinform.chk.value;
		var pwd = document.joinform.mem_pwd.value;
		var name = document.joinform.mem_name.value;
		var pwdlength = pwd.length
		//아이디 중복체크 여부확인 chk값이  1이여야함
		if (chk == 0) {
			alert('아이디 중복체크 하세요')
			//비밀번호가 공백일경우
		} else if (name == "") {
			alert('이름을 입력해주세요')
			//select된 mail주소가 self(직접입력)일경우	
		} else if (pwd == "") {
			alert('비밀번호를 설정해주세요')
			//select된 mail주소가 self(직접입력)일경우	
		} else if (5 >= pwdlength) {
			alert("비밀번호는 6자리 이상으로 설정해주세요")
		} else {
			var params = "mem_id=" + document.joinform.mem_id.value;
			var html = "<input type='text' name='certify'/> "
					+ "<input type='button' class='btn btn-info' value='인증하기' onclick='sumbit_cert()'/> </h1>"
					+ "<input type='hidden' name='load_num'>"
			document.getElementById("msgconfirm").innerHTML = "<h1 class='msgcss'>회원의 이메일주소로 인증번호를 전송하였습니다.</h1><br><h1 class='msgcss'> 인증번호 :\n"
					+ html
			sendRequest(
					"${pageContext.request.contextPath}/emailCont/sentMsg.do", params, chk_mail, 'POST')
		}
	}
	var result
	function chk_mail() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				result = eval("(" + str + ")");
				var html = "<input type='hidden' name='load_num' value= "+ result +" >"
				document.getElementById("msgconfirm").innerHTML += html
			}
		}
	}

	function sumbit_cert() {
		var cert = document.joinform.certify.value
		if(cert == result){
			document.joinform.action = "${pageContext.request.contextPath}/logincont/join.do";
			document.joinform.submit();
		} else {
			alert('번호가 다릅니다.')
		}
	}

	function cancle_do() {
		document.joinform.action = "${pageContext.request.contextPath}/";
		document.joinform.submit();
	}
</script>

<title>Insert title here</title>
<style>
.lgcss {
	margin-top: 5%;
	margin-left: 30%;
	margin-right: 30%;
	width: 40%;
}

.joincss {
	font-size: 29px !important;
}

.msgcss {
	font-size: 21px !important;
}
</style>
</head>
<body>

	<form name="joinform" method="post" class="lgcss">
		<input type="hidden" name="chk" value="0">

		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1 class='joincss'>가입하시면 더 많은 정보를 얻을 수 있습니다.</h1>
			</div>

			<div class="panel-body">
				<input type="hidden" name="chk" value="0"> <label
					for="mem_id">사용할 아이디 :</label> <input type="text"
					class="form-control" name="mem_id" placeholder="이메일주소를 입력하세요"
					onkeyup="id_chk()">
				<div id="checkid"></div>

				<br> <label for="mem_pwd">사용할 비밀번호 :</label> <input
					type="password" class="form-control" name="mem_pwd"
					placeholder="비밀번호를 입력하세요"> <br> <label for="mem_name">이름
					:</label> <input type="text" class="form-control" name="mem_name"
					placeholder="이름을 입력하세요"> <br> <input type="hidden"
					name="mem_survey" value="${survey_Answer}">

			</div>

			<div class="panel-footer">
				<input type="button" class="btn btn-info" value="완료"
					onclick="join_do()">
				<div id="msgconfirm">
				</div>
			</div>
		</div>

	</form>

</body>
</html>