<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function edit_do(type){
		if(type=='edit'){
			
			
			
		}else if(type=='dropout'){
			var flag = confirm("탈퇴하시겠습니까?");
			if(!flag){
				return
			}else{			
				document.mypage.action="${pageContext.request.contextPath}/logincont/dropoutpage.do"
				document.mypage.submit()
			}
		}
	}


</script>
<title>Insert title here</title>
</head>
<body>

<form name="mypage">
id : ${members.mem_id}<br>
이름 : ${members.mem_name}<br>
<input type="hidden" name="mem_id" value="${members.mem_id}">
<input type="button" value="정보수정" onclick="edit_do('edit')"><input type="button" value="탈퇴" onclick="edit_do('dropout')">
</form>
</body>
</html>