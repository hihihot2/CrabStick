<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	function id_chk(){
		var mem_id = document.joinform.mem_id.value;
		var params = "mem_id="+document.joinform.mem_id.value;		
		if(mem_id == ""){
			alert('아이디를 입력하세요')
		}else{
			sendRequest("${pageContext.request.contextPath}/logincont/idchk.do", params, id_chkresult, 'POST')	
		}		
	}
	function id_chkresult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				var rep = eval("(" + str + ")");
				if(rep==0){
					document.getElementById("checkid").innerHTML = "<font color=blue>사용 가능한 아이디</font>"
					document.joinform.chk.value="1";
				}else{
					document.getElementById("checkid").innerHTML = "<font color=red>사용 불가능한 아이디</font>"
				}
			}
		}
	}
	function join_do() {
		var chk = document.joinform.chk.value;
		var pwd = document.joinform.mem_pwd.value;
		var pwdchk = document.joinform.mem_pwdchk.value;
		if(chk == 0){
			alert('아이디 중복체크 하세요')
		}else if(pwd!=pwdchk){
			alert('비밀번호가 일치하지 않습니다.')		
		}else{
			document.joinform.action="${pageContext.request.contextPath}/logincont/join.do";
			document.joinform.submit();
		}		
	}
	function cancle_do(){
		document.joinform.action = "${pageContext.request.contextPath}/";
		document.joinform.submit();	
	}
</script>
<title>Insert title here</title>
</head>
<body>
<form name="joinform" method="post">
<input type="hidden" name="chk" value="0">
아이디 : <input type="text" name="mem_id" placeholder="이메일주소를 입력하세요" onkeyup="id_chk()"> <input type="button" value="중복확인" onclick="id_chk()"><div id="checkid"></div><br>
비밀번호 : <input type="password" name="mem_pwd"><br>
비밀번호확인 : <input type="password" name="mem_pwdchk"><br>
이름 : <input type="text" name="mem_name"><br>
<input type="button" value="완료" onclick="join_do()"><input type="button" value="취소" onclick="cancle_do()">
</form>



</body>
</html>