<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/${blog.id }/admin/category/addAction" method="POST">
		<label>Title </label><input type="text" name="title"  /> <br />
		<input type="submit" value="추가"  />
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />