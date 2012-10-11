<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="blog">
	<div id="blogHeader">
		<div id="blogTitle">
			${blog.title }
			<c:if test="${loginUserProvider.loggedIn && (loginUserProvider.blog.id != blog.id) }">
				<c:if test="${!loginUserProvider.isExistFavorite(blog.id) }">
				<a href="${pageContext.request.contextPath }/favorite/addAction?blogId=${blog.id}">이웃 추가</a>
				</c:if>
			</c:if>
		</div>
	</div>
		
	<div id="blogMenu">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/">홈</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/guestbook">방명록</a></li>
			<c:if test="${loginUserProvider.loggedIn }">
			<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a></li> 
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a></li>
			</c:if>
			</c:if>
		</ul> 
	</div>
	
	<div id="blogContents">
		<div id="blogCategory">
			<c:forEach items="${categoryList }" var="category">
				<a href="${pageContext.request.contextPath }/${blog.id}/category/${category.displayId}">${category.title }</a><br />
			</c:forEach>
		</div>