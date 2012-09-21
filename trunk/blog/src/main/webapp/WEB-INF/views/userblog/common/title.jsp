<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="header">
	${blog.title }
	<div id="link">
		<a href="${pageContext.request.contextPath }/${blog.id}/">홈</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a> | 
		<a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/visitor">방명록</a>
	</div>
</div>