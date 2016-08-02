<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript">
	
	function testFunction(form) {
		
		var arr = new Array();
		
		for(var i = 0; i < 4; i++) {
			var object = new Object();
			object.column1 = form.column1[i].value;			
			object.column2 = form.column2[i].value;			
			object.column3 = form.column3[i].value;
			
			arr.push(object);
		}
		
		console.log(arr);
		alert(arr);
		
		location.href="${pageContext.request.contextPath }/getObjects.do?json="+JSON.stringify(arr);
	}
	
	</script>
</head>
<body>
<form action="" name="testForm">
	컬럼1: <input type='text' name='column1'><br>
	컬럼2: <input type='text' name='column2'><br>
	컬럼3: <input type='text' name='column3'><br><br>
	
 	컬럼1: <input type='text' name='column1'><br>
	컬럼2: <input type='text' name='column2'><br>
	컬럼3: <input type='text' name='column3'><br><br>
	
	컬럼1: <input type='text' name='column1'><br>
	컬럼2: <input type='text' name='column2'><br>
	컬럼3: <input type='text' name='column3'><br><br>
	
	컬럼1: <input type='text' name='column1'><br>
	컬럼2: <input type='text' name='column2'><br>
	컬럼3: <input type='text' name='column3'><br>
	<input type='button' value='테스트^-^' onclick='testFunction(this.form)'>
</form>
</body>
</html>
