<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function login_do(){
		document.loginform.action="${pageContext.request.contextPath}/logincont/login.do";
		document.loginform.submit();
	}
	function join_go(){
		document.loginform.action="${pageContext.request.contextPath}/logincont/joinpage.do";
		document.loginform.submit();		
	}
	function loginfail(){
		alert('로그인을 실패하였습니다.')
		document.loginform.mem_id.value="";
		document.loginform.mem_pwd.value="";
	}
</script>


<title>Insert title here</title>
</head>
<body>

<c:if test="${result eq 'fail'}">		
	<body onload="loginfail()" />
</c:if>



<form name="loginform">
id : <input type="text" name="mem_id">
password : <input type="password" name="mem_pwd"><br>
<input type="button" value="로그인" onclick="login_do()"><input type="button" value="cancel" onclick="cancel_do()"> <input type="button" value="회원가입" onclick="join_go()">
</form> 
</body>
</html>