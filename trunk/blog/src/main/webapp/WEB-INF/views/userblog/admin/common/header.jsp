<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" ></jsp:include>
<c:forEach items="${loginUserProvider.blogList }" var="blog">
<div class="menu">
			<div class="menu-title">관리자페이지</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/${blog.id}">블로그 홈</a></li>
				</ul>				
			</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/category">카테고리 관리</a></li>
					<li><a href="${pageContext.request.contextPath }/${blog.id}/admin">게시물 관리</a></li>
					<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a></li>
				</ul>				
			</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/visit_history">방문자 통계</a></li>
				</ul>				
			</div>
		</div>
		</c:forEach>
		<article class="articleB">