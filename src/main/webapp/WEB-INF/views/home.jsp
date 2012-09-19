<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/header.jsp" />

<div id="blogInfo">
	<c:if test="${loginUser != null }">
		<div>내 블로그 &nbsp; &nbsp; <a href="${pageContext.request.contextPath }/blog/settings">설정</a></div>
		<c:if test="${blog != null }">
		<a href="${pageContext.request.contextPath }/${blog.id}">${blog.title }</a> &nbsp;
		</c:if>
	</c:if>
</div>

<jsp:include page="common/footer.jsp" />