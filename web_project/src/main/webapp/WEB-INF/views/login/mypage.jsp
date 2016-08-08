<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js" type="text/javascript"></script>
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
					alert('비밀번호가 일치하지 않습니다. 다시 확인하세요')
					return
				} else if(password1.length < 6){
					alert('비밀번호는 6자리 이상을 설정하세요')
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
		}else if(type=='survey'){
			var flag = confirm("설문조사를 다시 응하시겠습니까? \n 다시 응하시게되면 기존에 작성한 설문조사 내용은 폐기됩니다.")
			if(!flag){
				return
			} else {
				document.mypage.action="${pageContext.request.contextPath}/survey/remain.do"
				document.mypage.submit()
			}
		}
	}


</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<form name="mypage" method="POST">
id : ${members.mem_id}<br>
이름 : ${members.mem_name}<br>
비밀번호      : <input type="text" name="mem_pwd"/>
비밀번호 확인 : <input type="text" name="mem_repwd"/>
<input type="hidden" name="mem_id" value="${members.mem_id}">
<input type="hidden" name="mem_no" value="${members.mem_no}">
<input type="button" value="비밀번호 수정" onclick="edit_do('edit')"/>
<input type="button" value="탈퇴" onclick="edit_do('dropout')">
<h3> 설문조사 변경 란</h3>
<input type="button" value="설문조사 변경" onclick="edit_do('survey')"/>
</form>
</body>
</html>