<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />


<div id="articles">
	<c:forEach items="${postList }" var="post">
	<div class="article">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
		${post.title } | 
		${post.dateCreated } <br />
		
		${post.contents }
	</div>
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />