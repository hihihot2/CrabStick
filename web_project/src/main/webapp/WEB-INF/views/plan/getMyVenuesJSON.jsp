<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	path_no: ${PATH.path_no },
	path_name: '${PATH.path_name }',
	venues: [
		<c:forEach var="venue" items="${VENUES }" varStatus="status">
		{
			ven_no: ${venue.ven_no },
			ven_name: '${venue.ven_name }',
			ven_lati: ${venue.ven_lati },
			ven_long: ${venue.ven_long },
			ven_commt: '${venue.ven_commt }',
			ven_type: ${venue.ven_type },
			ven_order: ${venue.ven_order },
			path_no: ${venue.path_no },
			loc_no: ${venue.loc_no }
		}<c:if test="${!status.last}">,</c:if>
		</c:forEach>
	]
}
	
