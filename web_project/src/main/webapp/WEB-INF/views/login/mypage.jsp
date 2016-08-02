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
			var password1 = document.mypage.mem_pwd.value;
			var password2 = document.mypage.mem_repwd.value;
			var flag = confirm("회원정보를 수정하시겠습니까?");
			if(!flag){
				return
			} else {
				if(password1!=password2){
					return
				} else {
					document.mypage.action="${pageContext.request.contextPath}/logincont/editpass.do"
					document.mypage.submit()
				}
			}
			
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

<form name="mypage" method="POST">
id : ${members.mem_id}<br>
이름 : ${members.mem_name}<br>
비밀번호      : <input type="text" name="mem_pwd"/>
비밀번호 확인 : <input type="text" name="mem_repwd"/>
<input type="hidden" name="mem_id" value="${members.mem_id}">
<input type="hidden" name="mem_no" value="${members.mem_no}">
<input type="button" value="비밀번호 수정" onclick="edit_do('edit')"/>
<input type="button" value="탈퇴" onclick="edit_do('dropout')">
</form>
</body>
</html>