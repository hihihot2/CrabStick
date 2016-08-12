<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
<c:forEach var="attr" items="${ATTR}" varStatus="status">
		{'name':'${attr.title}', 'lat':${attr.mapy}, 'lng':${attr.mapx}, 'type':${type}, address:'${attr.addr1}', img:'${attr.imgURL}'}<c:if test="${!status.last}">,</c:if>
</c:forEach>
]