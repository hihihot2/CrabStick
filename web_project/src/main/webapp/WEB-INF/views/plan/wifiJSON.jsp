<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="loc" items="${LOC}" varStatus="status">
	{"lat":${loc.lat}, "lang":${loc.lang}}<c:if test="${!status.last}">,</c:if>
</c:forEach>
]