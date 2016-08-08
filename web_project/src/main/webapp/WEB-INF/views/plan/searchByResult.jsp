<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix= "c" uri ="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="resultList" items="${resultList }" varStatus="status">
<c:if test="${not status.first }">,</c:if>
{num:${resultList.loc_no }, name:'${resultList.loc_name }', lati:'${resultList.loc_lati }', long2:'${resultList.loc_long }'}
</c:forEach>
]