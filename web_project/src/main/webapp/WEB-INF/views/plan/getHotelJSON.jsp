<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="hotel" items="${HOTELS}" varStatus="status">
	{name:'${hotel.name}', 'lat':${hotel.latitude}, 'lng':${hotel.longitude}, type:${type}, address:'${hotel.address}', img:''}<c:if test="${!status.last}">,</c:if>
</c:forEach>
]