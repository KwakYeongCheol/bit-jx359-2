<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common/header.jsp" />

<div>
	<h1>
	HOME PAGE
	</h1>
</div>
<div border="1">
	<div>내 블로그</div>
	<a href="${pageContext.request.contextPath }/${blog.id}">${blog.title }</a> &nbsp;
	<a href="${pageContext.request.contextPath }/blog/settings">설정</a>
</div>

<jsp:include page="common/footer.jsp" />