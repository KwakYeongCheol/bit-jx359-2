<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="blog">
	<div id="blogHeader">
		<div id="blogTitle">
			${blog.title }
		</div>
	</div>
		
	<div id="blogMenu">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/">홈</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a></li> 
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/visitor">방명록</a></li>
		</ul> 
	</div>
	
	<div id="blogContents">
		<div id="blogCategory">
			<c:forEach items="${categoryList }" var="category">
				<a href="${pageContext.request.contextPath }/${blog.id}/category/${category.id}">${category.title }</a><br />
			</c:forEach>
		</div>