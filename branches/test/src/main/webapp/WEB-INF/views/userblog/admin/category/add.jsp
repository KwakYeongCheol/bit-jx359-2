<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	<form:form modelAttribute="category" action="${pageContext.request.contextPath }/${blog.id }/admin/category/addAction" method="POST">
	<form:errors cssClass="error" path="title"></form:errors>
		<div>
			<form:label cssClass="label" path="title">Title</form:label>
			<form:input cssClass="input" path="title"/>
			<input class="submit" type="submit" value="추가">
		</div>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />