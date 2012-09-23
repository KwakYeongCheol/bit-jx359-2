<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	글 목록
	<c:forEach items="${postList }" var="post">
	<div class="blogArticle">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
		${post.title } | 
		${post.dateCreated } | 
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/modify?id=${post.id}">수정</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/delete?id=${post.id}">삭제</a><br />
		${post.contents }
	</div>
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />