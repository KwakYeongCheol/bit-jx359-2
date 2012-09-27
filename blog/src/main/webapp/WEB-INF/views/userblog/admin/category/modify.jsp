<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	<form:form modelAttribute="category"  action="${pageContext.request.contextPath }/${blog.id }/admin/category/modifyAction" method="POST">
		<form:errors cssClass="error" path="title"></form:errors>
		<input type="hidden" name="id" value="${category.id }"/>
		<div>
			<form:label cssClass="title" path="title">Title</form:label>
			<form:input cssClass="title" path="title"/>
			<input type="submit" value="수정">
		</div>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />