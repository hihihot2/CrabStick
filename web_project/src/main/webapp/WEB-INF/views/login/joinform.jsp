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
			var params = "mem_id="+document.joinform.mem_id.value;
			sendRequest("${pageContext.request.contextPath}/logincont/idchk.do", params, id_chkresult, 'POST')	
		
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
		var name = document.joinform.mem_name.value;
		var pwdlength = pwd.length
		//아이디 중복체크 여부확인 chk값이  1이여야함
		if(chk == 0){
			alert('아이디 중복체크 하세요')
		//비밀번호가 공백일경우
		}else if(name==""){
			alert('이름을 입력해주세요')	
		//select된 mail주소가 self(직접입력)일경우	
		}else if(pwd==""){
			alert('비밀번호를 설정해주세요')	
			//select된 mail주소가 self(직접입력)일경우	
		}else if(5 >= pwdlength){
			alert("비밀번호는 6자리 이상으로 설정해주세요")			
		}else{
			var params = "mem_id="+document.joinform.mem_id.value;
			sendRequest("${pageContext.request.contextPath}/emailCont/sentMsg.do", params, chk_mail, 'POST')
		}	
	}
	
	function chk_mail() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var html = "<input type='text' name='certify'/> "+
		           "<input type='button' value='인증하기' onclick='sumbit_cert()'/>"
		        alert("가입예정자의 이메일로 메일을 첨부하였습니다 확인바랍니다.")
				document.getElementById("msgconfirm").innerHTML = '<h3>인증번호입력 : </h3>' + html 
			}
		}
	}
	
	function sumbit_cert(){
		document.joinform.action="${pageContext.request.contextPath}/logincont/join.do";
		document.joinform.submit();
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
아이디 : <input type="text" name="mem_id" placeholder="이메일주소를 입력하세요" onkeyup="id_chk()">
<div id="checkid"></div><br>
비밀번호 : <input type="password" name="mem_pwd" placeholder="비밀번호를 입력하세요"><br>
이름 : <input type="text" name="mem_name" placeholder="이름을 입력하세요"><br>
<input type="hidden" name="mem_survey" value="${survey_Answer}">
<input type="button" value="완료" onclick="join_do()"><input type="button" value="취소" onclick="cancle_do()">
</form>
<div id="msgconfirm"></div><br>

</body>
</html>