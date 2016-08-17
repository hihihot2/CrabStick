<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
	<c:forEach var="hotel" items="${HOTELS.hotelList }" varStatus="status">
	{name: "${hotel.name }", address: "${hotel.address }", lat: "${hotel.latitude }", lng: "${hotel.longitude }", img: "http://media.expedia.com${hotel.thumbnailUrl}", type: 0}<c:if test="${!status.last }">,</c:if> 
	</c:forEach>
	<c:if test="${!empty HOTELS && (!empty VENUES || !empty SIGHTS || !empty SHOPPINGS || !empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="venue" items="${VENUES }" varStatus="status">
	{name: "${venue.name }", address: "${venue.location.address}", lat: "${venue.location.lat}", lng: "${venue.location.lng }", img: "", type: 1}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty VENUES && (!empty SIGHTS || !empty SHOPPINGS || !empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="sight" items="${SIGHTS }" varStatus="status">
	{name: "", address: "", lat: "", lng: "", img: "", type: 2}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty SIGHTS && (!empty SHOPPINGS || !empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="shopping" items="${SHOPPINGS }" varStatus="status">
	{name: "", address: "", lat: "", lng: "", img: "", type: 3}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty SHOPPINGS && (!empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="rest" items="${RESTS }" varStatus="status">
	{name: "", address: "", lat: "", lng: "", img: "", type: 4}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty RESTS && !empty NATURES}">,</c:if>
	<c:forEach var="nature" items="${NATURES }" varStatus="status">
	{name: "", address: "", lat: "", lng: "", img: "", type: 5}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
]
