<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="list" items="${SLIST}" varStatus="status">
	{title:'${list.title}', addr:'${list.addr}', lat:${list.lat}, lng:${list.lng}}<c:if test="${!status.last}">,</c:if>
</c:forEach>
]