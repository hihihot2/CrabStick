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
	{name: "${sight.title}", address: "${sight.addr1}", lat: "${sight.mapy}", lng: "${sight.mapx}", img: "${sight.imgURL}", type: 2}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty SIGHTS && (!empty SHOPPINGS || !empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="shopping" items="${SHOPPINGS }" varStatus="status">
	{name: "${shopping.title}", address: "${shopping.addr1}", lat: "${shopping.mapy}", lng: "${shopping.mapx}", img: "${shopping.imgURL}", type: 3}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty SHOPPINGS && (!empty RESTS || !empty NATURES)}">,</c:if>
	<c:forEach var="rest" items="${RESTS }" varStatus="status">
	{name: "${rest.title}", address: "${rest.addr1}", lat: "${rest.mapy}", lng: "${rest.mapx}", img: "${rest.imgURL}", type: 4}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
	<c:if test="${!empty RESTS && !empty NATURES}">,</c:if>
	<c:forEach var="nature" items="${NATURES }" varStatus="status">
	{name: "${nature.title}", address: "${nature.addr1}", lat: "${nature.mapy}", lng: "${nature.mapx}", img: "${nature.imgURL}", type: 5}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
]
