<%@ page language="java" contentType="Application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	plan: {
		plan_no: ${PLAN.plan_no},
		plan_name: '${PLAN.plan_name}',
		plan_commt: '${PLAN.plan_commt}',
		plan_cost: ${PLAN.plan_cost},
		plan_persons: ${PLAN.plan_persons},
		plan_style: ${PLAN.plan_style},
		loc_no: ${PLAN.loc_no}
	},
	path: {
		path_no: ${PATH.path_no},
		path_name: '${PATH.path_name}',
		path_summary: '${PATH_SUMMARY}'
	}	
}