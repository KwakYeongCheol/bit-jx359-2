<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="blogSetting">
	<h2>블로그 정보</h2>
	
	<div>
	<c:forEach items="${blogList }" var="blog">
		<div>
			<a href="${pageContext.request.contextPath }/${blog.id}">${blog.title }</a> |
			<a href="${pageContext.request.contextPath }/blog/settings?id=${blog.id}">설정하기</a>
		</div>
	</c:forEach>
	</div>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />