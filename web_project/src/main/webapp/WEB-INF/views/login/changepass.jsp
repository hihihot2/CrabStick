<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">

	function pwd_chk() {
		var pwd = document.changepass.mem_pwd.value;
		if (pwd.length < 6) {
			alert("비밀번호는 6자리 이상으로 설정해주세요")
		} else if (pwd==""){
			alert("비밀번호는 6자리 이상으로 설정해주세요")
		} else {
			document.changepass.submit();
		}
	}
</script>
<title></title>
</head>
<body>
<form name="changepass" action="${pageContext.request.contextPath}/logincont/editpass.do" method="POST">
<input type="text" name="mem_pwd" />
<input type="hidden" name="mem_no" value="${mem_no }">
<input type="button" value="비밀번호변경" onclick="pwd_chk()">
</form>
</body>
</html>