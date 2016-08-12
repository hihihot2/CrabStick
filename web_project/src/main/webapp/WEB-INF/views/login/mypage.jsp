
<!-- TOP.jsp 삽입 부분 -->
<jsp:include page="../top.jsp"></jsp:include>
<!-- TOP.jsp 삽입 부분 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery_cookie.js"
	type="text/javascript"></script>
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
				document.mypage.action="${pageContext.request.contextPath}/survey/main.do?sw=1"
				document.mypage.submit()
			}
		}
	}


</script>
<title>Insert title here</title>
<style type="text/css">
.lgcss {
	margin-top: 10%;
	margin-left: 30%;
	margin-right: 30%;
	width: 40%;
	min-width: 300px;
	min-height: 500px;
}

</style>
</head>
<body>
	<br>
	<br>

	<form name="mypage" method="POST" class="lgcss" >
		<input type="hidden" name="mem_id" value="${members.mem_id}">
		<input type="hidden" name="mem_no" value="${members.mem_no}">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${members.mem_name}님의 정보수정</h1>
			</div>

			<div class="panel-body">

				비밀번호: <input type="text" class="form-control" name="mem_pwd">
				<br> 비밀번호 확인 : <input type="text" class="form-control"
					name="mem_repwd" /> <br> <input type="button"
					class="btn btn-info" value="비밀번호 수정" onclick="edit_do('edit')">
				<input type="button" class="btn btn-warning" value="탈퇴"
					onclick="edit_do('dropout')">
			</div>

			<div class="panel-footer">
				<h3>설문조사 변경 란</h3>
				<input type="button" class="btn btn-warning" value="설문조사 변경" onclick="edit_do('survey')" />
			</div>
		</div>


	</form>
</body>
</html>