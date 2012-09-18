<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/blog/create" method="POST">
		<label>Blog Title</label><input type="text" name="title" /> <br />
		<input type="submit" value="블로그 생성" />
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />