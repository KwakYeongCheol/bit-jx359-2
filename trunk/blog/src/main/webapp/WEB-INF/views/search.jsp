<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/search" method="post">
		<input type="text" name="query" value="${query }"/>
		<input type="submit" value="검색" />
	</form>
</div>

<div id="search">
	<c:forEach items="${search.postList }" var="post">
	<div>
		<a href="${pageContext.request.contextPath }/${post.category.blog.id }/${post.displayId }">
			${post.title }
		</a>
	</div>
	</c:forEach>
</div>

<jsp:include page="common/footer.jsp" />