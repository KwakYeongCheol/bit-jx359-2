<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div id="blogArticles">
	<form:form modelAttribute="comment" action="${pageContext.request.contextPath }/${blog.id}/comment/modifyAction" method="post">
		<form:errors cssClass="error" path="contents"></form:errors>
		<form:hidden path="displayId" value="${comment.displayId }"/>
		<form:hidden path="target.id" value="${comment.target.id }" />
		<form:hidden path="target.type" value="${comment.target.type }" />
		<form:input path="contents" value="${comment.contents }" />
		<input type="submit" value="수정">		
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />