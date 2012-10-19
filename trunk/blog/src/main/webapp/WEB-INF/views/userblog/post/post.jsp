<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${postList != null }">
	<jsp:include page="postListTemplate.jsp" />
</c:if>

<c:if test="${post != null }">
	<jsp:include page="postTemplate.jsp" />
</c:if>
