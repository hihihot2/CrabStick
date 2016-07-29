<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function action_do(type){		
		if(type == 'login'){
			document.loginpage.action="${pageContext.request.contextPath}/logincont/loginpage.do";
			document.loginpage.submit();
		}else if(type=='logout'){
			var flag = confirm("로그아웃하시겠습니까?");
			if(!flag){
				return
			}else{			
				document.loginpage.action="${pageContext.request.contextPath}/logincont/logout.do";
				document.loginpage.submit();
			}
		}	
	}


</script>

<title>Insert title here</title>
</head>
<body>
	<form name="loginpage">
	<c:choose>
		<c:when test="${no ne null}"><input type="button" value="logout" onclick="action_do('logout')"></c:when>
		<c:otherwise><input type="submit" value="login" onclick="action_do('login')"></c:otherwise>
	</c:choose>
	<hr>
	</form>
</body>
</html>