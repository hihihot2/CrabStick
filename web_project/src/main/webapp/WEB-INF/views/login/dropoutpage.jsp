<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script type="text/javascript">
	function dropout_do(){
		var id =  encodeURIComponent(document.dropout.mem_id.value);
		var params = "mem_id="+id+"&mem_pwd="+document.dropout.mem_pwd.value;
		sendRequest("${pageContext.request.contextPath}/logincont/dropout.do", params, dropoutresult, 'POST')
	}
	function dropoutresult(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var str = httpRequest.responseText;
				if(str==1){
					alert('삭제되었습니다.')
					location.href="${pageContext.request.contextPath}/";
				}else if(str==0){
					alert('비밀번호가 맞지 않습니다.')
				}				
			}
		}
	}
	
</script>
<title>Insert title here</title>
</head>
<body>
<h3>비밀번호를 입력하세요</h3>
<form name="dropout">
비밀번호 : <input type="password" name="mem_pwd" >
<input type="hidden" name="mem_id" value="${mem_id}">
<input type="button" value="탈퇴하기" onclick="dropout_do()">
</form>

</body>
</html>