<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h1>방명록 수정</h1>
<div>
	<form action="${pageContext.request.contextPath }/${blog.id}/visitor/modifyAction" method="post">
		<input type="hidden" name="id" value="${visitor.id }" />
		<input type="text" name="contents" value="${visitor.contents }">
		<input type="submit" value="수정">
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />