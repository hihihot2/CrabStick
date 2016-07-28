/**
 * 상단 메뉴의 기능 스크립트 파일
 */
function action_do(str){
		if(str == 'login'){
			location.href="../viewcont/loginform.do";
		}else if(str == 'logout'){
			location.href="../logincont/logout.do";
		}else if(str == 'mypage'){
			location.href="../viewcont/myform.do";
		}else if(str == 'orderInfo'){
			location.href="../viewcont/myorderform.do";
		}
}
function searchEvent(){ //검색기능 enter 와 나머지키로 구분하여 기능수행
	var params = "searchData="+encodeURIComponent(document.getElementById("search").value);
	if(event.keyCode == 13){
		location.href="../custcont/search.do?"+params;
	}/* else{ //검색시 리스트형태로 보여주기
		sendRequest("${pageContext.request.contextPath}/cont/searchlist.do", params, callback, method);
	} */
}