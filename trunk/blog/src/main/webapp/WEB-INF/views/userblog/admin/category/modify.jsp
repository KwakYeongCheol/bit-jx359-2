<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form:form modelAttribute="category"  action="${pageContext.request.contextPath }/${blog.id }/admin/category/modifyAction" method="POST">
		<input type="hidden" name="id" value="${category.id }"/>
		<p>
			<form:label path="title">Title</form:label>
			<form:input path="title"/>
			<input type="submit" value="수정">
		</p>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />