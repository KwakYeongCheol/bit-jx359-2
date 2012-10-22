<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div id="blogArticles">
	<form:form modelAttribute="guestbook" action="${pageContext.request.contextPath }/${blog.id}/guestbook/modifyAction" method="post">
		<input type="hidden" name="displayId" value="${guestbook.displayId }" />
		<p>
			<form:input path="contents"/>
			<input type="submit" value="수정">		
		</p>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />