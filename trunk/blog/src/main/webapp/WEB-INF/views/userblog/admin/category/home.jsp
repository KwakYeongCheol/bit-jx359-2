<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	카테고리 목록 &nbsp; &nbsp; <a href="${pageContext.request.contextPath }/${blog.id }/admin/category/add">카테고리 추가</a>
	<br /><br />
	<div>
		<c:forEach items="${categoryList }" var="category">
		<div>
			${category.title } (order value : ${category.orderValue }) | 
			<a href="${pageContext.request.contextPath }/${blog.id}/admin/category/modify?displayId=${category.displayId}">수정</a> |
			<a href="${pageContext.request.contextPath }/${blog.id}/admin/category/delete?displayId=${category.displayId}">삭제</a><br />
		</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />