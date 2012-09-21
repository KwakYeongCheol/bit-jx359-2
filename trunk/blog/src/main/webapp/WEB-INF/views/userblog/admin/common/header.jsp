<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="header">
	관리자 페이지 - ${blog.title } 
	<div id="link">
		<a href="${pageContext.request.contextPath }/${blog.id}/">블로그 홈</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a> | 
		<a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/visitor">방명록</a>
	</div>
</div>

<div id="menu">
	<div>
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/category">카테고리 관리</a>
	</div>
	<div>
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post">글 목록</a>
	</div>
</div>