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
		var mail2 = document.joinform.email2.value;
		if(mem_id == ""){
			alert('아이디를 입력하세요')
		}else if(mail2==""){
			alert('메일을 선택하세요')
		//select값이 직접입력이고 입력된 값에 '.com'이 없을 경우 띄우는 alert	
		}else if(!mail2.match('.com')){
			alert('메일형식이 맞지 않습니다.')
		}else{		
			var params = "mem_id="+document.joinform.mem_id.value+"@"+document.joinform.email2.value;
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
		alert(chk)
		var pwd = document.joinform.mem_pwd.value;
		var pwdchk = document.joinform.mem_pwdchk.value;
		var mail = document.joinform.email_select.value;	
		//아이디 중복체크 여부확인 chk값이  1이여야함
		if(chk == 0){
			alert('아이디 중복체크 하세요')
		//비밀번호와 비밀번호확인 값이 같아야함	
		}else if(pwd!=pwdchk){
			alert('비밀번호가 일치하지 않습니다.')
		//select된 mail주소가 0('선택하세요')값일 경우 띄우는 alert
		}else if(mail==0){
			alert("메일을 선택하세요")			
		//select된 mail주소가 self(직접입력)일경우	
		}else if(mail=='self'){
			document.joinform.mem_id.value += "@"+document.joinform.email2.value;
			document.joinform.action="${pageContext.request.contextPath}/logincont/join.do";
			document.joinform.submit();
		}else if(mail=='naver.com'|| mail=='daum.com' ||mail=='google.com'||mail=='yahoo.com'){
			document.joinform.mem_id.value += "@"+document.joinform.email2.value;
			document.joinform.action="${pageContext.request.contextPath}/logincont/join.do";
			document.joinform.submit();
		}	
	}
	function cancle_do(){
		document.joinform.action = "${pageContext.request.contextPath}/";
		document.joinform.submit();	
	}
	
	function checkemailaddy() {
		var mail = document.joinform.email_select.value;

		if (joinform.email_select.value == 'self') {
			joinform.email2.readOnly = false;
			joinform.email2.value = '';
			joinform.email2.focus();
		} else {
			joinform.email2.readOnly = "readonly";
			if (mail == 0) {
				document.joinform.email2.value = ""
			} else {
				joinform.email2.value = joinform.email_select.value;

			}
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
<form name="joinform" method="post">
<input type="hidden" name="chk" value="0">
아이디 : <input type="text" name="mem_id" placeholder="이메일주소를 입력하세요">@<input name="email2" type="text" class="box" id="email2" size="20" readonly="readonly">
<select name="email_select" id="email_select" onChange="checkemailaddy()">
 <option value="0">선택하세요</option>
 <option value="naver.com">naver.com</option>
 <option value="daum.com">daum.com</option>
 <option value="google.com">google.com</option>
 <option value="yahoo.com">yahoo.com</option>
 <option value="self">직접입력</option>
</select>


<input type="button" value="중복확인" onclick="id_chk()"><div id="checkid"></div><br>
비밀번호 : <input type="password" name="mem_pwd"><br>
비밀번호확인 : <input type="password" name="mem_pwdchk"><br>
이름 : <input type="text" name="mem_name"><br>
<input type="button" value="완료" onclick="join_do()"><input type="button" value="취소" onclick="cancle_do()">
</form>



</body>
</html>