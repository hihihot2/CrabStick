<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="group" items="${VENUES}">
	<c:forEach var="venue" items="${group.items}" varStatus="status">
		{name:'${venue.name}', lat:${venue.location.lat}, lng:${venue.location.lng}, type:${type}, address:'${venue.location.address}', img:''}<c:if test="${!status.last}">,</c:if>
	</c:forEach>
</c:forEach>
]