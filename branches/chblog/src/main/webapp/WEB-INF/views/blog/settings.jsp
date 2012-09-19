<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>

<c:if test="${blog != null }">
	<form action="${pageContext.request.contextPath }/blog/modify" method="POST">
		<label>Blog Title</label><input type="text" name="title" value="${blog.title }" /><br />
		<input type="submit" value="블로그 수정" />
	</form>
</c:if>

<c:if test="${blog == null }">
	<form action="${pageContext.request.contextPath }/blog/create" method="POST">
		<label>Blog Title</label><input type="text" name="title" /> <br />
		<input type="submit" value="블로그 생성" />
	</form>
</c:if>
	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />