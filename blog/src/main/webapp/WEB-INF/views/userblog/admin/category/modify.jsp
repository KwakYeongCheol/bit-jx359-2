<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/${blog.id }/admin/category/modifyAction" method="POST">
		<input type="hidden" name="id" value="${category.id }"/>
		<label>Title </label><input type="text" name="title" value="${category.title }"/> <br />
		<input type="submit" value="수정" />
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />